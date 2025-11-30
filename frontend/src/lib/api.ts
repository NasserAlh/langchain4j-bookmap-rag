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

// Streaming chat using native fetch + ReadableStream
export async function sendStreamingMessage(
	message: string,
	onToken: (token: string) => void,
	onComplete: (usage: TokenUsage) => void,
	onError: (error: ApiError) => void
): Promise<void> {
	try {
		const res = await fetch(`${API_BASE}/chat/stream`, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ message })
		});

		if (!res.ok) {
			const errorData = await res.json().catch(() => null);
			if (errorData?.errorId) {
				onError(errorData as ApiError);
			} else {
				onError({
					errorId: 'unknown',
					code: 'NETWORK_ERROR',
					message: 'Stream request failed',
					timestamp: Date.now()
				});
			}
			return;
		}

		const reader = res.body?.getReader();
		if (!reader) {
			onError({
				errorId: 'unknown',
				code: 'NETWORK_ERROR',
				message: 'No response body',
				timestamp: Date.now()
			});
			return;
		}

		const decoder = new TextDecoder();
		let buffer = '';

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
						} catch {
							onComplete({ inputTokens: 0, outputTokens: 0, estimatedCost: 0 });
						}
					} else if (eventType === 'error') {
						// Parse structured error from backend
						try {
							const errorData = JSON.parse(data);
							if (errorData.errorId) {
								onError(errorData as ApiError);
							} else {
								// Legacy string error
								onError({
									errorId: 'unknown',
									code: 'UNKNOWN',
									message: String(errorData),
									timestamp: Date.now()
								});
							}
						} catch {
							// Plain string error
							onError({
								errorId: 'unknown',
								code: 'UNKNOWN',
								message: data.replace(/^"|"$/g, ''),
								timestamp: Date.now()
							});
						}
					}
				}
			}
		}
	} catch (error) {
		onError({
			errorId: 'unknown',
			code: 'NETWORK_ERROR',
			message: error instanceof Error ? error.message : 'Unknown error',
			timestamp: Date.now()
		});
	}
}
