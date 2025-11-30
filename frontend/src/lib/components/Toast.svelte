<script lang="ts">
	import { notificationState, dismiss } from '$lib/stores/notifications.svelte';
	import { X, CircleAlert, CheckCircle, AlertTriangle, Info } from 'lucide-svelte';
	import { fly, fade } from 'svelte/transition';

	const iconMap = {
		error: CircleAlert,
		success: CheckCircle,
		warning: AlertTriangle,
		info: Info
	};

	const colorMap = {
		error: {
			bg: 'bg-red-50 dark:bg-red-950/80',
			border: 'border-red-200 dark:border-red-800',
			icon: 'text-red-500',
			text: 'text-red-800 dark:text-red-200',
			close: 'hover:bg-red-100 dark:hover:bg-red-900/50'
		},
		success: {
			bg: 'bg-green-50 dark:bg-green-950/80',
			border: 'border-green-200 dark:border-green-800',
			icon: 'text-green-500',
			text: 'text-green-800 dark:text-green-200',
			close: 'hover:bg-green-100 dark:hover:bg-green-900/50'
		},
		warning: {
			bg: 'bg-yellow-50 dark:bg-yellow-950/80',
			border: 'border-yellow-200 dark:border-yellow-800',
			icon: 'text-yellow-500',
			text: 'text-yellow-800 dark:text-yellow-200',
			close: 'hover:bg-yellow-100 dark:hover:bg-yellow-900/50'
		},
		info: {
			bg: 'bg-blue-50 dark:bg-blue-950/80',
			border: 'border-blue-200 dark:border-blue-800',
			icon: 'text-blue-500',
			text: 'text-blue-800 dark:text-blue-200',
			close: 'hover:bg-blue-100 dark:hover:bg-blue-900/50'
		}
	};
</script>

<!-- Toast container - fixed position at top right -->
<div class="fixed top-4 right-4 z-50 flex flex-col gap-2 max-w-sm w-full pointer-events-none">
	{#each notificationState.notifications as notification (notification.id)}
		{@const Icon = iconMap[notification.type]}
		{@const colors = colorMap[notification.type]}
		<div
			class="pointer-events-auto {colors.bg} {colors.border} border rounded-lg shadow-lg p-4"
			in:fly={{ x: 300, duration: 300 }}
			out:fade={{ duration: 200 }}
		>
			<div class="flex items-start gap-3">
				<Icon class="w-5 h-5 {colors.icon} flex-shrink-0 mt-0.5" />
				<div class="flex-1 min-w-0">
					<p class="text-sm font-medium {colors.text}">{notification.message}</p>
					{#if notification.errorId}
						<p class="text-xs mt-1 opacity-70 {colors.text} font-mono">
							ID: {notification.errorId}
						</p>
					{/if}
				</div>
				<button
					onclick={() => dismiss(notification.id)}
					class="p-1 rounded {colors.close} transition-colors flex-shrink-0"
				>
					<X class="w-4 h-4 {colors.text}" />
				</button>
			</div>
		</div>
	{/each}
</div>
