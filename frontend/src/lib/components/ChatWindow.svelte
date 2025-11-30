<script lang="ts">
	import type { Message } from '$lib/types';
	import ChatMessage from './ChatMessage.svelte';
	import { MessageSquare } from 'lucide-svelte';

	let { messages }: { messages: Message[] } = $props();

	let containerEl: HTMLDivElement | null = $state(null);

	// Auto-scroll to bottom when new messages arrive
	$effect(() => {
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
</script>

<div bind:this={containerEl} class="flex-1 overflow-y-auto p-4 bg-gray-50 dark:bg-slate-950">
	{#if messages.length === 0}
		<div class="h-full flex flex-col items-center justify-center text-gray-400 dark:text-slate-500">
			<MessageSquare class="w-16 h-16 mb-4 opacity-50" />
			<p class="text-lg font-medium">Start a conversation</p>
			<p class="text-sm">Send a message to begin chatting with DeepSeek</p>
		</div>
	{:else}
		{#each messages as message (message.id)}
			<ChatMessage {message} />
		{/each}
	{/if}
</div>
