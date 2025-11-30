import type { Message, Settings, TokenUsage, ConnectionStatus, ApiError } from '$lib/types';

// Reactive state using Svelte 5 $state rune
export const chatState = $state({
	messages: [] as Message[],
	isStreaming: false,
	isLoading: false,
	error: null as string | null,
	connectionStatus: 'idle' as ConnectionStatus,
	currentAssistantId: null as string | null,
	settings: {
		model: 'deepseek-chat',
		temperature: 0.7,
		ragEnabled: false,
		streamingEnabled: true,
		showUsage: true
	} as Settings
});

// Helper functions
export function addMessage(role: 'user' | 'assistant', content: string): string {
	const id = crypto.randomUUID();
	chatState.messages.push({
		id,
		role,
		content,
		timestamp: Date.now()
	});
	return id;
}

export function updateMessage(id: string, content: string) {
	const msg = chatState.messages.find((m) => m.id === id);
	if (msg) msg.content = content;
}

export function appendToMessage(id: string, content: string) {
	const msg = chatState.messages.find((m) => m.id === id);
	if (msg) msg.content += content;
}

export function setMessageUsage(id: string, usage: TokenUsage) {
	const msg = chatState.messages.find((m) => m.id === id);
	if (msg) msg.usage = usage;
}

export function setMessageError(id: string, error: ApiError) {
	const msg = chatState.messages.find((m) => m.id === id);
	if (msg) msg.error = error;
}

export function clearMessages() {
	chatState.messages = [];
}

export function updateSettings(settings: Partial<Settings>) {
	Object.assign(chatState.settings, settings);
}

export function setError(error: string | null) {
	chatState.error = error;
	if (error) {
		// Auto-clear error after 5 seconds
		setTimeout(() => {
			if (chatState.error === error) {
				chatState.error = null;
			}
		}, 5000);
	}
}

export function setConnectionStatus(status: ConnectionStatus) {
	chatState.connectionStatus = status;
}

export function setStreaming(streaming: boolean) {
	chatState.isStreaming = streaming;
}

export function setLoading(loading: boolean) {
	chatState.isLoading = loading;
}
