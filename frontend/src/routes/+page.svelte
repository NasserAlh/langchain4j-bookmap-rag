<script lang="ts">
	import { ChatWindow, ChatInput, Sidebar, Toast } from '$lib/components';
	import {
		chatState,
		addMessage,
		appendToMessage,
		setMessageUsage,
		setMessageError,
		clearMessages,
		updateSettings,
		setConnectionStatus,
		setStreaming,
		setLoading
	} from '$lib/stores/chat.svelte';
	import {
		notifyError,
		notifySuccess,
		notifyWarning,
		notifyInfo
	} from '$lib/stores/notifications.svelte';
	import {
		sendMessage,
		sendStreamingMessage,
		getSettings,
		updateSettings as apiUpdateSettings,
		toggleRag,
		ingestDocuments,
		clearHistory,
		checkHealth
	} from '$lib/api';
	import type { Settings, ApiError } from '$lib/types';
	import { ChatApiError } from '$lib/api';
	import { onMount } from 'svelte';
	import { PanelLeft } from 'lucide-svelte';

	// Sidebar collapse state
	let sidebarCollapsed = $state(false);

	function toggleSidebar() {
		sidebarCollapsed = !sidebarCollapsed;
	}

	// Check backend health on mount
	onMount(async () => {
		setConnectionStatus('connecting');
		const healthy = await checkHealth();
		if (healthy) {
			setConnectionStatus('connected');
			// Load initial settings
			try {
				const settings = await getSettings();
				updateSettings(settings);
			} catch {
				notifyWarning('Could not load settings. Using defaults.');
			}
		} else {
			setConnectionStatus('error');
			notifyError('Backend not reachable. Is the server running on port 8080?');
		}
	});

	async function handleSendMessage(content: string) {
		// Add user message
		addMessage('user', content);

		// Create placeholder for assistant response
		const assistantId = addMessage('assistant', '');
		chatState.currentAssistantId = assistantId;

		if (chatState.settings.streamingEnabled) {
			// Streaming mode
			setStreaming(true);
			setConnectionStatus('connecting');
			await sendStreamingMessage(
				content,
				(token) => {
					setConnectionStatus('connected');
					appendToMessage(assistantId, token);
				},
				(usage) => {
					if (chatState.settings.showUsage) {
						setMessageUsage(assistantId, usage);
					}
					setStreaming(false);
					setConnectionStatus('connected');
					chatState.currentAssistantId = null;
				},
				(error, partialContent) => {
					// Check if we have partial content (interrupted response)
					const hasPartialContent = partialContent && partialContent.length > 0;
					setMessageError(assistantId, error, hasPartialContent);
					setStreaming(false);
					setConnectionStatus('error');
					chatState.currentAssistantId = null;

					// Show toast for the error
					if (hasPartialContent) {
						notifyWarning('Response interrupted. Partial content preserved.');
					} else {
						notifyError(error.message, error.errorId, error.code);
					}
				},
				(attempt, maxRetries) => {
					// Retry notification
					setConnectionStatus('reconnecting');
					notifyInfo(`Connection lost. Retrying (${attempt}/${maxRetries})...`);
				}
			);
		} else {
			// Non-streaming mode
			setLoading(true);
			try {
				const response = await sendMessage(content);
				appendToMessage(assistantId, response.response);
				if (chatState.settings.showUsage) {
					setMessageUsage(assistantId, response.usage);
				}
			} catch (error) {
				if (error instanceof ChatApiError) {
					setMessageError(assistantId, error.apiError);
				} else {
					setMessageError(assistantId, {
						errorId: 'unknown',
						code: 'UNKNOWN',
						message: error instanceof Error ? error.message : 'Unknown error',
						timestamp: Date.now()
					});
				}
			} finally {
				setLoading(false);
				chatState.currentAssistantId = null;
			}
		}
	}

	async function handleSettingsChange(newSettings: Partial<Settings>) {
		updateSettings(newSettings);
		try {
			await apiUpdateSettings(newSettings);
		} catch {
			// Silently fail - local state is already updated
		}
	}

	async function handleToggleRag() {
		try {
			const result = await toggleRag();
			updateSettings({ ragEnabled: result.ragEnabled });
			notifyInfo(result.ragEnabled ? 'RAG enabled' : 'RAG disabled');
		} catch (error) {
			notifyError('Failed to toggle RAG');
		}
	}

	async function handleIngestDocuments() {
		notifyInfo('Indexing documents...');
		try {
			const result = await ingestDocuments();
			notifySuccess(`Indexed ${result.documentsIndexed} documents`);
		} catch (error) {
			notifyError('Failed to ingest documents');
		}
	}

	async function handleClearHistory() {
		try {
			await clearHistory();
			clearMessages();
			notifySuccess('Chat history cleared');
		} catch (error) {
			notifyWarning('Backend clear failed, local history cleared');
			clearMessages();
		}
	}

	const isDisabled = $derived(chatState.isStreaming || chatState.isLoading);
</script>

<!-- Toast notifications -->
<Toast />

<div class="h-screen flex bg-gray-50 dark:bg-slate-950">
	<!-- Mobile sidebar toggle button -->
	<button
		onclick={toggleSidebar}
		class="fixed top-4 left-4 z-40 p-2 rounded-lg bg-white dark:bg-slate-800 shadow-lg border border-gray-200 dark:border-slate-700 lg:hidden transition-transform duration-200"
		class:translate-x-64={!sidebarCollapsed}
		aria-label="Toggle sidebar"
	>
		<PanelLeft class="w-5 h-5 text-gray-600 dark:text-slate-400" />
	</button>

	<!-- Backdrop for mobile -->
	{#if !sidebarCollapsed}
		<button
			class="fixed inset-0 bg-black/30 z-30 lg:hidden"
			onclick={toggleSidebar}
			aria-label="Close sidebar"
		></button>
	{/if}

	<!-- Sidebar with mobile slide -->
	<div
		class="fixed lg:relative z-40 h-full transition-transform duration-300 ease-in-out lg:translate-x-0"
		class:-translate-x-full={sidebarCollapsed}
	>
		<Sidebar
			settings={chatState.settings}
			connectionStatus={chatState.connectionStatus}
			onSettingsChange={handleSettingsChange}
			onToggleRag={handleToggleRag}
			onIngestDocuments={handleIngestDocuments}
			onClearHistory={handleClearHistory}
			collapsed={sidebarCollapsed}
			onToggleCollapse={toggleSidebar}
		/>
	</div>

	<main class="flex-1 flex flex-col min-w-0">
		<ChatWindow
			messages={chatState.messages}
			isStreaming={chatState.isStreaming}
			showUsage={chatState.settings.showUsage}
			onSendSuggestion={handleSendMessage}
		/>
		<ChatInput onSend={handleSendMessage} disabled={isDisabled} />
	</main>
</div>
