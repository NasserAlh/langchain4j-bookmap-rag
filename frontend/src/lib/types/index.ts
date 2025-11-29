export interface Message {
	id: string;
	role: 'user' | 'assistant';
	content: string;
	timestamp: number;
	usage?: TokenUsage;
	error?: string;
}

export interface TokenUsage {
	inputTokens: number;
	outputTokens: number;
	estimatedCost: number;
}

export interface Settings {
	model: 'deepseek-chat' | 'deepseek-reasoner';
	temperature: number;
	ragEnabled: boolean;
	streamingEnabled: boolean;
	showUsage: boolean;
}

export interface ChatRequest {
	message: string;
}

export interface ChatResponse {
	response: string;
	usage: TokenUsage;
}

export type ConnectionStatus = 'idle' | 'connecting' | 'connected' | 'reconnecting' | 'error';
