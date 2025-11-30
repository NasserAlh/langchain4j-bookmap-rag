<script lang="ts">
	import { Bot } from 'lucide-svelte';
</script>

<div class="flex gap-3 mb-4">
	<!-- Avatar -->
	<div class="flex-shrink-0 w-8 h-8 rounded-full flex items-center justify-center bg-gray-600 dark:bg-slate-600">
		<Bot class="w-5 h-5 text-white" />
	</div>

	<!-- Typing dots -->
	<div class="flex items-center gap-1 px-4 py-3 bg-gray-100 dark:bg-slate-800 rounded-2xl rounded-bl-sm">
		<span class="w-2 h-2 bg-gray-400 dark:bg-slate-500 rounded-full animate-bounce" style="animation-delay: 0ms;"></span>
		<span class="w-2 h-2 bg-gray-400 dark:bg-slate-500 rounded-full animate-bounce" style="animation-delay: 150ms;"></span>
		<span class="w-2 h-2 bg-gray-400 dark:bg-slate-500 rounded-full animate-bounce" style="animation-delay: 300ms;"></span>
	</div>
</div>
