# How to Test API Keys

Quick methods to verify your API keys are working before running the full application.

## Prerequisites

- Curl installed (or Postman/Insomnia)
- API keys added to `.env`
- Backend running on `http://localhost:8080`

## Method 1: Test Backend Health (Easiest)

No API key needed - just checks if backend is running:

```bash
curl http://localhost:8080/api/health
```

Expected response:
```json
{"status":"ok"}
```

---

## Method 2: Test Chat Endpoint (Tests API Key)

This tests if your API key works by sending a real chat request.

### Start Backend First

```bash
# Terminal 1 - Load API keys and start backend
export $(cat .env | xargs)  # Linux/macOS
# OR (Windows PowerShell):
# Get-Content .env | ForEach-Object { $name, $value = $_ -split '=', 2; [Environment]::SetEnvironmentVariable($name, $value) }

./build-and-run.sh  # Linux/macOS
# OR
.\build-and-run.ps1  # Windows PowerShell
# OR
build-and-run.bat  # Windows cmd
```

Wait for: `ChatbotServer started on port 8080`

### Test Non-Streaming Chat

In a new terminal:

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello, what is 2+2?"}'
```

Expected response (success):
```json
{
  "response": "2 + 2 = 4",
  "usage": {
    "inputTokens": 12,
    "outputTokens": 8,
    "estimatedCost": 0.0042
  }
}
```

Expected response (invalid API key):
```json
{
  "errorId": "a1b2c3d4",
  "code": "AUTH_FAILED",
  "message": "API authentication failed. Check your API key.",
  "details": "HttpClientException: 401 Unauthorized",
  "timestamp": 1732972800000
}
```

### Test Streaming Chat

```bash
curl -X POST http://localhost:8080/api/chat/stream \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello"}' \
  -N  # Disable buffering for real-time output
```

Expected response (streaming):
```
event: token
data: "Hello"

event: token
data: " there!"

event: done
data: {"inputTokens":12,"outputTokens":8,"estimatedCost":0.0042}
```

---

## Method 3: Check Logs for Errors

After running test above, check logs:

```bash
# View recent activity
tail -50 logs/chatbot.log

# View errors only
tail -50 logs/chatbot-errors.log

# Search for specific error ID
grep "a1b2c3d4" logs/chatbot-errors.log
```

Example log output (success):
```
2024-11-30 11:00:21.123 [main] INFO ChatController - Received chat request: Hello, what is 2+2?
2024-11-30 11:00:22.456 [main] INFO ChatController - Chat response: 2 + 2 = 4
```

Example log output (API key error):
```
2024-11-30 11:00:21.123 [main] INFO ChatController - Received chat request: Hello, what is 2+2?
2024-11-30 11:00:21.234 [main] ERROR ChatController [a1b2c3d4] - Chat request failed: AUTH_FAILED - 401 Unauthorized
```

---

## Method 4: Use Postman or Insomnia

### Postman Steps

1. Open Postman
2. Create new request:
   - **Method:** POST
   - **URL:** `http://localhost:8080/api/chat`
   - **Headers:**
     - Key: `Content-Type`
     - Value: `application/json`
   - **Body (raw JSON):**
     ```json
     {"message": "Hello, what is 2+2?"}
     ```
3. Click **Send**

### Insomnia Steps

1. Open Insomnia
2. Create new POST request
3. Set URL to: `http://localhost:8080/api/chat`
4. Set body to JSON:
   ```json
   {"message": "Hello, what is 2+2?"}
   ```
5. Click **Send**

---

## Common Error Responses & Fixes

### 401 Unauthorized (Invalid API Key)

```json
{
  "errorId": "a1b2c3d4",
  "code": "AUTH_FAILED",
  "message": "API authentication failed. Check your API key."
}
```

**Fix:**
1. Verify API key in `.env` is correct
2. Check key hasn't expired
3. Regenerate key from provider dashboard
4. Restart backend after updating key

### 429 Too Many Requests (Rate Limit)

```json
{
  "errorId": "b2c3d4e5",
  "code": "RATE_LIMITED",
  "message": "Too many requests. Please wait and try again."
}
```

**Fix:**
1. Wait 30-60 seconds
2. Check API quota in provider dashboard
3. Try again

### Connection Refused

```
curl: (7) Failed to connect to localhost port 8080
```

**Fix:**
1. Make sure backend is running
2. Check: `curl http://localhost:8080/api/health`
3. Check port 8080 is not blocked by firewall
4. Try different port: `PORT=8081 ./build-and-run.sh`

### 500 Internal Server Error

```json
{
  "errorId": "c3d4e5f6",
  "code": "INTERNAL_ERROR",
  "message": "An unexpected error occurred",
  "details": "NullPointerException"
}
```

**Fix:**
1. Check logs: `tail logs/chatbot-errors.log`
2. Search by error ID: `grep "c3d4e5f6" logs/chatbot-errors.log`
3. Look at full stack trace
4. Restart backend

---

## Step-by-Step Test Checklist

### ✅ Step 1: Verify .env File

```bash
# Check .env exists
cat .env

# Should show:
# DEEPSEEK_API_KEY=sk-...
# OPENAI_API_KEY=sk-...
```

### ✅ Step 2: Load API Keys

**Linux/macOS:**
```bash
export $(cat .env | xargs)
echo $DEEPSEEK_API_KEY  # Should show your key
```

**Windows PowerShell:**
```powershell
Get-Content .env | ForEach-Object {
    $name, $value = $_ -split '=', 2
    [Environment]::SetEnvironmentVariable($name, $value)
}
echo $env:DEEPSEEK_API_KEY  # Should show your key
```

### ✅ Step 3: Start Backend

```bash
./build-and-run.sh  # or .bat or .ps1
```

Wait for: `ChatbotServer started on port 8080`

### ✅ Step 4: Test Health Endpoint

In new terminal:
```bash
curl http://localhost:8080/api/health
# Expected: {"status":"ok"}
```

### ✅ Step 5: Test Chat Endpoint

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hi"}'
```

### ✅ Step 6: Check Response

- **Got valid JSON with response?** ✅ API Key works!
- **Got error with `code: "AUTH_FAILED"`?** ❌ API key is invalid
- **Got error with `code: "RATE_LIMITED"`?** ⏳ Wait and retry
- **Got connection refused?** ⚠️ Backend not running

---

## Quick Test Commands

### Test Everything (5 commands)

```bash
# 1. Start backend
./build-and-run.sh &

# 2. Wait 3 seconds
sleep 3

# 3. Test health
curl http://localhost:8080/api/health

# 4. Test chat with API key
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'

# 5. Check logs
tail logs/chatbot-errors.log
```

### One-Liner Test (Linux/macOS)

```bash
export $(cat .env | xargs) && \
./build-and-run.sh &
sleep 3 && \
curl http://localhost:8080/api/health && \
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hi"}'
```

---

## Frontend Testing

Once backend is working, start frontend:

```bash
cd frontend
npm run dev
```

Open: **http://localhost:5173**

Send a message - if you see a response, your API key works! ✅

---

## Debugging with Verbose Output

### Verbose cURL Output

```bash
curl -v -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'
```

Shows:
- Request headers
- Response headers
- Response body

### Watch Backend Logs

In separate terminal:

```bash
tail -f logs/chatbot.log
```

Now run curl command - you'll see log entries in real-time.

---

## Common Test Scenarios

### Scenario 1: Fresh Setup

```bash
# Create .env from template
cp .env.example .env

# Edit .env with your API keys
nano .env  # or use any editor

# Load keys
export $(cat .env | xargs)

# Start backend
./build-and-run.sh

# Test (in another terminal)
curl http://localhost:8080/api/health
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'
```

### Scenario 2: API Key Rotation

```bash
# Update .env with new key
nano .env

# Reload environment
export $(cat .env | xargs)

# Restart backend (Ctrl+C first, then)
./build-and-run.sh

# Test
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'
```

### Scenario 3: Test Different Provider

```bash
# Update .env
LLM_PROVIDER=openai  # Change from deepseek

# Reload and restart
export $(cat .env | xargs)
./build-and-run.sh

# Test
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "test"}'
```

---

## See Also

- [API_KEY_MANAGEMENT.md](docs/API_KEY_MANAGEMENT.md) - How to set API keys
- [DEBUGGING_CHECKLIST.md](docs/DEBUGGING_CHECKLIST.md) - Debugging errors
- [REFERENCE.md](REFERENCE.md) - Quick command reference
- [ERROR_OBSERVABILITY.md](docs/ERROR_OBSERVABILITY.md) - Error codes explained
