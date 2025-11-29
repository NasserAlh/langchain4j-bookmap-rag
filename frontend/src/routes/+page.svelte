<script lang="ts">
	import { ChatWindow, ChatInput, Sidebar } from '$lib/components';
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
		sendMessage,
		sendStreamingMessage,
		getSettings,
		updateSettings as apiUpdateSettings,
		toggleRag,
		ingestDocuments,
		clearHistory,
		checkHealth
	} from '$lib/api';
	import type { Settings } from '$lib/types';
	import { onMount } from 'svelte';

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
				// Use defaults if settings fetch fails
			}
		} else {
			setConnectionStatus('error');
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
			await sendStreamingMessage(
				content,
				(token) => {
					appendToMessage(assistantId, token);
				},
				(usage) => {
					if (chatState.settings.showUsage) {
						setMessageUsage(assistantId, usage);
					}
					setStreaming(false);
					chatState.currentAssistantId = null;
				},
				(error) => {
					setMessageError(assistantId, error);
					setStreaming(false);
					chatState.currentAssistantId = null;
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
				setMessageError(assistantId, error instanceof Error ? error.message : 'Unknown error');
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
		} catch (error) {
			console.error('Failed to toggle RAG:', error);
		}
	}

	async function handleIngestDocuments() {
		try {
			const result = await ingestDocuments();
			// Could show a toast notification here
			console.log(`Indexed ${result.documentsIndexed} documents`);
		} catch (error) {
			console.error('Failed to ingest documents:', error);
		}
	}

	async function handleClearHistory() {
		try {
			await clearHistory();
			clearMessages();
		} catch (error) {
			console.error('Failed to clear history:', error);
			// Clear local messages anyway
			clearMessages();
		}
	}

	const isDisabled = $derived(chatState.isStreaming || chatState.isLoading);
</script>

<div class="h-screen flex bg-gray-50">
	<Sidebar
		settings={chatState.settings}
		connectionStatus={chatState.connectionStatus}
		onSettingsChange={handleSettingsChange}
		onToggleRag={handleToggleRag}
		onIngestDocuments={handleIngestDocuments}
		onClearHistory={handleClearHistory}
	/>

	<main class="flex-1 flex flex-col">
		<ChatWindow messages={chatState.messages} />
		<ChatInput onSend={handleSendMessage} disabled={isDisabled} />
	</main>
</div>
