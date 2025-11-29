<script lang="ts">
	import type { Message } from '$lib/types';
	import { User, Bot, CircleAlert } from 'lucide-svelte';

	let { message }: { message: Message } = $props();

	const isUser = $derived(message.role === 'user');
</script>

<div class="flex gap-3 {isUser ? 'flex-row-reverse' : ''} mb-4">
	<!-- Avatar -->
	<div
		class="flex-shrink-0 w-8 h-8 rounded-full flex items-center justify-center {isUser
			? 'bg-blue-500'
			: 'bg-gray-600'}"
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
				: 'bg-gray-100 text-gray-900 rounded-bl-sm'}"
		>
			<p class="whitespace-pre-wrap break-words">{message.content}</p>
		</div>

		<!-- Error indicator -->
		{#if message.error}
			<div class="flex items-center gap-1 mt-1 text-red-500 text-xs">
				<CircleAlert class="w-3 h-3" />
				<span>{message.error}</span>
			</div>
		{/if}

		<!-- Token usage -->
		{#if message.usage && !isUser}
			<div class="text-xs text-gray-400 mt-1">
				{message.usage.inputTokens} in / {message.usage.outputTokens} out · ${message.usage.estimatedCost.toFixed(
					4
				)}
			</div>
		{/if}

		<!-- Timestamp -->
		<div class="text-xs text-gray-400 mt-1">
			{new Date(message.timestamp).toLocaleTimeString()}
		</div>
	</div>
</div>
