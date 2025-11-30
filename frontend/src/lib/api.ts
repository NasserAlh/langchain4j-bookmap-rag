import type { Settings, ChatResponse, TokenUsage, ApiError } from '$lib/types';

const API_BASE = 'http://localhost:8080/api';

// Custom error class that carries the structured error info
export class ChatApiError extends Error {
	constructor(public readonly apiError: ApiError) {
		super(apiError.message);
		this.name = 'ChatApiError';
	}
}

export async function sendMessage(message: string): Promise<ChatResponse> {
	const res = await fetch(`${API_BASE}/chat`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ message })
	});
	if (!res.ok) {
		const error = await res.json().catch(() => ({
			errorId: 'unknown',
			code: 'NETWORK_ERROR',
			message: 'Chat request failed',
			timestamp: Date.now()
		}));
		// Check if it's a structured error response
		if (error.errorId) {
			throw new ChatApiError(error as ApiError);
		}
		// Legacy error format fallback
		throw new ChatApiError({
			errorId: 'unknown',
			code: 'UNKNOWN',
			message: error.error || 'Chat request failed',
			timestamp: Date.now()
		});
	}
	return res.json();
}

export async function getSettings(): Promise<Settings> {
	const res = await fetch(`${API_BASE}/settings`);
	if (!res.ok) throw new Error('Failed to get settings');
	return res.json();
}

export async function updateSettings(settings: Partial<Settings>): Promise<void> {
	const res = await fetch(`${API_BASE}/settings`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(settings)
	});
	if (!res.ok) throw new Error('Failed to update settings');
}

export async function toggleRag(): Promise<{ ragEnabled: boolean }> {
	const res = await fetch(`${API_BASE}/rag/toggle`, { method: 'POST' });
	if (!res.ok) throw new Error('Failed to toggle RAG');
	return res.json();
}

export async function ingestDocuments(): Promise<{ documentsIndexed: number }> {
	const res = await fetch(`${API_BASE}/rag/ingest`, { method: 'POST' });
	if (!res.ok) throw new Error('Failed to ingest documents');
	return res.json();
}

export async function clearHistory(): Promise<void> {
	const res = await fetch(`${API_BASE}/history/clear`, { method: 'POST' });
	if (!res.ok) throw new Error('Failed to clear history');
}

export async function checkHealth(): Promise<boolean> {
	try {
		const res = await fetch(`${API_BASE}/health`);
		return res.ok;
	} catch {
		return false;
	}
}

// Streaming configuration
const MAX_RETRIES = 3;
const BASE_RETRY_DELAY_MS = 1000;

// Streaming result type for partial response tracking
export interface StreamingCallbacks {
	onToken: (token: string) => void;
	onComplete: (usage: TokenUsage) => void;
	onError: (error: ApiError, partialContent: string) => void;
	onRetry?: (attempt: number, maxRetries: number) => void;
}

// Streaming chat using native fetch + ReadableStream with auto-reconnect
export async function sendStreamingMessage(
	message: string,
	onToken: (token: string) => void,
	onComplete: (usage: TokenUsage) => void,
	onError: (error: ApiError, partialContent?: string) => void,
	onRetry?: (attempt: number, maxRetries: number) => void
): Promise<void> {
	let accumulatedContent = '';
	let completedSuccessfully = false;

	for (let attempt = 0; attempt <= MAX_RETRIES; attempt++) {
		// Notify retry (skip first attempt)
		if (attempt > 0) {
			onRetry?.(attempt, MAX_RETRIES);
			// Exponential backoff: 1s, 2s, 4s
			const delay = BASE_RETRY_DELAY_MS * Math.pow(2, attempt - 1);
			await new Promise((resolve) => setTimeout(resolve, delay));
		}

		try {
			const result = await executeStreamRequest(
				message,
				(token) => {
					accumulatedContent += token;
					onToken(token);
				},
				(usage) => {
					completedSuccessfully = true;
					onComplete(usage);
				}
			);

			// If we got here without error, we're done
			if (result.completed) {
				return;
			}

			// Stream ended unexpectedly without 'done' event
			if (!completedSuccessfully && attempt < MAX_RETRIES) {
				continue; // Retry
			}

			// Max retries exceeded, report error with partial content
			if (!completedSuccessfully) {
				onError(
					{
						errorId: 'stream-incomplete',
						code: 'NETWORK_ERROR',
						message: 'Stream ended unexpectedly',
						timestamp: Date.now()
					},
					accumulatedContent
				);
			}
			return;
		} catch (error) {
			// Check if it's a server error (non-retryable)
			if (error instanceof StreamError && error.isServerError) {
				onError(error.apiError, accumulatedContent);
				return;
			}

			// Network error - retry if attempts remain
			if (attempt < MAX_RETRIES) {
				continue;
			}

			// Max retries exceeded
			const apiError: ApiError =
				error instanceof StreamError
					? error.apiError
					: {
							errorId: 'unknown',
							code: 'NETWORK_ERROR',
							message: error instanceof Error ? error.message : 'Unknown error',
							timestamp: Date.now()
						};

			onError(apiError, accumulatedContent);
			return;
		}
	}
}

// Custom error class for stream errors
class StreamError extends Error {
	constructor(
		public readonly apiError: ApiError,
		public readonly isServerError: boolean = false
	) {
		super(apiError.message);
		this.name = 'StreamError';
	}
}

// Execute a single stream request (no retry logic)
async function executeStreamRequest(
	message: string,
	onToken: (token: string) => void,
	onComplete: (usage: TokenUsage) => void
): Promise<{ completed: boolean }> {
	const res = await fetch(`${API_BASE}/chat/stream`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ message })
	});

	if (!res.ok) {
		const errorData = await res.json().catch(() => null);
		const apiError: ApiError = errorData?.errorId
			? (errorData as ApiError)
			: {
					errorId: 'unknown',
					code: 'NETWORK_ERROR',
					message: 'Stream request failed',
					timestamp: Date.now()
				};
		// Server errors (4xx/5xx) are not retryable
		throw new StreamError(apiError, res.status >= 400 && res.status < 500);
	}

	const reader = res.body?.getReader();
	if (!reader) {
		throw new StreamError({
			errorId: 'unknown',
			code: 'NETWORK_ERROR',
			message: 'No response body',
			timestamp: Date.now()
		});
	}

	const decoder = new TextDecoder();
	let buffer = '';
	let completed = false;

	try {
		while (true) {
			const { done, value } = await reader.read();
			if (done) break;

			buffer += decoder.decode(value, { stream: true });

			// Parse SSE events from buffer
			const lines = buffer.split('\n');
			buffer = lines.pop() || ''; // Keep incomplete line in buffer

			let eventType = '';
			for (const line of lines) {
				if (line.startsWith('event: ')) {
					eventType = line.slice(7).trim();
				} else if (line.startsWith('data: ')) {
					const data = line.slice(6);
					if (eventType === 'token') {
						// Parse the token (it's JSON-escaped string)
						try {
							const token = JSON.parse(data);
							onToken(token);
						} catch {
							// If parsing fails, use raw data
							onToken(data);
						}
					} else if (eventType === 'done') {
						try {
							const usage = JSON.parse(data);
							onComplete(usage);
							completed = true;
						} catch {
							onComplete({ inputTokens: 0, outputTokens: 0, estimatedCost: 0 });
							completed = true;
						}
					} else if (eventType === 'error') {
						// Parse structured error from backend - these are server errors, not retryable
						let apiError: ApiError;
						try {
							const errorData = JSON.parse(data);
							apiError = errorData.errorId
								? (errorData as ApiError)
								: {
										errorId: 'unknown',
										code: 'UNKNOWN',
										message: String(errorData),
										timestamp: Date.now()
									};
						} catch {
							apiError = {
								errorId: 'unknown',
								code: 'UNKNOWN',
								message: data.replace(/^"|"$/g, ''),
								timestamp: Date.now()
							};
						}
						throw new StreamError(apiError, true);
					}
				}
			}
		}
	} finally {
		reader.releaseLock();
	}

	return { completed };
}
