# Comprehensive Error Logging & Observability Plan

## 📋 Problem Analysis

**Current Issue**: Svelte frontend shows generic "Internal server error" when chatting with DeepSeek Reasoner model, with no context about what went wrong.

**Root Problems Identified**:
- Generic error messages (`e.getMessage()` only)
- No error IDs for frontend ↔ backend correlation
- No structured logging or log levels configuration
- Silent failures in some UI operations

---

## 🚀 Implementation Phases

### Phase 1: Quick Wins (Immediate Visibility)

#### 1.1 Structured Error Responses with Correlation IDs

```java
// Create: src/main/java/com/example/chatbot/server/ErrorResponse.java
public record ErrorResponse(
    String errorId,      // UUID for correlation
    String code,         // e.g., "CHAT_FAILED", "MODEL_ERROR", "RATE_LIMITED"
    String message,      // Human-readable
    String details,      // Stack trace summary (first line)
    long timestamp
) {}
```

#### 1.2 Configure Proper Logging (logback.xml)

```xml
<!-- Create: src/main/resources/logback.xml -->
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/chatbot.log</file>
        <encoder>
            <pattern>%d{ISO8601} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss} %-5level %logger{20} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

#### 1.3 Enhanced Frontend Error Display

```svelte
<!-- ChatMessage.svelte - Show error ID for support -->
{#if message.error}
  <div class="error-panel">
    <span class="error-message">{message.error.message}</span>
    <span class="error-id">Error ID: {message.error.errorId}</span>
    <button onclick={() => copyErrorDetails()}>Copy for Support</button>
  </div>
{/if}
```

---

### Phase 2: Backend Observability

#### 2.1 Request Context Middleware

```java
// Add request ID to every request, log request/response
app.before(ctx -> {
    String requestId = UUID.randomUUID().toString().substring(0, 8);
    ctx.attribute("requestId", requestId);
    MDC.put("requestId", requestId);  // SLF4J MDC for log correlation
    log.info("→ {} {} from {}", ctx.method(), ctx.path(), ctx.ip());
});

app.after(ctx -> {
    log.info("← {} {} → {} ({}ms)",
        ctx.method(), ctx.path(), ctx.status(),
        System.currentTimeMillis() - ctx.attribute("startTime"));
    MDC.clear();
});
```

#### 2.2 Categorized Error Codes

| Code | Meaning | User Action |
|------|---------|-------------|
| `MODEL_UNAVAILABLE` | DeepSeek API down | Wait and retry |
| `RATE_LIMITED` | Too many requests | Slow down |
| `INVALID_INPUT` | Bad message format | Check input |
| `CONFIG_ERROR` | Settings issue | Check settings |
| `RAG_FAILED` | Document search failed | Check documents |
| `NETWORK_ERROR` | Connection issue | Check network |
| `UNKNOWN` | Unexpected error | Contact support |

#### 2.3 DeepSeek API Error Parsing

```java
// Parse specific DeepSeek errors from their response
catch (OpenAiHttpException e) {
    if (e.statusCode() == 429) {
        return new ErrorResponse(id, "RATE_LIMITED", "Too many requests", ...);
    } else if (e.statusCode() == 401) {
        return new ErrorResponse(id, "AUTH_FAILED", "Invalid API key", ...);
    }
    // ... etc
}
```

---

### Phase 3: Frontend Observability

#### 3.1 Global Error Toast System

```typescript
// stores/notifications.svelte.ts
export function createNotificationStore() {
    let notifications = $state<Notification[]>([]);

    return {
        error: (msg: string, errorId?: string) => {
            notifications.push({ type: 'error', msg, errorId, ts: Date.now() });
        },
        // Auto-dismiss after 10s for errors
    };
}
```

#### 3.2 Error Boundary Component

```svelte
<!-- components/ErrorBoundary.svelte -->
<script>
    let error = $state(null);

    // Catch unhandled promise rejections
    onMount(() => {
        window.addEventListener('unhandledrejection', (e) => {
            error = e.reason;
            logToBackend(e.reason); // Optional: send to backend
        });
    });
</script>

{#if error}
    <div class="error-overlay">
        <h2>Something went wrong</h2>
        <pre>{error.message}</pre>
        <button onclick={() => location.reload()}>Reload</button>
    </div>
{:else}
    <slot />
{/if}
```

#### 3.3 Network Status Monitor

```javascript
// Heartbeat every 30s, auto-reconnect logic
let healthCheckInterval = setInterval(async () => {
    try {
        await fetch('/api/health');
        connectionStatus = 'connected';
    } catch {
        connectionStatus = 'disconnected';
        notifications.error('Connection lost. Retrying...');
    }
}, 30000);
```

---

### Phase 4: Developer Experience

#### 4.1 Debug Mode Toggle
Add debug mode showing:
- Full error stack traces in UI (dev only)
- Request/response timing
- Token usage per message
- Model latency breakdown

#### 4.2 Log Viewer Endpoint (Optional)
```java
// GET /api/logs?lines=100&level=ERROR
// Returns recent log entries for debugging without SSH access
```

---

## 🎯 Implementation Priority

| Priority | Item | Time | Impact |
|----------|------|------|---------|
| **P0** | Add error correlation IDs | 30 min | **High** - immediately know which error is which |
| **P0** | Add logback.xml with file logging | 15 min | **High** - persistent logs to review |
| **P1** | Categorized error codes | 1 hr | **High** - actionable error messages |
| **P1** | Frontend toast notifications | 45 min | **Medium** - better UX |
| **P2** | Request context middleware | 30 min | **Medium** - trace request flow |
| **P2** | Health check heartbeat | 30 min | **Medium** - detect disconnects |
| **P3** | Debug mode UI | 1 hr | **Low** - dev convenience |

---

## 🔍 Immediate Next Steps

To diagnose your current "Internal server error":

1. **Check the terminal** where you ran `mvn exec:java` - the stacktrace should be there
2. **Implement Phase 1 (P0 items)** to capture the next error with proper context

**Phase 1 Benefits**:
- ✅ Error IDs on every error
- ✅ Persistent log files in `logs/chatbot.log`
- ✅ Better error messages in the UI

**Ready to implement Phase 1 now?** This will give you immediate visibility into what's causing the current errors.