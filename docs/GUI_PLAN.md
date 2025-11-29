# GUI Plan: Svelte 5 Frontend for LangChain4j Chatbot

## Overview

This document outlines the plan to create a modern web GUI for the existing Java 21 LangChain4j chatbot using **Svelte 5 (5.45.2)** and **SvelteKit**. The architecture separates concerns between a lightweight Java HTTP backend and a reactive Svelte frontend.

## Architecture

```
┌─────────────────────────┐     HTTP/SSE      ┌─────────────────────────┐
│   SvelteKit 5 Frontend  │ ◄──────────────► │   Java Backend (Javalin)│
│   (localhost:5173)      │                   │   (localhost:8080)      │
└─────────────────────────┘                   └─────────────────────────┘
```

### Technology Stack

| Layer | Technology | Version |
|-------|------------|---------|
| Frontend Framework | Svelte | 5.45.2 |
| Frontend Meta-framework | SvelteKit | Latest |
| Styling | Tailwind CSS | 4.x |
| SSE Client | sveltekit-sse | Latest |
| Icons | lucide-svelte | Latest |
| Backend HTTP Server | Javalin | 6.4.0 |
| JSON Serialization | Jackson | 2.17.0 |

---

## Phase 1: Java Backend HTTP API

### Objective

Expose the existing `DeepSeekChatbot` functionality via REST endpoints and Server-Sent Events (SSE) for streaming.

### Dependencies to Add (pom.xml)

```xml
<!-- Javalin HTTP Server -->
<dependency>
    <groupId>io.javalin</groupId>
    <artifactId>javalin</artifactId>
    <version>6.4.0</version>
</dependency>

<!-- Jackson for JSON -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

### API Endpoints

| Method | Path | Description | Request Body | Response |
|--------|------|-------------|--------------|----------|
| `POST` | `/api/chat` | Send message (non-streaming) | `{ "message": "..." }` | `{ "response": "...", "usage": {...} }` |
| `POST` | `/api/chat/stream` | Send message (SSE streaming) | `{ "message": "..." }` | SSE: `event: token`, `event: done` |
| `GET` | `/api/settings` | Get current settings | - | `{ "model": "...", "temperature": 0.7, ... }` |
| `POST` | `/api/settings` | Update settings | `{ "model": "...", "temperature": 0.7 }` | `{ "success": true }` |
| `POST` | `/api/rag/toggle` | Toggle RAG mode | - | `{ "ragEnabled": true/false }` |
| `POST` | `/api/rag/ingest` | Trigger document ingestion | - | `{ "documentsIndexed": 42 }` |
| `POST` | `/api/history/clear` | Clear chat memory | - | `{ "success": true }` |
| `GET` | `/api/health` | Health check | - | `{ "status": "ok" }` |

### New Java Classes

```
src/main/java/com/example/chatbot/
├── DeepSeekChatbot.java          (existing - refactor to remove main())
├── server/
│   ├── ChatbotServer.java        (Javalin app entry point)
│   ├── ChatController.java       (Chat endpoints)
│   ├── SettingsController.java   (Settings endpoints)
│   └── dto/
│       ├── ChatRequest.java
│       ├── ChatResponse.java
│       └── SettingsDto.java
```

### SSE Streaming Implementation

```java
// ChatController.java
app.post("/api/chat/stream", ctx -> {
    ctx.contentType("text/event-stream");
    ctx.header("Cache-Control", "no-cache");
    ctx.header("Connection", "keep-alive");

    ChatRequest request = ctx.bodyAsClass(ChatRequest.class);

    chatbot.chatStreamingWithCallback(request.getMessage(),
        token -> ctx.sse().sendEvent("token", token),
        response -> {
            ctx.sse().sendEvent("done", response.usage());
            ctx.sse().close();
        }
    );
});
```

### CORS Configuration

```java
app.before(ctx -> {
    ctx.header("Access-Control-Allow-Origin", "http://localhost:5173");
    ctx.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    ctx.header("Access-Control-Allow-Headers", "Content-Type");
});

app.options("/*", ctx -> ctx.status(200));
```

---

## Phase 2: SvelteKit 5 Frontend Setup

### Project Initialization

```bash
# Create SvelteKit project
npx sv create frontend

# Options to select:
# - SvelteKit minimal
# - TypeScript
# - Tailwind CSS (via sv add tailwindcss)
# - Prettier, ESLint

cd frontend
npm install

# Additional dependencies
npm install sveltekit-sse
npm install lucide-svelte
```

### Project Structure

```
frontend/
├── src/
│   ├── lib/
│   │   ├── components/
│   │   │   ├── ChatMessage.svelte      # Individual message bubble
│   │   │   ├── ChatInput.svelte        # Input field + send button
│   │   │   ├── ChatWindow.svelte       # Message list container
│   │   │   ├── Sidebar.svelte          # Settings & controls
│   │   │   ├── Settings.svelte         # Model/temp configuration
│   │   │   ├── RagStatus.svelte        # RAG indicator
│   │   │   └── TokenUsage.svelte       # Usage display
│   │   ├── stores/
│   │   │   └── chat.svelte.ts          # Shared reactive state (runes)
│   │   ├── types/
│   │   │   └── index.ts                # TypeScript interfaces
│   │   └── api.ts                      # Backend API client
│   ├── routes/
│   │   ├── +layout.svelte              # App shell
│   │   └── +page.svelte                # Main chat page
│   └── app.css                         # Global styles
├── static/
│   └── favicon.png
├── svelte.config.js
├── tailwind.config.js
└── package.json
```

---

## Phase 3: Svelte 5 Implementation

### TypeScript Interfaces (`src/lib/types/index.ts`)

```typescript
export interface Message {
  id: string;
  role: 'user' | 'assistant';
  content: string;
  timestamp: number;
  usage?: TokenUsage;
}

export interface TokenUsage {
  inputTokens: number;
  outputTokens: number;
  estimatedCost: number;
}

export interface Settings {
  model: 'deepseek-chat' | 'deepseek-reasoner';
  temperature: number;
  ragEnabled: boolean;
  streamingEnabled: boolean;
  showUsage: boolean;
}
```

### Shared State with Svelte 5 Runes (`src/lib/stores/chat.svelte.ts`)

```typescript
import type { Message, Settings } from '$lib/types';

// Reactive state using $state rune
export const chatState = $state({
  messages: [] as Message[],
  isStreaming: false,
  isLoading: false,
  error: null as string | null,
  settings: {
    model: 'deepseek-chat',
    temperature: 0.7,
    ragEnabled: false,
    streamingEnabled: true,
    showUsage: true
  } as Settings
});

// Helper functions
export function addMessage(role: 'user' | 'assistant', content: string): string {
  const id = crypto.randomUUID();
  chatState.messages.push({
    id,
    role,
    content,
    timestamp: Date.now()
  });
  return id;
}

export function updateMessage(id: string, content: string) {
  const msg = chatState.messages.find(m => m.id === id);
  if (msg) msg.content = content;
}

export function setMessageUsage(id: string, usage: TokenUsage) {
  const msg = chatState.messages.find(m => m.id === id);
  if (msg) msg.usage = usage;
}

export function clearMessages() {
  chatState.messages = [];
}

export function updateSettings(settings: Partial<Settings>) {
  Object.assign(chatState.settings, settings);
}
```

### API Client (`src/lib/api.ts`)

```typescript
const API_BASE = 'http://localhost:8080/api';

export async function sendMessage(message: string): Promise<{response: string, usage: TokenUsage}> {
  const res = await fetch(`${API_BASE}/chat`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ message })
  });
  if (!res.ok) throw new Error('Chat request failed');
  return res.json();
}

export async function getSettings(): Promise<Settings> {
  const res = await fetch(`${API_BASE}/settings`);
  return res.json();
}

export async function updateSettings(settings: Partial<Settings>): Promise<void> {
  await fetch(`${API_BASE}/settings`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(settings)
  });
}

export async function toggleRag(): Promise<{ragEnabled: boolean}> {
  const res = await fetch(`${API_BASE}/rag/toggle`, { method: 'POST' });
  return res.json();
}

export async function ingestDocuments(): Promise<{documentsIndexed: number}> {
  const res = await fetch(`${API_BASE}/rag/ingest`, { method: 'POST' });
  return res.json();
}

export async function clearHistory(): Promise<void> {
  await fetch(`${API_BASE}/history/clear`, { method: 'POST' });
}
```

### SSE Streaming Integration

```svelte
<!-- src/lib/components/ChatInput.svelte -->
<script lang="ts">
  import { source } from 'sveltekit-sse';
  import { chatState, addMessage, updateMessage, setMessageUsage } from '$lib/stores/chat.svelte';
  import { Send } from 'lucide-svelte';

  let input = $state('');
  let inputElement: HTMLTextAreaElement;

  async function sendMessage() {
    const message = input.trim();
    if (!message || chatState.isStreaming) return;

    // Add user message
    addMessage('user', message);
    input = '';

    if (chatState.settings.streamingEnabled) {
      await sendStreamingMessage(message);
    } else {
      await sendNonStreamingMessage(message);
    }
  }

  async function sendStreamingMessage(message: string) {
    chatState.isStreaming = true;
    const assistantId = addMessage('assistant', '');

    try {
      const connection = source('http://localhost:8080/api/chat/stream', {
        options: {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ message })
        }
      });

      const tokenStream = connection.select('token');
      const doneStream = connection.select('done');

      let fullResponse = '';

      tokenStream.subscribe((token) => {
        if (token) {
          fullResponse += token;
          updateMessage(assistantId, fullResponse);
        }
      });

      doneStream.subscribe((data) => {
        if (data) {
          const usage = JSON.parse(data);
          setMessageUsage(assistantId, usage);
          chatState.isStreaming = false;
        }
      });
    } catch (error) {
      chatState.error = 'Failed to send message';
      chatState.isStreaming = false;
    }
  }

  async function sendNonStreamingMessage(message: string) {
    chatState.isLoading = true;
    try {
      const { response, usage } = await import('$lib/api').then(m => m.sendMessage(message));
      const id = addMessage('assistant', response);
      setMessageUsage(id, usage);
    } catch (error) {
      chatState.error = 'Failed to send message';
    } finally {
      chatState.isLoading = false;
    }
  }

  function handleKeydown(e: KeyboardEvent) {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      sendMessage();
    }
  }
</script>

<div class="flex gap-2 p-4 border-t">
  <textarea
    bind:this={inputElement}
    bind:value={input}
    onkeydown={handleKeydown}
    placeholder="Type your message..."
    rows="1"
    class="flex-1 resize-none rounded-lg border p-3 focus:outline-none focus:ring-2"
    disabled={chatState.isStreaming}
  ></textarea>
  <button
    onclick={sendMessage}
    disabled={!input.trim() || chatState.isStreaming}
    class="rounded-lg bg-blue-600 px-4 py-2 text-white hover:bg-blue-700 disabled:opacity-50"
  >
    <Send size={20} />
  </button>
</div>
```

### Chat Message Component

```svelte
<!-- src/lib/components/ChatMessage.svelte -->
<script lang="ts">
  import type { Message } from '$lib/types';
  import { Copy, Check, User, Bot } from 'lucide-svelte';

  let { message } = $props<{ message: Message }>();
  let copied = $state(false);

  async function copyContent() {
    await navigator.clipboard.writeText(message.content);
    copied = true;
    setTimeout(() => copied = false, 2000);
  }
</script>

<div class="flex gap-3 p-4 {message.role === 'user' ? 'bg-gray-50' : 'bg-white'}">
  <div class="flex-shrink-0">
    {#if message.role === 'user'}
      <div class="w-8 h-8 rounded-full bg-blue-600 flex items-center justify-center">
        <User size={16} class="text-white" />
      </div>
    {:else}
      <div class="w-8 h-8 rounded-full bg-green-600 flex items-center justify-center">
        <Bot size={16} class="text-white" />
      </div>
    {/if}
  </div>

  <div class="flex-1 min-w-0">
    <div class="prose prose-sm max-w-none">
      {message.content}
    </div>

    {#if message.usage}
      <div class="mt-2 text-xs text-gray-500">
        Tokens: {message.usage.inputTokens} in, {message.usage.outputTokens} out
        | Est. cost: ${message.usage.estimatedCost.toFixed(6)}
      </div>
    {/if}
  </div>

  <button onclick={copyContent} class="text-gray-400 hover:text-gray-600">
    {#if copied}
      <Check size={16} />
    {:else}
      <Copy size={16} />
    {/if}
  </button>
</div>
```

### Main Page Layout

```svelte
<!-- src/routes/+page.svelte -->
<script lang="ts">
  import ChatWindow from '$lib/components/ChatWindow.svelte';
  import ChatInput from '$lib/components/ChatInput.svelte';
  import Sidebar from '$lib/components/Sidebar.svelte';
  import { chatState } from '$lib/stores/chat.svelte';
</script>

<div class="flex h-screen bg-gray-100">
  <!-- Sidebar -->
  <Sidebar />

  <!-- Main Chat Area -->
  <div class="flex flex-1 flex-col">
    <!-- Header -->
    <header class="flex items-center justify-between border-b bg-white px-6 py-4">
      <h1 class="text-xl font-semibold">DeepSeek Chatbot</h1>
      <div class="flex items-center gap-2">
        {#if chatState.settings.ragEnabled}
          <span class="rounded-full bg-green-100 px-3 py-1 text-xs text-green-800">
            RAG Active
          </span>
        {/if}
        {#if chatState.settings.streamingEnabled}
          <span class="rounded-full bg-blue-100 px-3 py-1 text-xs text-blue-800">
            Streaming
          </span>
        {/if}
      </div>
    </header>

    <!-- Messages -->
    <ChatWindow />

    <!-- Input -->
    <ChatInput />
  </div>
</div>
```

---

## Phase 3.5: SSE Error Handling & Reconnection

### Backend: SSE Error Handling (Java/Javalin)

Handle client disconnections, timeouts, and streaming errors gracefully.

```java
// ChatController.java - Robust SSE streaming
app.sse("/api/chat/stream", sse -> {
    // Set timeout (5 minutes for long responses)
    sse.keepAlive();

    // Handle client disconnect
    sse.onClose(() -> {
        log.info("Client disconnected from SSE stream");
        // Cancel any ongoing LLM request if possible
    });

    try {
        ChatRequest request = sse.ctx().bodyAsClass(ChatRequest.class);

        chatbot.chatStreamingWithCallback(request.getMessage(),
            // On each token
            token -> {
                try {
                    sse.sendEvent("token", token);
                } catch (Exception e) {
                    log.warn("Failed to send token, client likely disconnected: {}", e.getMessage());
                    throw new ClientDisconnectedException();
                }
            },
            // On complete
            response -> {
                try {
                    sse.sendEvent("done", objectMapper.writeValueAsString(response.usage()));
                } catch (Exception e) {
                    log.warn("Failed to send completion event: {}", e.getMessage());
                }
            },
            // On error
            error -> {
                try {
                    sse.sendEvent("error", error.getMessage());
                } catch (Exception e) {
                    log.error("Failed to send error event: {}", e.getMessage());
                }
            }
        );
    } catch (ClientDisconnectedException e) {
        log.info("Streaming aborted - client disconnected");
    } catch (Exception e) {
        log.error("SSE streaming error: {}", e.getMessage());
        try {
            sse.sendEvent("error", "Internal server error");
        } catch (Exception ignored) {}
    }
});
```

### Backend: Heartbeat/Keep-Alive

Send periodic heartbeats to detect stale connections:

```java
// Send heartbeat every 15 seconds during long operations
ScheduledExecutorService heartbeat = Executors.newSingleThreadScheduledExecutor();
heartbeat.scheduleAtFixedRate(() -> {
    try {
        sse.sendComment("heartbeat"); // SSE comment (: heartbeat)
    } catch (Exception e) {
        heartbeat.shutdown();
    }
}, 15, 15, TimeUnit.SECONDS);
```

### Backend: Request Timeout Configuration

```java
// ChatbotServer.java
Javalin app = Javalin.create(config -> {
    config.http.asyncTimeout = 300_000L; // 5 minutes for SSE
    config.jetty.modifyServer(server -> {
        server.setIdleTimeout(300_000L);
    });
});
```

### Frontend: SSE Error Handling & Reconnection (Svelte)

```svelte
<!-- src/lib/components/ChatInput.svelte - Enhanced with error handling -->
<script lang="ts">
  import { source } from 'sveltekit-sse';
  import { chatState, addMessage, updateMessage, setMessageUsage, setMessageError } from '$lib/stores/chat.svelte';

  const MAX_RETRIES = 3;
  const RETRY_DELAY_MS = 1000;

  async function sendStreamingMessage(message: string, retryCount = 0) {
    chatState.isStreaming = true;
    chatState.connectionStatus = 'connecting';

    const assistantId = retryCount === 0
      ? addMessage('assistant', '')
      : chatState.currentAssistantId;

    if (retryCount === 0) {
      chatState.currentAssistantId = assistantId;
    }

    let fullResponse = '';
    let receivedDone = false;

    try {
      const connection = source('http://localhost:8080/api/chat/stream', {
        options: {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ message })
        },
        // Reconnection callback
        close({ connect }) {
          if (!receivedDone && retryCount < MAX_RETRIES) {
            console.log(`SSE closed unexpectedly, retry ${retryCount + 1}/${MAX_RETRIES}`);
            chatState.connectionStatus = 'reconnecting';
            setTimeout(() => {
              sendStreamingMessage(message, retryCount + 1);
            }, RETRY_DELAY_MS * Math.pow(2, retryCount)); // Exponential backoff
          } else if (!receivedDone) {
            handleStreamError('Connection lost after max retries', assistantId, fullResponse);
          }
        },
        // Error callback
        error(err) {
          console.error('SSE error:', err);
          if (retryCount < MAX_RETRIES) {
            chatState.connectionStatus = 'reconnecting';
            setTimeout(() => {
              sendStreamingMessage(message, retryCount + 1);
            }, RETRY_DELAY_MS * Math.pow(2, retryCount));
          } else {
            handleStreamError(err.message || 'Stream error', assistantId, fullResponse);
          }
        }
      });

      chatState.connectionStatus = 'connected';

      // Token stream
      const tokenStream = connection.select('token');
      tokenStream.subscribe((token) => {
        if (token) {
          fullResponse += token;
          updateMessage(assistantId, fullResponse);
        }
      });

      // Done stream
      const doneStream = connection.select('done');
      doneStream.subscribe((data) => {
        if (data) {
          receivedDone = true;
          const usage = JSON.parse(data);
          setMessageUsage(assistantId, usage);
          chatState.isStreaming = false;
          chatState.connectionStatus = 'idle';
          chatState.currentAssistantId = null;
        }
      });

      // Error stream from server
      const errorStream = connection.select('error');
      errorStream.subscribe((errorMsg) => {
        if (errorMsg) {
          handleStreamError(errorMsg, assistantId, fullResponse);
        }
      });

    } catch (error) {
      handleStreamError(error.message || 'Connection failed', assistantId, fullResponse);
    }
  }

  function handleStreamError(errorMessage: string, assistantId: string, partialResponse: string) {
    chatState.isStreaming = false;
    chatState.connectionStatus = 'error';
    chatState.currentAssistantId = null;

    if (partialResponse) {
      // Keep partial response but mark as incomplete
      updateMessage(assistantId, partialResponse + '\n\n[Response interrupted: ' + errorMessage + ']');
    } else {
      setMessageError(assistantId, errorMessage);
    }

    chatState.error = errorMessage;

    // Clear error after 5 seconds
    setTimeout(() => {
      if (chatState.error === errorMessage) {
        chatState.error = null;
      }
    }, 5000);
  }
</script>
```

### Frontend: Connection Status State

Add connection status to the shared state:

```typescript
// src/lib/stores/chat.svelte.ts - Extended state
export const chatState = $state({
  messages: [] as Message[],
  isStreaming: false,
  isLoading: false,
  error: null as string | null,
  connectionStatus: 'idle' as 'idle' | 'connecting' | 'connected' | 'reconnecting' | 'error',
  currentAssistantId: null as string | null, // Track current streaming message for retries
  settings: {
    model: 'deepseek-chat',
    temperature: 0.7,
    ragEnabled: false,
    streamingEnabled: true,
    showUsage: true
  } as Settings
});

export function setMessageError(id: string, error: string) {
  const msg = chatState.messages.find(m => m.id === id);
  if (msg) {
    msg.error = error;
  }
}
```

### Frontend: Connection Status Indicator Component

```svelte
<!-- src/lib/components/ConnectionStatus.svelte -->
<script lang="ts">
  import { chatState } from '$lib/stores/chat.svelte';
  import { Wifi, WifiOff, Loader2, AlertCircle } from 'lucide-svelte';
</script>

{#if chatState.connectionStatus !== 'idle'}
  <div class="fixed bottom-20 right-4 flex items-center gap-2 rounded-lg px-3 py-2 text-sm shadow-lg
    {chatState.connectionStatus === 'connected' ? 'bg-green-100 text-green-800' : ''}
    {chatState.connectionStatus === 'connecting' ? 'bg-blue-100 text-blue-800' : ''}
    {chatState.connectionStatus === 'reconnecting' ? 'bg-yellow-100 text-yellow-800' : ''}
    {chatState.connectionStatus === 'error' ? 'bg-red-100 text-red-800' : ''}
  ">
    {#if chatState.connectionStatus === 'connected'}
      <Wifi size={16} />
      <span>Connected</span>
    {:else if chatState.connectionStatus === 'connecting'}
      <Loader2 size={16} class="animate-spin" />
      <span>Connecting...</span>
    {:else if chatState.connectionStatus === 'reconnecting'}
      <Loader2 size={16} class="animate-spin" />
      <span>Reconnecting...</span>
    {:else if chatState.connectionStatus === 'error'}
      <AlertCircle size={16} />
      <span>Connection error</span>
    {/if}
  </div>
{/if}
```

### Frontend: Timeout Handling

```typescript
// src/lib/api.ts - Add timeout wrapper
export async function fetchWithTimeout(
  url: string,
  options: RequestInit,
  timeoutMs = 30000
): Promise<Response> {
  const controller = new AbortController();
  const timeoutId = setTimeout(() => controller.abort(), timeoutMs);

  try {
    const response = await fetch(url, {
      ...options,
      signal: controller.signal
    });
    clearTimeout(timeoutId);
    return response;
  } catch (error) {
    clearTimeout(timeoutId);
    if (error.name === 'AbortError') {
      throw new Error('Request timed out');
    }
    throw error;
  }
}
```

### Error Scenarios Handled

| Scenario | Backend Handling | Frontend Handling |
|----------|------------------|-------------------|
| Client disconnects mid-stream | Detect via `onClose()`, stop LLM call | N/A |
| Server timeout | `asyncTimeout` config | Retry with backoff |
| Network interruption | Heartbeat detection | Auto-reconnect up to 3 times |
| LLM API error | Emit `error` event | Display error, keep partial response |
| Malformed response | Try-catch in handlers | Parse error, show message |

---

## Phase 4: UI Features & Polish

### Features Checklist

- [ ] Message list with auto-scroll to bottom
- [ ] Markdown rendering for code blocks
- [ ] Syntax highlighting for code
- [ ] Copy code button on code blocks
- [ ] Typing indicator during streaming
- [ ] Token usage display per message
- [ ] Error toast notifications
- [ ] Loading states
- [ ] Keyboard shortcuts (Enter to send, Shift+Enter for newline)

### Sidebar Features

- [ ] Model selector dropdown (deepseek-chat / deepseek-reasoner)
- [ ] Temperature slider (0.0 - 2.0) with presets
- [ ] RAG toggle switch
- [ ] Streaming toggle switch
- [ ] Usage display toggle
- [ ] Clear history button with confirmation
- [ ] Ingest documents button with progress

### Styling with Tailwind CSS

The UI will follow a clean, modern design:
- Light theme with subtle gradients
- Rounded corners throughout
- Responsive layout (mobile-friendly)
- Smooth transitions and animations
- Accessible color contrast

---

## Phase 5: Development & Deployment

### Development Workflow

**Terminal 1 - Java Backend:**
```bash
mvn compile exec:java -Dexec.mainClass="com.example.chatbot.server.ChatbotServer"
```

**Terminal 2 - SvelteKit Frontend:**
```bash
cd frontend
npm run dev
```

### Environment Variables

**Backend (.env or system):**
```
DEEPSEEK_API_KEY=your_key
OPENAI_API_KEY=your_key  # For embeddings
PORT=8080
```

**Frontend (.env):**
```
PUBLIC_API_URL=http://localhost:8080/api
```

### Production Build

**Frontend:**
```bash
cd frontend
npm run build
npm run preview  # Test production build
```

**Backend:**
```bash
mvn package
java -jar target/deepseek-chatbot-1.0-SNAPSHOT.jar
```

### Optional: Single Deployment

For production, the SvelteKit build output can be served by the Java backend:
1. Build frontend: `npm run build`
2. Copy `frontend/build` to `src/main/resources/static`
3. Configure Javalin to serve static files

---

## Alternative Approaches Considered

| Approach | Pros | Cons | Decision |
|----------|------|------|----------|
| **Javalin + SvelteKit** | Lightweight, SSE native, clean separation | Two processes in dev | ✅ Selected |
| **Spring Boot WebFlux** | Enterprise features, reactive | Heavy, complex setup | ❌ Overkill |
| **Vaadin** | Java-only, no JS needed | Heavy, less modern UI | ❌ Limited flexibility |
| **Tauri + Svelte** | Desktop app, native performance | Different deployment model | ❌ Out of scope |
| **htmx + Javalin** | Simple, no build step | Limited interactivity | ❌ Less reactive |

---

## Implementation Order

1. **Phase 1.1** - Add Javalin dependency to pom.xml
2. **Phase 1.2** - Create `ChatbotServer.java` with basic `/api/health` endpoint
3. **Phase 1.3** - Implement `/api/chat` (non-streaming) endpoint
4. **Phase 1.4** - Implement `/api/chat/stream` (SSE) endpoint
5. **Phase 1.5** - Add settings and RAG endpoints
6. **Phase 2.1** - Initialize SvelteKit project with dependencies
7. **Phase 2.2** - Create shared state store with runes
8. **Phase 2.3** - Build basic chat UI (messages + input)
9. **Phase 3.1** - Integrate non-streaming chat
10. **Phase 3.2** - Integrate SSE streaming with sveltekit-sse
11. **Phase 4.1** - Add sidebar with settings controls
12. **Phase 4.2** - Polish UI, add markdown rendering
13. **Phase 5.1** - Testing and bug fixes
14. **Phase 5.2** - Production build configuration

---

## References

- [Svelte 5 Documentation](https://svelte.dev/docs)
- [SvelteKit Documentation](https://kit.svelte.dev/docs)
- [Javalin Documentation](https://javalin.io/documentation)
- [sveltekit-sse Library](https://github.com/razshare/sveltekit-sse)
- [Tailwind CSS](https://tailwindcss.com/docs)
