# Error Observability System - Quick Reference

## Error ID Lookup

Every error has a unique 8-character ID. Use it to find logs:

```bash
# Find error in logs
grep "a1b2c3d4" logs/chatbot-errors.log

# See surrounding context
grep -A 10 "a1b2c3d4" logs/chatbot-errors.log

# Watch logs in real-time
tail -f logs/chatbot-errors.log
```

## Error Codes at a Glance

| Code | Meaning | User Action |
|------|---------|-------------|
| `VALIDATION_ERROR` | Invalid input | Check your message |
| `MODEL_ERROR` | API timeout/failure | Wait and retry |
| `RATE_LIMITED` | Too many requests | Wait 30-60 seconds |
| `AUTH_FAILED` | Invalid API key | Check `DEEPSEEK_API_KEY` |
| `RAG_ERROR` | Document search failed | Re-ingest documents |
| `CONFIG_ERROR` | Settings issue | Review settings |
| `NETWORK_ERROR` | Connection failed | Check backend/network |
| `INTERNAL_ERROR` | Unexpected error | Check logs with error ID |

## Setup Commands

```bash
# One-time setup
cp .env.example .env
# Edit .env with your API keys

# Load environment (Linux/Mac)
export $(cat .env | xargs)

# Load environment (Windows PowerShell)
Get-Content .env | ForEach-Object {
    $name, $value = $_ -split '=', 2
    [Environment]::SetEnvironmentVariable($name, $value)
}

# Start backend
mvn exec:java -Pserver

# Start frontend (new terminal)
cd frontend && npm run dev
```

## Log Commands

```bash
# View recent errors
tail -50 logs/chatbot-errors.log

# Watch logs in real-time
tail -f logs/chatbot-errors.log

# Search by error ID
grep "a1b2c3d4" logs/chatbot-errors.log

# Search by error code
grep "MODEL_ERROR" logs/chatbot-errors.log

# Count errors by type
grep -o "ERROR_[A-Z_]*" logs/chatbot-errors.log | sort | uniq -c

# View all logs
tail -100 logs/chatbot.log
```

## Debugging Workflow

1. **See error in UI** → Note error ID (e.g., `a1b2c3d4`)
2. **Check error type** → Look at the badge in error card
3. **Search logs** → `grep "a1b2c3d4" logs/chatbot-errors.log`
4. **Find stack trace** → Should appear right after error line
5. **Fix issue** → Apply solution based on error type

## Common Fixes

| Error | Fix |
|-------|-----|
| `AUTH_FAILED` | Verify `DEEPSEEK_API_KEY` is set and valid |
| `RATE_LIMITED` | Wait 30-60 seconds, then retry |
| `MODEL_ERROR` | Check DeepSeek API status, try again |
| `NETWORK_ERROR` | Restart backend: `mvn exec:java -Pserver` |
| `VALIDATION_ERROR` | Make sure message isn't empty |
| `RAG_ERROR` | Ingest documents: Click "Ingest Documents" in UI |

## File Locations

```
Project Root/
├── .env                             # Your API keys (create from .env.example)
├── .env.example                     # Template (check in git)
├── logs/
│   ├── chatbot.log                  # All logs
│   └── chatbot-errors.log           # Errors only
├── src/main/resources/
│   └── logback.xml                  # Logging config
└── docs/
    ├── ERROR_OBSERVABILITY.md       # Full reference
    ├── API_KEY_MANAGEMENT.md        # API key setup
    ├── DEBUGGING_CHECKLIST.md       # Debugging guide
    └── README.md                    # Docs index
```

## API Key Setup

| Platform | URL | Key Format |
|----------|-----|-----------|
| DeepSeek | https://platform.deepseek.com/ | `sk-...` |
| OpenAI | https://platform.openai.com/api-keys | `sk-proj-...` |

## Code Locations

| Component | File |
|-----------|------|
| Error DTO | `src/main/java/.../dto/ErrorResponse.java` |
| Error categorization | `src/main/java/.../ChatController.java` |
| Streaming errors | `src/main/java/.../ChatbotServer.java` |
| Frontend error handling | `frontend/src/lib/api.ts` |
| Error display | `frontend/src/lib/components/ChatMessage.svelte` |

## HTTP Status Codes

| Code | Meaning | Error Code |
|------|---------|-----------|
| 200 | Success | - |
| 400 | Bad request | `VALIDATION_ERROR` |
| 401 | Unauthorized | `AUTH_FAILED` |
| 429 | Too many requests | `RATE_LIMITED` |
| 500 | Server error | Various (see `code` field) |

## Environment Variables

```bash
# Required
DEEPSEEK_API_KEY=sk-your-key-here

# Optional
OPENAI_API_KEY=sk-your-key-here
LLM_PROVIDER=deepseek    # or openai
PORT=8080
```

## Testing

```bash
# Test backend is running
curl http://localhost:8080/api/health

# Test empty message (should return error)
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": ""}'

# Test valid message
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello"}'
```

## Documentation Map

| Need | Read |
|------|------|
| Quick start | [QUICKSTART.md](QUICKSTART.md) |
| API key setup | [docs/API_KEY_MANAGEMENT.md](docs/API_KEY_MANAGEMENT.md) |
| Error system details | [docs/ERROR_OBSERVABILITY.md](docs/ERROR_OBSERVABILITY.md) |
| How to debug | [docs/DEBUGGING_CHECKLIST.md](docs/DEBUGGING_CHECKLIST.md) |
| What was built | [docs/IMPLEMENTATION_SUMMARY.md](docs/IMPLEMENTATION_SUMMARY.md) |
| Docs index | [docs/README.md](docs/README.md) |

## Support

1. Check [DEBUGGING_CHECKLIST.md](docs/DEBUGGING_CHECKLIST.md)
2. Search logs by error ID
3. Check [API_KEY_MANAGEMENT.md](docs/API_KEY_MANAGEMENT.md#troubleshooting)
4. Review [ERROR_OBSERVABILITY.md](docs/ERROR_OBSERVABILITY.md)

---

**Tip:** Keep this file open while debugging. Error ID → Search logs → Fix!
