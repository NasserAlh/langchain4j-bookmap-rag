<script lang="ts">
	import type { Message } from '$lib/types';
	import { User, Bot, CircleAlert, Copy, Check } from 'lucide-svelte';

	let { message }: { message: Message } = $props();

	const isUser = $derived(message.role === 'user');
	let copied = $state(false);

	function copyErrorDetails() {
		if (!message.error) return;
		const details = `Error ID: ${message.error.errorId}\nCode: ${message.error.code}\nMessage: ${message.error.message}${message.error.details ? `\nDetails: ${message.error.details}` : ''}`;
		navigator.clipboard.writeText(details);
		copied = true;
		setTimeout(() => (copied = false), 2000);
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
</script>

<div class="flex gap-3 {isUser ? 'flex-row-reverse' : ''} mb-4">
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
		<div
			class="rounded-2xl px-4 py-2 {isUser
				? 'bg-blue-500 text-white rounded-br-sm'
				: 'bg-gray-100 dark:bg-slate-800 text-gray-900 dark:text-slate-100 rounded-bl-sm'}"
		>
			<p class="whitespace-pre-wrap break-words">{message.content}</p>
		</div>

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
						<div class="flex items-center justify-between mt-2 pt-2 border-t border-red-200 dark:border-red-900">
							<span class="text-xs text-red-400 dark:text-red-500 font-mono">
								ID: {message.error.errorId}
							</span>
							<button
								onclick={copyErrorDetails}
								class="flex items-center gap-1 text-xs text-red-500 hover:text-red-700 dark:text-red-400 dark:hover:text-red-300 transition-colors"
								title="Copy error details for support"
							>
								{#if copied}
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
		{#if message.usage && !isUser}
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
