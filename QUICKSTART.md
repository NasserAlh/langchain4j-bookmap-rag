# Quick Start Guide

Get the chatbot running in minutes.

## Prerequisites

- Java 21+
- Node.js 18+ and npm
- Maven 3.8+
- API keys: [DeepSeek](https://platform.deepseek.com/) and/or [OpenAI](https://platform.openai.com/api-keys)

## 1. Setup API Keys

Copy the example file and add your keys:

```bash
cp .env.example .env
```

Edit `.env`:
```env
DEEPSEEK_API_KEY=sk-your-deepseek-key-here
OPENAI_API_KEY=sk-your-openai-key-here
LLM_PROVIDER=deepseek
```

**Windows (PowerShell):**
```powershell
Get-Content .env | ForEach-Object {
    $name, $value = $_ -split '=', 2
    [Environment]::SetEnvironmentVariable($name, $value)
}
```

**macOS/Linux:**
```bash
export $(cat .env | xargs)
```

## 2. Start the Backend

```bash
mvn exec:java -Pserver
```

Wait for: `ChatbotServer started on port 8080`

## 3. Start the Frontend

In a new terminal:
```bash
cd frontend
npm install
npm run dev
```

Open [http://localhost:5173](http://localhost:5173)

## 4. Chat!

Type a message and see the response stream in real-time.

## Troubleshooting

### "DEEPSEEK_API_KEY not set"
- Make sure you ran the environment variable setup (step 1)
- Check: `echo $DEEPSEEK_API_KEY` (Linux/Mac) or `echo %DEEPSEEK_API_KEY%` (Windows)

### "Cannot compile"
- Run: `mvn clean compile`
- Make sure Java 21 is installed: `java -version`

### "Port 8080 already in use"
- Change port: `PORT=8081 mvn exec:java -Pserver`
- Or kill existing process using port 8080

### Frontend shows "Connection error"
- Make sure backend is running on port 8080
- Check: `curl http://localhost:8080/api/health`

## Debugging

### View Live Logs
```bash
# All logs
tail -f logs/chatbot.log

# Errors only
tail -f logs/chatbot-errors.log
```

### Find Error by ID
When you see an error in the UI with ID `a1b2c3d4`:
```bash
grep "a1b2c3d4" logs/chatbot-errors.log
```

## Learn More

- [Error Logging Guide](docs/ERROR_OBSERVABILITY.md)
- [API Key Management](docs/API_KEY_MANAGEMENT.md)
- [DeepSeek API Docs](https://platform.deepseek.com/)
- [OpenAI API Docs](https://platform.openai.com/docs/)
