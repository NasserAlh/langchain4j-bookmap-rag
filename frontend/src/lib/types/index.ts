export interface ApiError {
	errorId: string;
	code: string;
	message: string;
	details?: string;
	timestamp: number;
}

export interface Message {
	id: string;
	role: 'user' | 'assistant';
	content: string;
	timestamp: number;
	usage?: TokenUsage;
	error?: ApiError;
	interrupted?: boolean; // True if response was cut off due to error
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
