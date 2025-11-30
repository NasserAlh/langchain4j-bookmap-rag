# Error Logging & Observability Guide

This document describes the error handling and observability system implemented in the LangChain4j Chatbot application.

## How to Use

1. Restart the backend: `mvn exec:java -Pserver`
2. Restart the frontend: `npm run dev`
3. When errors occur:
   - You'll see a detailed error card in the UI with error ID
   - Check `logs/chatbot.log` for full details
   - Check `logs/chatbot-errors.log` for errors only
   - Search logs by error ID to find exact stack trace

## Overview

The system provides:
- **Correlation IDs**: Every error gets a unique 8-character ID linking frontend errors to backend logs
- **Categorized Errors**: Errors are classified into actionable types (rate limiting, auth, model errors, etc.)
- **Persistent Logging**: File-based logs with rotation for debugging production issues
- **Rich UI Feedback**: Users see meaningful error messages with copy-to-clipboard support

## Error Response Structure

All API errors return this JSON structure:

```json
{
  "errorId": "a1b2c3d4",
  "code": "MODEL_ERROR",
  "message": "Failed to get response from AI model",
  "details": "HttpTimeoutException: request timed out",
  "timestamp": 1732972800000
}
```

### Error Codes

| Code | Meaning | User Action |
|------|---------|-------------|
| `VALIDATION_ERROR` | Invalid input (empty message, bad format) | Fix input and retry |
| `MODEL_ERROR` | DeepSeek API issue (timeout, bad response) | Wait and retry |
| `RATE_LIMITED` | Too many requests (429) | Wait 30-60 seconds |
| `AUTH_FAILED` | Invalid API key (401) | Check `DEEPSEEK_API_KEY` |
| `RAG_ERROR` | Document search/embedding failed | Check RAG configuration |
| `CONFIG_ERROR` | Settings issue | Review settings |
| `NETWORK_ERROR` | Connection failed | Check network/backend status |
| `INTERNAL_ERROR` | Unexpected server error | Check logs with error ID |

## Log Files

Logs are stored in the `logs/` directory:

| File | Contents | Retention |
|------|----------|-----------|
| `chatbot.log` | All logs (DEBUG and above) | 7 days, 100MB max |
| `chatbot-errors.log` | Errors only | 30 days |

### Log Format

```
2024-11-30 11:00:21.123 [main] ERROR ChatController [a1b2c3d4] - Chat request failed: MODEL_ERROR - Connection timed out
```

The `[a1b2c3d4]` is the error ID that matches what the user sees in the UI.

## Debugging Workflow

### 1. User Reports an Error

User sees this in the UI:
```
┌─────────────────────────────────────────┐
│ ⚠ AI Model Error                        │
│ Failed to get response from AI model    │
│ HttpTimeoutException: request timed out │
│                                         │
│ ID: a1b2c3d4                    [Copy]  │
└─────────────────────────────────────────┘
```

### 2. Find the Error in Logs

```bash
# Search by error ID
grep "a1b2c3d4" logs/chatbot.log

# Or check error-only log
grep "a1b2c3d4" logs/chatbot-errors.log

# View recent errors
tail -100 logs/chatbot-errors.log
```

### 3. Get Full Stack Trace

The log file contains the complete stack trace:

```
2024-11-30 11:00:21.123 [main] ERROR ChatController [a1b2c3d4] - Chat request failed: MODEL_ERROR - Connection timed out
java.net.http.HttpTimeoutException: request timed out
    at java.net.http/jdk.internal.net.http.HttpClientImpl.send(HttpClientImpl.java:950)
    at dev.langchain4j.model.openai.OpenAiChatModel.chat(OpenAiChatModel.java:142)
    ...
```

## Configuration

### Logback Configuration

Edit `src/main/resources/logback.xml` to adjust logging:

```xml
<!-- Change log level for more/less detail -->
<logger name="com.example.chatbot" level="DEBUG"/>

<!-- Reduce library noise -->
<logger name="dev.langchain4j" level="INFO"/>
<logger name="io.javalin" level="WARN"/>
```

### Log Level Guide

| Level | When to Use |
|-------|-------------|
| `ERROR` | Production - errors only |
| `WARN` | Production - errors + warnings |
| `INFO` | Normal operation monitoring |
| `DEBUG` | Development/troubleshooting |

## Backend Code Reference

### Creating Errors

```java
// Validation error (400)
ErrorResponse error = ErrorResponse.validation("Message is required");
ctx.status(400).json(error);

// Model error (500)
ErrorResponse error = ErrorResponse.modelError("Failed to get response", exception);
ctx.status(500).json(error);

// Rate limited
ErrorResponse error = ErrorResponse.rateLimited();
ctx.status(429).json(error);

// Generic internal error
ErrorResponse error = ErrorResponse.internal(exception);
ctx.status(500).json(error);
```

### Logging with Error ID

```java
ErrorResponse error = ErrorResponse.internal(e);
MDC.put("errorId", error.getErrorId());  // Adds to log pattern
log.error("[{}] Chat failed: {}", error.getErrorId(), e.getMessage(), e);
MDC.remove("errorId");
ctx.status(500).json(error);
```

### Error Categorization

The `categorizeError()` method in `ChatController.java` maps exceptions to error codes:

```java
private ErrorResponse categorizeError(Exception e) {
    String message = e.getMessage().toLowerCase();

    if (message.contains("rate limit") || message.contains("429")) {
        return ErrorResponse.rateLimited();
    }
    if (message.contains("401") || message.contains("unauthorized")) {
        return ErrorResponse.authFailed();
    }
    // ... more patterns

    return ErrorResponse.internal(e);
}
```

## Frontend Code Reference

### Handling Errors in API Calls

```typescript
import { ChatApiError } from '$lib/api';

try {
    const response = await sendMessage(content);
} catch (error) {
    if (error instanceof ChatApiError) {
        // Structured error with errorId, code, message, details
        console.log(error.apiError.errorId);
        setMessageError(assistantId, error.apiError);
    }
}
```

### Streaming Error Handling

```typescript
await sendStreamingMessage(
    message,
    (token) => { /* handle token */ },
    (usage) => { /* handle completion */ },
    (error: ApiError) => {
        // error has: errorId, code, message, details, timestamp
        setMessageError(assistantId, error);
    }
);
```

### ApiError Type

```typescript
interface ApiError {
    errorId: string;    // "a1b2c3d4"
    code: string;       // "MODEL_ERROR"
    message: string;    // "Failed to get response from AI model"
    details?: string;   // "HttpTimeoutException: request timed out"
    timestamp: number;  // 1732972800000
}
```

## File Locations

| Component | File |
|-----------|------|
| Error DTO | `src/main/java/.../server/dto/ErrorResponse.java` |
| Logback config | `src/main/resources/logback.xml` |
| Chat error handling | `src/main/java/.../server/ChatController.java` |
| Streaming error handling | `src/main/java/.../server/ChatbotServer.java` |
| Frontend API | `frontend/src/lib/api.ts` |
| Error types | `frontend/src/lib/types/index.ts` |
| Error display | `frontend/src/lib/components/ChatMessage.svelte` |

## Extending the System

### Adding New Error Codes

1. Add constant in `ErrorResponse.Codes`:
   ```java
   public static final String QUOTA_EXCEEDED = "QUOTA_EXCEEDED";
   ```

2. Add factory method:
   ```java
   public static ErrorResponse quotaExceeded() {
       return new ErrorResponse(Codes.QUOTA_EXCEEDED,
           "API quota exceeded for this month", null);
   }
   ```

3. Add detection in `categorizeError()`:
   ```java
   if (message.contains("quota") || message.contains("billing")) {
       return ErrorResponse.quotaExceeded();
   }
   ```

4. Add friendly label in frontend `ChatMessage.svelte`:
   ```typescript
   const errorCodeLabels = {
       // ...
       QUOTA_EXCEEDED: 'Quota Exceeded'
   };
   ```

### Adding More Context to Logs

Use MDC for request-scoped context:

```java
// In a before-handler or filter
MDC.put("requestId", UUID.randomUUID().toString());
MDC.put("model", settings.getModel());

// All logs in this request will include these
log.info("Processing chat request");

// Clean up
MDC.clear();
```

Update `logback.xml` pattern:
```xml
<pattern>%d{ISO8601} [%X{requestId}] [%X{model}] %-5level %logger{20} - %msg%n</pattern>
```
