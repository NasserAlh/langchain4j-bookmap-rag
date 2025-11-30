<script lang="ts">
	import { marked } from 'marked';
	import hljs from 'highlight.js';
	import { onMount } from 'svelte';
	import { Copy, Check } from 'lucide-svelte';

	let { content }: { content: string } = $props();

	let containerEl: HTMLDivElement | null = $state(null);
	let copiedIndex: number | null = $state(null);

	// Configure marked with highlight.js
	marked.setOptions({
		breaks: true,
		gfm: true
	});

	// Custom renderer for code blocks with copy button placeholder
	const renderer = new marked.Renderer();
	let codeBlockIndex = 0;

	renderer.code = function ({ text, lang }) {
		const language = lang && hljs.getLanguage(lang) ? lang : 'plaintext';
		const highlighted = hljs.highlight(text, { language }).value;
		const index = codeBlockIndex++;
		return `<div class="code-block-wrapper relative group my-3" data-code-index="${index}">
			<div class="code-header flex items-center justify-between px-3 py-1.5 bg-gray-800 dark:bg-slate-800 rounded-t-lg border-b border-gray-700">
				<span class="text-xs text-gray-400 font-mono">${language}</span>
				<button class="copy-code-btn flex items-center gap-1 text-xs text-gray-400 hover:text-white transition-colors" data-code="${encodeURIComponent(text)}">
					<svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z"></path></svg>
					<span class="copy-text">Copy</span>
				</button>
			</div>
			<pre class="!mt-0 !rounded-t-none overflow-x-auto bg-gray-900 dark:bg-slate-900 p-4 rounded-b-lg"><code class="hljs language-${language} text-sm">${highlighted}</code></pre>
		</div>`;
	};

	// Inline code
	renderer.codespan = function ({ text }) {
		return `<code class="px-1.5 py-0.5 bg-gray-200 dark:bg-slate-700 rounded text-sm font-mono text-pink-600 dark:text-pink-400">${text}</code>`;
	};

	const html = $derived.by(() => {
		codeBlockIndex = 0; // Reset for each render
		return marked.parse(content, { renderer }) as string;
	});

	// Handle copy button clicks
	function handleClick(e: MouseEvent) {
		const target = e.target as HTMLElement;
		const copyBtn = target.closest('.copy-code-btn') as HTMLButtonElement;
		if (copyBtn) {
			const code = decodeURIComponent(copyBtn.dataset.code || '');
			navigator.clipboard.writeText(code).then(() => {
				const textEl = copyBtn.querySelector('.copy-text');
				if (textEl) {
					textEl.textContent = 'Copied!';
					setTimeout(() => {
						textEl.textContent = 'Copy';
					}, 2000);
				}
			});
		}
	}

	onMount(() => {
		if (containerEl) {
			containerEl.addEventListener('click', handleClick);
			return () => containerEl?.removeEventListener('click', handleClick);
		}
	});
</script>

<div
	bind:this={containerEl}
	class="markdown-content prose prose-sm dark:prose-invert max-w-none
		prose-headings:font-semibold prose-headings:text-gray-900 dark:prose-headings:text-white
		prose-p:text-gray-800 dark:prose-p:text-slate-200
		prose-a:text-blue-600 dark:prose-a:text-blue-400 prose-a:no-underline hover:prose-a:underline
		prose-strong:text-gray-900 dark:prose-strong:text-white
		prose-ul:list-disc prose-ol:list-decimal
		prose-li:text-gray-800 dark:prose-li:text-slate-200
		prose-blockquote:border-l-4 prose-blockquote:border-gray-300 dark:prose-blockquote:border-slate-600
		prose-blockquote:pl-4 prose-blockquote:italic prose-blockquote:text-gray-600 dark:prose-blockquote:text-slate-400
		prose-hr:border-gray-200 dark:prose-hr:border-slate-700"
>
	{@html html}
</div>

<style>
	/* Import highlight.js theme */
	:global(.hljs) {
		background: transparent !important;
	}
</style>
