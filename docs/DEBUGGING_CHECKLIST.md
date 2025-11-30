# Error Debugging Checklist

Use this checklist when debugging errors in the application.

## 🚀 Initial Setup (One-Time)

- [ ] Copy `.env.example` to `.env`
- [ ] Fill in `DEEPSEEK_API_KEY` in `.env`
- [ ] (Optional) Fill in `OPENAI_API_KEY` if using OpenAI
- [ ] Set environment variables from `.env`
- [ ] Run `mvn clean compile` to verify backend builds
- [ ] Run `cd frontend && npm install && npm run check` to verify frontend builds

## 🔍 When You See an Error

### Step 1: Note the Error ID
In the UI, look for the error card:
```
⚠ AI Model Error
Failed to get response from AI model
HttpTimeoutException: request timed out

ID: a1b2c3d4 [Copy]
```

Copy the error ID: `a1b2c3d4`

### Step 2: Check the Error Type
Look at the badge (e.g., "AI Model Error", "Authentication Failed")

| Badge | Possible Causes | Solutions |
|-------|-----------------|-----------|
| **Invalid Input** | Empty message, bad format | Check input validation |
| **AI Model Error** | API timeout, server down, bad response | Check API key, check DeepSeek/OpenAI status |
| **Rate Limited** | Too many requests (429) | Wait 30-60 seconds, check API quota |
| **Authentication Failed** | Invalid API key (401) | Verify `DEEPSEEK_API_KEY` is set correctly |
| **Document Search Error** | RAG embedding/search failed | Check RAG configuration, re-ingest documents |
| **Configuration Error** | Settings issue | Check settings in UI |
| **Network Error** | Connection failed | Check backend is running, network connectivity |
| **Server Error** | Unexpected error | Check backend logs for details |

### Step 3: Search Backend Logs
```bash
# Find error in error-only log
grep "a1b2c3d4" logs/chatbot-errors.log

# Find error in all logs
grep "a1b2c3d4" logs/chatbot.log

# View surrounding context
grep -A 10 -B 2 "a1b2c3d4" logs/chatbot-errors.log
```

### Step 4: Find the Stack Trace
The error log will show:
```
2024-11-30 11:00:21.123 [main] ERROR ChatController [a1b2c3d4] - Chat request failed: MODEL_ERROR - Connection timed out
java.net.http.HttpTimeoutException: request timed out
    at java.net.http/jdk.internal.net.http.HttpClientImpl.send(HttpClientImpl.java:950)
    at dev.langchain4j.model.openai.OpenAiChatModel.chat(OpenAiChatModel.java:142)
    ...
```

## 🔧 Common Issues & Fixes

### "Internal server error" with no details

**Problem:** Error ID shows but stack trace is hard to find

**Solution:**
1. Check that logback.xml is in `src/main/resources/`
2. Check that `logs/` directory has write permissions
3. View recent errors: `tail -50 logs/chatbot-errors.log`
4. Search by timestamp: `grep "11:00:21" logs/chatbot-errors.log`

### API Key Issues

**Problem:** Error shows "Authentication Failed"

**Checklist:**
- [ ] API key is set: `echo $DEEPSEEK_API_KEY`
- [ ] Key is not truncated (should be ~48 chars starting with `sk-`)
- [ ] API key is valid in provider dashboard
- [ ] API key has credit/quota remaining
- [ ] Using correct environment variable name `DEEPSEEK_API_KEY`

**Fix:**
```bash
# Generate new key from provider dashboard
export DEEPSEEK_API_KEY=sk-new-key-here
mvn exec:java -Pserver
```

### Timeout Issues

**Problem:** Error shows "AI Model Error" with timeout

**Checklist:**
- [ ] DeepSeek/OpenAI API status is up (check status page)
- [ ] Network connectivity is working
- [ ] Try again (might be temporary)
- [ ] Check API rate limits haven't been exceeded
- [ ] Check model name is correct in settings

**Fix:**
```bash
# Try with longer timeout (edit DeepSeekChatbot.java)
# Or try with smaller message first
```

### RAG/Document Errors

**Problem:** Error shows "Document Search Error"

**Checklist:**
- [ ] Documents are actually ingested (check `data/bookmap-embeddings.json`)
- [ ] RAG is enabled in UI settings
- [ ] Embedding model is working
- [ ] Try without RAG first (toggle RAG off)

**Fix:**
```bash
# Re-ingest documents
# In UI: Settings → Ingest Documents

# Or manually clear embeddings and re-ingest
rm data/bookmap-embeddings.json
# Then click "Ingest Documents" in UI
```

### Connection Refused

**Problem:** Error shows "Network Error"

**Checklist:**
- [ ] Backend is running: `curl http://localhost:8080/api/health`
- [ ] Port 8080 is not blocked by firewall
- [ ] No other process using port 8080
- [ ] Frontend is trying to connect to correct backend URL

**Fix:**
```bash
# Check if backend is running
curl http://localhost:8080/api/health
# Should return: {"status":"ok"}

# Kill process using port 8080
lsof -i :8080
kill -9 <PID>

# Try different port
PORT=8081 mvn exec:java -Pserver
# Update frontend API_BASE in api.ts
```

## 📊 Analyzing Logs

### View All Activity
```bash
# Last 50 lines
tail -50 logs/chatbot.log

# Last 100 lines, real-time
tail -f logs/chatbot.log

# View during specific time
grep "11:00" logs/chatbot.log
```

### Filter by Error Type
```bash
# MODEL_ERROR only
grep "MODEL_ERROR" logs/chatbot-errors.log

# RATE_LIMITED only
grep "RATE_LIMITED" logs/chatbot-errors.log

# All errors in last 24 hours
find logs -name "chatbot*.log" -mtime -1 -exec grep "ERROR" {} +
```

### Count Errors by Type
```bash
# Error frequency
grep -o "ERROR_[A-Z_]*" logs/chatbot-errors.log | sort | uniq -c

# Example output:
#   5 MODEL_ERROR
#   2 RATE_LIMITED
#   1 AUTH_FAILED
```

## 🚨 Production Debugging

If backend is on remote server:

```bash
# SSH into server
ssh user@server.com

# View logs in real-time
tail -f /app/logs/chatbot-errors.log

# Search for specific error ID
grep "a1b2c3d4" /app/logs/chatbot-errors.log

# Download error log
scp user@server.com:/app/logs/chatbot-errors.log ./server-errors.log
```

## 📋 Developer Workflow

When adding new error handling:

### Backend
1. Create error code in `ErrorResponse.Codes`
2. Add factory method in `ErrorResponse`
3. Add detection in `categorizeError()` method
4. Log with MDC: `MDC.put("errorId", error.getErrorId())`

### Frontend
1. Add error code label in `ChatMessage.svelte`
2. Test error handling in `+page.svelte`
3. Verify error displays correctly in UI

### Testing
```bash
# Compile
mvn clean compile

# Check types
cd frontend && npm run check

# Run
mvn exec:java -Pserver
cd frontend && npm run dev

# Trigger error and verify:
# - Error ID appears in UI
# - Error ID appears in logs
# - Stack trace is complete
```

## ✅ Troubleshooting Template

When reporting an issue:

```
Error ID: a1b2c3d4
Type: [MODEL_ERROR / RATE_LIMITED / etc]
Message: [User-facing message]
Details: [Technical details]

Timestamp: [When it occurred]
Steps to Reproduce: [How to make it happen again]

Backend: [Running / Stopped / Error]
Frontend: [Running / Error / Blank]

Environment:
- Java version: [mvn -version]
- Node version: [node -v]
- API Key: [Set / Not Set]

Relevant Logs:
[Paste grep output with error ID]
```

## 🎓 Learning Resources

- [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md) - Full error handling guide
- [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md) - API key setup
- [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - What was built
- [logback.xml](../src/main/resources/logback.xml) - Logging configuration

## 💡 Pro Tips

1. **Watch logs while testing:** `tail -f logs/chatbot-errors.log` in one terminal
2. **Search by time:** `grep "11:00" logs/chatbot.log` to find all activity at that minute
3. **Copy error details:** Click "Copy" button in error card to share with others
4. **Check API status:** Before debugging, verify the API provider's status page
5. **Test with curl:** `curl -X POST http://localhost:8080/api/chat -H "Content-Type: application/json" -d '{"message":"test"}'`
6. **Increase logging:** Change `<logger name="com.example.chatbot" level="DEBUG"/>` in logback.xml for more details
7. **Save logs:** Keep logs folder for at least 7 days for debugging production issues
