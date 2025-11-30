<script lang="ts">
	import { Send, LoaderCircle } from 'lucide-svelte';

	let {
		onSend,
		disabled = false
	}: {
		onSend: (message: string) => void;
		disabled?: boolean;
	} = $props();

	let inputValue = $state('');
	let textareaEl: HTMLTextAreaElement | null = $state(null);

	function handleSubmit(e: Event) {
		e.preventDefault();
		const trimmed = inputValue.trim();
		if (trimmed && !disabled) {
			onSend(trimmed);
			inputValue = '';
			// Reset textarea height
			if (textareaEl) {
				textareaEl.style.height = 'auto';
			}
		}
	}

	function handleKeydown(e: KeyboardEvent) {
		if (e.key === 'Enter' && !e.shiftKey) {
			e.preventDefault();
			handleSubmit(e);
		}
	}

	function autoResize() {
		if (textareaEl) {
			textareaEl.style.height = 'auto';
			textareaEl.style.height = Math.min(textareaEl.scrollHeight, 200) + 'px';
		}
	}
</script>

<form onsubmit={handleSubmit} class="flex gap-2 p-4 border-t border-gray-200 dark:border-slate-700 bg-white dark:bg-slate-900">
	<textarea
		bind:this={textareaEl}
		bind:value={inputValue}
		onkeydown={handleKeydown}
		oninput={autoResize}
		placeholder="Type your message..."
		rows="1"
		{disabled}
		class="flex-1 resize-none rounded-xl border border-gray-300 dark:border-slate-600 dark:bg-slate-800 dark:text-white px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent disabled:bg-gray-100 dark:disabled:bg-slate-700 dark:disabled:text-slate-400 disabled:cursor-not-allowed"
	></textarea>
	<button
		type="submit"
		disabled={disabled || !inputValue.trim()}
		class="flex-shrink-0 w-10 h-10 rounded-full bg-blue-500 text-white flex items-center justify-center hover:bg-blue-600 transition-colors disabled:bg-gray-300 disabled:cursor-not-allowed"
	>
		{#if disabled}
			<LoaderCircle class="w-5 h-5 animate-spin" />
		{:else}
			<Send class="w-5 h-5" />
		{/if}
	</button>
</form>
