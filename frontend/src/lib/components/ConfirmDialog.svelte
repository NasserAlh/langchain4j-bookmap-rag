<script lang="ts">
	import { AlertTriangle, X } from 'lucide-svelte';
	import { fade, scale } from 'svelte/transition';

	let {
		open = false,
		title = 'Confirm',
		message = 'Are you sure?',
		confirmText = 'Confirm',
		cancelText = 'Cancel',
		variant = 'danger' as 'danger' | 'warning' | 'info',
		onConfirm,
		onCancel
	}: {
		open: boolean;
		title?: string;
		message?: string;
		confirmText?: string;
		cancelText?: string;
		variant?: 'danger' | 'warning' | 'info';
		onConfirm: () => void;
		onCancel: () => void;
	} = $props();

	const variantStyles = {
		danger: {
			icon: 'text-red-500',
			button: 'bg-red-500 hover:bg-red-600 text-white'
		},
		warning: {
			icon: 'text-amber-500',
			button: 'bg-amber-500 hover:bg-amber-600 text-white'
		},
		info: {
			icon: 'text-blue-500',
			button: 'bg-blue-500 hover:bg-blue-600 text-white'
		}
	};

	const styles = $derived(variantStyles[variant]);

	function handleKeydown(e: KeyboardEvent) {
		if (e.key === 'Escape') {
			onCancel();
		}
	}
</script>

<svelte:window onkeydown={handleKeydown} />

{#if open}
	<!-- Backdrop -->
	<!-- svelte-ignore a11y_no_noninteractive_element_interactions -->
	<div
		class="fixed inset-0 bg-black/50 z-50 flex items-center justify-center p-4"
		transition:fade={{ duration: 150 }}
		onclick={onCancel}
		onkeydown={(e) => e.key === 'Escape' && onCancel()}
		role="dialog"
		aria-modal="true"
		aria-labelledby="dialog-title"
		tabindex="-1"
	>
		<!-- Dialog -->
		<!-- svelte-ignore a11y_no_static_element_interactions -->
		<div
			class="bg-white dark:bg-slate-800 rounded-xl shadow-xl max-w-md w-full p-6"
			transition:scale={{ duration: 150, start: 0.95 }}
			onclick={(e) => e.stopPropagation()}
			onkeydown={(e) => e.stopPropagation()}
		>
			<div class="flex items-start gap-4">
				<div class="flex-shrink-0 w-10 h-10 rounded-full bg-red-100 dark:bg-red-900/30 flex items-center justify-center">
					<AlertTriangle class="w-5 h-5 {styles.icon}" />
				</div>
				<div class="flex-1">
					<h3 id="dialog-title" class="text-lg font-semibold text-gray-900 dark:text-white">
						{title}
					</h3>
					<p class="mt-2 text-sm text-gray-600 dark:text-slate-400">
						{message}
					</p>
				</div>
				<button
					onclick={onCancel}
					class="flex-shrink-0 p-1 rounded-lg hover:bg-gray-100 dark:hover:bg-slate-700 transition-colors"
				>
					<X class="w-5 h-5 text-gray-400" />
				</button>
			</div>

			<div class="mt-6 flex gap-3 justify-end">
				<button
					onclick={onCancel}
					class="px-4 py-2 text-sm font-medium text-gray-700 dark:text-slate-300 bg-gray-100 dark:bg-slate-700 hover:bg-gray-200 dark:hover:bg-slate-600 rounded-lg transition-colors"
				>
					{cancelText}
				</button>
				<button
					onclick={onConfirm}
					class="px-4 py-2 text-sm font-medium rounded-lg transition-colors {styles.button}"
				>
					{confirmText}
				</button>
			</div>
		</div>
	</div>
{/if}
