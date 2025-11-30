<script lang="ts">
	import type { Settings, ConnectionStatus } from '$lib/types';
	import {
		Settings as SettingsIcon,
		Database,
		Trash2,
		RefreshCw,
		LoaderCircle,
		CircleCheck,
		CircleX,
		CircleAlert,
		Moon,
		Sun,
		PanelLeftClose,
		PanelLeft
	} from 'lucide-svelte';
	import { themeStore } from '$lib/stores/theme.svelte';
	import ConfirmDialog from './ConfirmDialog.svelte';
	import { fly } from 'svelte/transition';

	let {
		settings,
		connectionStatus,
		onSettingsChange,
		onToggleRag,
		onIngestDocuments,
		onClearHistory,
		collapsed = false,
		onToggleCollapse
	}: {
		settings: Settings;
		connectionStatus: ConnectionStatus;
		onSettingsChange: (settings: Partial<Settings>) => void;
		onToggleRag: () => void;
		onIngestDocuments: () => Promise<void> | void;
		onClearHistory: () => void;
		collapsed?: boolean;
		onToggleCollapse?: () => void;
	} = $props();

	let isIngesting = $state(false);
	let showClearConfirm = $state(false);

	// Temperature presets
	const temperaturePresets = [
		{ label: 'Precise', value: 0.0, description: 'Deterministic, factual' },
		{ label: 'Balanced', value: 0.7, description: 'Default, versatile' },
		{ label: 'Creative', value: 1.2, description: 'Imaginative, varied' }
	];

	function handleThemeToggle() {
		themeStore.toggle();
	}

	async function handleIngest() {
		isIngesting = true;
		try {
			await onIngestDocuments();
		} finally {
			isIngesting = false;
		}
	}

	function handleClearClick() {
		showClearConfirm = true;
	}

	function handleClearConfirm() {
		showClearConfirm = false;
		onClearHistory();
	}

	function handleClearCancel() {
		showClearConfirm = false;
	}

	function setTemperaturePreset(value: number) {
		onSettingsChange({ temperature: value });
	}

	const statusColor = $derived(
		connectionStatus === 'connected'
			? 'text-green-500'
			: connectionStatus === 'error'
				? 'text-red-500'
				: connectionStatus === 'connecting' || connectionStatus === 'reconnecting'
					? 'text-yellow-500'
					: 'text-gray-400'
	);

	const StatusIcon = $derived(
		connectionStatus === 'connected'
			? CircleCheck
			: connectionStatus === 'error'
				? CircleX
				: CircleAlert
	);

	// Find closest preset for highlighting
	const activePreset = $derived(
		temperaturePresets.find((p) => Math.abs(p.value - settings.temperature) < 0.05)?.value ?? null
	);
</script>

<!-- Confirm Dialog -->
<ConfirmDialog
	open={showClearConfirm}
	title="Clear Chat History"
	message="This will permanently delete all messages in this conversation. This action cannot be undone."
	confirmText="Clear History"
	cancelText="Keep Messages"
	variant="danger"
	onConfirm={handleClearConfirm}
	onCancel={handleClearCancel}
/>

<aside class="w-72 bg-white dark:bg-slate-900 border-r border-gray-200 dark:border-slate-700 flex flex-col">
	<!-- Header -->
	<div class="p-4 border-b border-gray-200 dark:border-slate-700">
		<div class="flex items-center justify-between">
			<div class="flex items-center gap-2">
				<SettingsIcon class="w-5 h-5 text-gray-600 dark:text-slate-400" />
				<h2 class="font-semibold text-gray-800 dark:text-white">Settings</h2>
			</div>
			<button
				onclick={handleThemeToggle}
				class="p-1 rounded-lg hover:bg-gray-100 dark:hover:bg-slate-800 transition-colors"
				aria-label="Toggle dark mode"
			>
				{#if themeStore.darkMode}
					<Sun class="w-5 h-5 text-yellow-500" />
				{:else}
					<Moon class="w-5 h-5 text-gray-600" />
				{/if}
			</button>
		</div>
		<!-- Connection status -->
		<div class="flex items-center gap-2 mt-2 text-sm {statusColor}">
			<StatusIcon class="w-4 h-4" />
			<span class="capitalize">{connectionStatus}</span>
		</div>
	</div>

	<!-- Settings content -->
	<div class="flex-1 overflow-y-auto p-4 space-y-6">
		<!-- Model selection -->
		<div>
			<label for="model-select" class="block text-sm font-medium text-gray-700 dark:text-slate-300 mb-2">Model</label>
			<select
				id="model-select"
				value={settings.model}
				onchange={(e) => {
					const value = e.currentTarget.value as Settings['model'];
					onSettingsChange({ model: value });
				}}
				class="w-full rounded-lg border border-gray-300 dark:border-slate-600 dark:bg-slate-800 dark:text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
			>
				<option value="deepseek-chat">DeepSeek Chat</option>
				<option value="deepseek-reasoner">DeepSeek Reasoner</option>
			</select>
		</div>

		<!-- Temperature slider with presets -->
		<div>
			<label for="temperature-slider" class="block text-sm font-medium text-gray-700 dark:text-slate-300 mb-2">
				Temperature: {settings.temperature.toFixed(1)}
			</label>

			<!-- Preset buttons -->
			<div class="flex gap-1 mb-3">
				{#each temperaturePresets as preset}
					<button
						onclick={() => setTemperaturePreset(preset.value)}
						class="flex-1 px-2 py-1.5 text-xs font-medium rounded-lg transition-all duration-200
							{activePreset === preset.value
								? 'bg-blue-500 text-white shadow-sm'
								: 'bg-gray-100 dark:bg-slate-700 text-gray-600 dark:text-slate-400 hover:bg-gray-200 dark:hover:bg-slate-600'}"
						title={preset.description}
					>
						{preset.label}
					</button>
				{/each}
			</div>

			<input
				id="temperature-slider"
				type="range"
				min="0"
				max="1.5"
				step="0.1"
				value={settings.temperature}
				oninput={(e) => onSettingsChange({ temperature: parseFloat(e.currentTarget.value) })}
				class="w-full h-2 bg-gray-200 dark:bg-slate-700 rounded-lg appearance-none cursor-pointer accent-blue-500"
			/>
			<div class="flex justify-between text-xs text-gray-400 dark:text-slate-500 mt-1">
				<span>0.0</span>
				<span>1.5</span>
			</div>
		</div>

		<!-- Toggles -->
		<div class="space-y-3">
			<!-- Streaming toggle -->
			<label class="flex items-center justify-between cursor-pointer">
				<span class="text-sm text-gray-700 dark:text-slate-300">Streaming</span>
				<input
					type="checkbox"
					checked={settings.streamingEnabled}
					onchange={() => onSettingsChange({ streamingEnabled: !settings.streamingEnabled })}
					class="w-10 h-5 rounded-full appearance-none bg-gray-300 checked:bg-blue-500 transition-colors cursor-pointer relative after:content-[''] after:absolute after:top-0.5 after:left-0.5 after:w-4 after:h-4 after:bg-white after:rounded-full after:transition-transform checked:after:translate-x-5"
				/>
			</label>

			<!-- Show usage toggle -->
			<label class="flex items-center justify-between cursor-pointer">
				<span class="text-sm text-gray-700 dark:text-slate-300">Show token usage</span>
				<input
					type="checkbox"
					checked={settings.showUsage}
					onchange={() => onSettingsChange({ showUsage: !settings.showUsage })}
					class="w-10 h-5 rounded-full appearance-none bg-gray-300 checked:bg-blue-500 transition-colors cursor-pointer relative after:content-[''] after:absolute after:top-0.5 after:left-0.5 after:w-4 after:h-4 after:bg-white after:rounded-full after:transition-transform checked:after:translate-x-5"
				/>
			</label>
		</div>

		<!-- RAG Section -->
		<div class="pt-4 border-t border-gray-200 dark:border-slate-700">
			<div class="flex items-center gap-2 mb-4">
				<Database class="w-5 h-5 text-gray-600 dark:text-slate-400" />
				<h3 class="font-medium text-gray-800 dark:text-white">RAG Mode</h3>
			</div>

			<label class="flex items-center justify-between cursor-pointer mb-4">
				<span class="text-sm text-gray-700 dark:text-slate-300">Enable RAG</span>
				<input
					type="checkbox"
					checked={settings.ragEnabled}
					onchange={onToggleRag}
					class="w-10 h-5 rounded-full appearance-none bg-gray-300 checked:bg-green-500 transition-colors cursor-pointer relative after:content-[''] after:absolute after:top-0.5 after:left-0.5 after:w-4 after:h-4 after:bg-white after:rounded-full after:transition-transform checked:after:translate-x-5"
				/>
			</label>

			<button
				onclick={handleIngest}
				disabled={isIngesting}
				class="w-full flex items-center justify-center gap-2 px-4 py-2 bg-gray-100 hover:bg-gray-200 dark:bg-slate-800 dark:hover:bg-slate-700 rounded-lg text-sm text-gray-700 dark:text-slate-300 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
			>
				{#if isIngesting}
					<LoaderCircle class="w-4 h-4 animate-spin" />
					<span>Ingesting...</span>
				{:else}
					<RefreshCw class="w-4 h-4" />
					<span>Ingest Documents</span>
				{/if}
			</button>
		</div>
	</div>

	<!-- Footer actions -->
	<div class="p-4 border-t border-gray-200 dark:border-slate-700">
		<button
			onclick={handleClearClick}
			class="w-full flex items-center justify-center gap-2 px-4 py-2 bg-red-50 hover:bg-red-100 dark:bg-red-950 dark:hover:bg-red-900 rounded-lg text-sm text-red-600 dark:text-red-400 transition-colors"
		>
			<Trash2 class="w-4 h-4" />
			<span>Clear Chat History</span>
		</button>
	</div>
</aside>
