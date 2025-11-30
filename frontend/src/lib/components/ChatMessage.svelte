<script lang="ts">
	import type { Message } from '$lib/types';
	import { User, Bot, CircleAlert, Copy, Check, AlertTriangle } from 'lucide-svelte';
	import MarkdownRenderer from './MarkdownRenderer.svelte';

	let { message, showUsage = true }: { message: Message; showUsage?: boolean } = $props();

	const isUser = $derived(message.role === 'user');
	let copiedError = $state(false);
	let copiedMessage = $state(false);

	function copyErrorDetails() {
		if (!message.error) return;
		const details = `Error ID: ${message.error.errorId}\nCode: ${message.error.code}\nMessage: ${message.error.message}${message.error.details ? `\nDetails: ${message.error.details}` : ''}`;
		navigator.clipboard.writeText(details);
		copiedError = true;
		setTimeout(() => (copiedError = false), 2000);
	}

	function copyMessageContent() {
		navigator.clipboard.writeText(message.content);
		copiedMessage = true;
		setTimeout(() => (copiedMessage = false), 2000);
	}

	// User-friendly error code labels
	const errorCodeLabels: Record<string, string> = {
		VALIDATION_ERROR: 'Invalid Input',
		MODEL_ERROR: 'AI Model Error',
		RATE_LIMITED: 'Rate Limited',
		AUTH_FAILED: 'Authentication Failed',
		RAG_ERROR: 'Document Search Error',
		CONFIG_ERROR: 'Configuration Error',
		NETWORK_ERROR: 'Network Error',
		INTERNAL_ERROR: 'Server Error',
		UNKNOWN: 'Unknown Error'
	};

	// Actionable guidance for each error code
	const errorGuidance: Record<string, string> = {
		VALIDATION_ERROR: 'Please check your message and try again.',
		MODEL_ERROR: 'The AI model is temporarily unavailable. Please wait a moment and retry.',
		RATE_LIMITED: 'Too many requests. Please wait 30 seconds before trying again.',
		AUTH_FAILED: 'API key is invalid or expired. Check your DEEPSEEK_API_KEY environment variable.',
		RAG_ERROR: 'Document search failed. Try disabling RAG or re-indexing documents.',
		CONFIG_ERROR: 'Check your application settings and restart if needed.',
		NETWORK_ERROR: 'Connection issue. Check your internet and ensure the backend is running.',
		INTERNAL_ERROR: 'An unexpected error occurred. Check logs for details.',
		UNKNOWN: 'Something went wrong. Please try again or contact support.'
	};
</script>

<div class="flex gap-3 {isUser ? 'flex-row-reverse' : ''} mb-4 group">
	<!-- Avatar -->
	<div
		class="flex-shrink-0 w-8 h-8 rounded-full flex items-center justify-center {isUser
			? 'bg-blue-500'
			: 'bg-gray-600 dark:bg-slate-600'}"
	>
		{#if isUser}
			<User class="w-5 h-5 text-white" />
		{:else}
			<Bot class="w-5 h-5 text-white" />
		{/if}
	</div>

	<!-- Message content -->
	<div class="flex flex-col max-w-[75%] {isUser ? 'items-end' : 'items-start'}">
		<div class="relative">
			<div
				class="rounded-2xl px-4 py-2 {isUser
					? 'bg-blue-500 text-white rounded-br-sm'
					: 'bg-gray-100 dark:bg-slate-800 text-gray-900 dark:text-slate-100 rounded-bl-sm'}"
			>
				{#if isUser}
					<!-- User messages are plain text -->
					<p class="whitespace-pre-wrap break-words">{message.content}</p>
				{:else if message.content}
					<!-- Assistant messages use markdown rendering -->
					<MarkdownRenderer content={message.content} />
				{:else}
					<!-- Empty content (streaming not started yet) -->
					<span class="text-gray-400 dark:text-slate-500 italic">...</span>
				{/if}
			</div>

			<!-- Copy message button (shows on hover) -->
			{#if message.content}
				<button
					onclick={copyMessageContent}
					class="absolute -right-8 top-1 opacity-0 group-hover:opacity-100 transition-opacity p-1 rounded hover:bg-gray-200 dark:hover:bg-slate-700"
					title="Copy message"
				>
					{#if copiedMessage}
						<Check class="w-4 h-4 text-green-500" />
					{:else}
						<Copy class="w-4 h-4 text-gray-400 dark:text-slate-500" />
					{/if}
				</button>
			{/if}
		</div>

		<!-- Interrupted response indicator -->
		{#if message.interrupted && message.content}
			<div
				class="mt-2 flex items-center gap-2 text-xs text-amber-600 dark:text-amber-400 bg-amber-50 dark:bg-amber-950/30 border border-amber-200 dark:border-amber-800 rounded-lg px-3 py-2"
			>
				<AlertTriangle class="w-4 h-4 flex-shrink-0" />
				<span>Response interrupted - partial content shown above</span>
			</div>
		{/if}

		<!-- Error indicator -->
		{#if message.error}
			<div
				class="mt-2 p-3 bg-red-50 dark:bg-red-950/30 border border-red-200 dark:border-red-900 rounded-lg max-w-md"
			>
				<div class="flex items-start gap-2">
					<CircleAlert class="w-4 h-4 text-red-500 mt-0.5 flex-shrink-0" />
					<div class="flex-1 min-w-0">
						<div class="flex items-center gap-2 mb-1">
							<span
								class="text-xs font-medium px-1.5 py-0.5 rounded bg-red-100 dark:bg-red-900/50 text-red-700 dark:text-red-300"
							>
								{errorCodeLabels[message.error.code] || message.error.code}
							</span>
						</div>
						<p class="text-sm text-red-700 dark:text-red-300">{message.error.message}</p>
						{#if message.error.details}
							<p class="text-xs text-red-500 dark:text-red-400 mt-1 font-mono">
								{message.error.details}
							</p>
						{/if}
						<!-- Actionable guidance -->
						{#if errorGuidance[message.error.code]}
							<p class="text-xs text-red-600 dark:text-red-400 mt-2 italic">
								{errorGuidance[message.error.code]}
							</p>
						{/if}
						<div class="flex items-center justify-between mt-2 pt-2 border-t border-red-200 dark:border-red-900">
							<span class="text-xs text-red-400 dark:text-red-500 font-mono">
								ID: {message.error.errorId}
							</span>
							<button
								onclick={copyErrorDetails}
								class="flex items-center gap-1 text-xs text-red-500 hover:text-red-700 dark:text-red-400 dark:hover:text-red-300 transition-colors"
								title="Copy error details for support"
							>
								{#if copiedError}
									<Check class="w-3 h-3" />
									<span>Copied!</span>
								{:else}
									<Copy class="w-3 h-3" />
									<span>Copy</span>
								{/if}
							</button>
						</div>
					</div>
				</div>
			</div>
		{/if}

		<!-- Token usage -->
		{#if message.usage && !isUser && showUsage}
			<div class="text-xs text-gray-400 dark:text-slate-500 mt-1">
				{message.usage.inputTokens} in / {message.usage.outputTokens} out · ${message.usage.estimatedCost.toFixed(
					4
				)}
			</div>
		{/if}

		<!-- Timestamp -->
		<div class="text-xs text-gray-400 dark:text-slate-500 mt-1">
			{new Date(message.timestamp).toLocaleTimeString()}
		</div>
	</div>
</div>
