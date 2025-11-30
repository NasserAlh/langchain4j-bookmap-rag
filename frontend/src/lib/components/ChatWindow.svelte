<script lang="ts">
	import type { Message } from '$lib/types';
	import ChatMessage from './ChatMessage.svelte';
	import TypingIndicator from './TypingIndicator.svelte';
	import { MessageSquare, Sparkles, Zap, BookOpen } from 'lucide-svelte';
	import { fade, fly } from 'svelte/transition';

	let {
		messages,
		isStreaming = false,
		showUsage = true,
		onSendSuggestion
	}: {
		messages: Message[];
		isStreaming?: boolean;
		showUsage?: boolean;
		onSendSuggestion?: (text: string) => void;
	} = $props();

	// Suggestion prompts for empty state
	const suggestions = [
		{ icon: Sparkles, text: 'Explain quantum computing in simple terms', color: 'text-purple-500' },
		{ icon: Zap, text: 'Write a Python function to sort a list', color: 'text-amber-500' },
		{ icon: BookOpen, text: 'Summarize the key points of machine learning', color: 'text-blue-500' }
	];

	let containerEl: HTMLDivElement | null = $state(null);

	// Track message content changes for auto-scroll during streaming
	const lastMessageContent = $derived(
		messages.length > 0 ? messages[messages.length - 1].content : ''
	);

	// Auto-scroll to bottom when new messages arrive or content updates
	$effect(() => {
		// Access lastMessageContent to trigger effect on content changes
		lastMessageContent;

		if (messages.length && containerEl) {
			// Small delay to ensure DOM has updated
			setTimeout(() => {
				containerEl?.scrollTo({
					top: containerEl.scrollHeight,
					behavior: 'smooth'
				});
			}, 50);
		}
	});

	// Also track streaming state for typing indicator scroll
	$effect(() => {
		if (isStreaming && containerEl) {
			setTimeout(() => {
				containerEl?.scrollTo({
					top: containerEl.scrollHeight,
					behavior: 'smooth'
				});
			}, 50);
		}
	});
</script>

<div bind:this={containerEl} class="flex-1 overflow-y-auto p-4 bg-gray-50 dark:bg-slate-950">
	{#if messages.length === 0 && !isStreaming}
		<div
			class="h-full flex flex-col items-center justify-center"
			in:fade={{ duration: 300 }}
		>
			<!-- Logo/Icon area -->
			<div class="relative mb-6">
				<div class="w-20 h-20 rounded-2xl bg-gradient-to-br from-blue-500 to-purple-600 flex items-center justify-center shadow-lg">
					<MessageSquare class="w-10 h-10 text-white" />
				</div>
				<div class="absolute -bottom-1 -right-1 w-6 h-6 rounded-full bg-green-500 border-2 border-white dark:border-slate-950 flex items-center justify-center">
					<Sparkles class="w-3 h-3 text-white" />
				</div>
			</div>

			<h2 class="text-xl font-semibold text-gray-800 dark:text-white mb-2">
				Welcome to DeepSeek Chat
			</h2>
			<p class="text-gray-500 dark:text-slate-400 text-center max-w-md mb-8">
				Ask me anything! I can help with coding, writing, analysis, and more.
			</p>

			<!-- Suggestion cards -->
			<div class="w-full max-w-lg space-y-2">
				<p class="text-xs text-gray-400 dark:text-slate-500 uppercase tracking-wide mb-3 text-center">
					Try asking
				</p>
				{#each suggestions as suggestion, i}
					{@const Icon = suggestion.icon}
					<button
						class="w-full flex items-center gap-3 p-3 rounded-xl bg-white dark:bg-slate-800 border border-gray-200 dark:border-slate-700 hover:border-blue-300 dark:hover:border-blue-600 hover:shadow-md transition-all duration-200 text-left group"
						in:fly={{ y: 20, delay: i * 100, duration: 300 }}
						onclick={() => onSendSuggestion?.(suggestion.text)}
					>
						<div class="flex-shrink-0 w-8 h-8 rounded-lg bg-gray-100 dark:bg-slate-700 flex items-center justify-center group-hover:scale-110 transition-transform">
							<Icon class="w-4 h-4 {suggestion.color}" />
						</div>
						<span class="text-sm text-gray-700 dark:text-slate-300 group-hover:text-gray-900 dark:group-hover:text-white transition-colors">
							{suggestion.text}
						</span>
					</button>
				{/each}
			</div>
		</div>
	{:else}
		{#each messages as message (message.id)}
			<div in:fly={{ y: 10, duration: 200 }}>
				<ChatMessage {message} {showUsage} />
			</div>
		{/each}

		<!-- Show typing indicator when streaming and last message has no content yet -->
		{#if isStreaming && messages.length > 0 && messages[messages.length - 1].role === 'assistant' && !messages[messages.length - 1].content}
			<div in:fade={{ duration: 150 }}>
				<TypingIndicator />
			</div>
		{/if}
	{/if}
</div>
