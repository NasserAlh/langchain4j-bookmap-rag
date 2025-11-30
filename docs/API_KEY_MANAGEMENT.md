# API Key Management Guide

This document explains where and how to safely store and use API keys for LLM services in this application.

## Current Setup

The application currently uses **environment variables** for API keys, which is the recommended approach for production.

### DeepSeek API Key

The application uses DeepSeek's OpenAI-compatible API. You need to set the `DEEPSEEK_API_KEY` environment variable.

## Setting API Keys

### Option 1: Environment Variables (Recommended for Development)

#### Windows (Command Prompt)
```cmd
set DEEPSEEK_API_KEY=your_actual_api_key_here
mvn exec:java -Pserver
```

#### Windows (PowerShell)
```powershell
$env:DEEPSEEK_API_KEY="your_actual_api_key_here"
mvn exec:java -Pserver
```

#### macOS/Linux
```bash
export DEEPSEEK_API_KEY=your_actual_api_key_here
mvn exec:java -Pserver
```

### Option 2: .env File (Local Development) - Recommended

1. Copy the example file:
```bash
cp .env.example .env
```

2. Edit `.env` and add your actual API keys:
```env
DEEPSEEK_API_KEY=sk-your-actual-deepseek-key-here
OPENAI_API_KEY=sk-your-actual-openai-key-here
LLM_PROVIDER=deepseek
```

The `.env` file is ignored by git for security.

Then load it before running:

**Windows (PowerShell):**
```powershell
Get-Content .env | ForEach-Object {
    $name, $value = $_ -split '=', 2
    [Environment]::SetEnvironmentVariable($name, $value)
}
mvn exec:java -Pserver
```

**macOS/Linux:**
```bash
export $(cat .env | xargs)
mvn exec:java -Pserver
```

### Option 3: IDE Configuration

#### IntelliJ IDEA / JetBrains IDEs

1. Go to **Run** → **Edit Configurations**
2. Select your Maven run configuration (or create a new one for `mvn exec:java -Pserver`)
3. Click the **Environment variables** field
4. Add: `DEEPSEEK_API_KEY=your_actual_key_here`

#### VS Code with Maven Extension

1. Create a `.env` file in the root directory
2. Install the **REST Client** extension
3. Create a `.vscode/settings.json`:
```json
{
    "java.configuration.updateBuildConfiguration": "automatic",
    "maven.terminal.executeInIntegratedTerminal": true
}
```

Then set the environment variable before running Maven.

### Option 4: System Properties (Advanced)

You can also modify `ChatbotServer.java` to read from system properties:

```java
// Current implementation (environment variables)
String apiKey = System.getenv("DEEPSEEK_API_KEY");

// Alternative: system properties
String apiKey = System.getProperty("deepseek.api.key");
```

Run with: `mvn exec:java -Ddeepseek.api.key=your_key -Pserver`

## Getting API Keys

### DeepSeek

1. Visit [https://platform.deepseek.com/](https://platform.deepseek.com/)
2. Sign up or log in
3. Go to **API Keys** section
4. Create a new API key
5. Copy the key (it won't be shown again)

Format: Starts with `sk-` (e.g., `sk-12345abcde...`)

### OpenAI

1. Visit [https://platform.openai.com/api-keys](https://platform.openai.com/api-keys)
2. Sign up or log in
3. Click **Create new secret key**
4. Copy the key (it won't be shown again)
5. You can set usage limits to prevent unexpected charges

Format: Starts with `sk-` (e.g., `sk-proj-...`)

**Note:** OpenAI requires a paid account with billing setup. Free trial credits may have expired.

## Security Best Practices

### ✅ DO

- Store API keys in environment variables
- Use `.env` file for local development (add to `.gitignore`)
- Use secret management systems in production (AWS Secrets Manager, HashiCorp Vault, etc.)
- Rotate API keys periodically
- Use different keys for development, staging, and production
- Restrict API key permissions to minimum required scope

### ❌ DON'T

- Commit API keys to git (they're in `.gitignore`, but double-check)
- Hardcode API keys in source files
- Store API keys in version control
- Share API keys in messages, emails, or documentation
- Use the same API key across multiple environments
- Log full API keys (only log first/last 4 characters if needed)

## Viewing Logs with API Key Security

The application logs requests but does NOT include the API key. Here's what you'll see:

```
2024-11-30 11:00:21.123 [main] INFO ChatController - Received chat request: What is Bookmap?
```

The API key is passed in HTTP headers and is never logged or displayed.

## Handling Multiple LLM Providers

If you want to support multiple LLM providers (e.g., OpenAI, Claude, DeepSeek), modify the setup:

### Update ChatbotServer.java

```java
public static void main(String[] args) {
    String apiKey = System.getenv("DEEPSEEK_API_KEY");
    if (apiKey == null || apiKey.isEmpty()) {
        System.err.println("Error: DEEPSEEK_API_KEY environment variable is not set.");
        System.exit(1);
    }

    String model = System.getenv("LLM_PROVIDER");
    if (model == null) {
        model = "deepseek"; // default
    }

    DeepSeekChatbot chatbot;

    switch (model.toLowerCase()) {
        case "deepseek":
            chatbot = new DeepSeekChatbot(apiKey);
            break;
        case "openai":
            String openaiKey = System.getenv("OPENAI_API_KEY");
            chatbot = new OpenAiChatbot(openaiKey);
            break;
        default:
            System.err.println("Unknown LLM provider: " + model);
            System.exit(1);
    }

    // ... rest of setup
}
```

### Run with Different Providers

```bash
# DeepSeek
export DEEPSEEK_API_KEY=sk-...
mvn exec:java -Pserver

# OpenAI
export LLM_PROVIDER=openai
export OPENAI_API_KEY=sk-...
mvn exec:java -Pserver
```

## Production Deployment

### Environment Variables

Set environment variables in your deployment platform:

**AWS EC2/ECS:**
```bash
aws ssm put-parameter \
  --name /deepseek/api-key \
  --value "sk-..." \
  --type SecureString

# In your application/start script:
export DEEPSEEK_API_KEY=$(aws ssm get-parameter --name /deepseek/api-key --query 'Parameter.Value' --output text)
mvn exec:java -Pserver
```

**Heroku:**
```bash
heroku config:set DEEPSEEK_API_KEY=sk-...
```

**Docker:**
```dockerfile
FROM maven:3.8-openjdk-21

WORKDIR /app
COPY . .

ENV DEEPSEEK_API_KEY=${DEEPSEEK_API_KEY}

RUN mvn clean package

CMD ["mvn", "exec:java", "-Pserver"]
```

Run with:
```bash
docker run -e DEEPSEEK_API_KEY=sk-... my-chatbot-image
```

**Docker Compose:**
```yaml
services:
  chatbot:
    build: .
    environment:
      DEEPSEEK_API_KEY: ${DEEPSEEK_API_KEY}
    ports:
      - "8080:8080"
```

Run with:
```bash
DEEPSEEK_API_KEY=sk-... docker-compose up
```

### Kubernetes Secrets

```bash
kubectl create secret generic deepseek-secrets \
  --from-literal=api-key=sk-...

kubectl create secret generic deepseek-secrets \
  --from-file=api-key=/path/to/api-key-file
```

Reference in deployment:
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: chatbot
spec:
  containers:
  - name: chatbot
    image: my-chatbot:latest
    env:
    - name: DEEPSEEK_API_KEY
      valueFrom:
        secretKeyRef:
          name: deepseek-secrets
          key: api-key
```

## Troubleshooting

### "Error: DEEPSEEK_API_KEY environment variable is not set"

The environment variable is not being passed to the Java process.

**Solutions:**
1. Check the environment variable is actually set: `echo $DEEPSEEK_API_KEY` (Linux/Mac) or `echo %DEEPSEEK_API_KEY%` (Windows)
2. Make sure you set it BEFORE running the Maven command
3. If using IDE, check the Run Configuration environment variables
4. Make sure `.env` file is in the root directory and properly formatted

### "401 Unauthorized" Error

The API key is not valid or has expired.

**Solutions:**
1. Generate a new API key from the provider's dashboard
2. Check that the key is not truncated or modified
3. Verify the key format (should start with `sk-` for DeepSeek)
4. Check if API key has usage limits or is rate-limited

### API Key Leaked in Git

If you accidentally committed an API key:

```bash
# Remove from git history
git filter-branch --force --index-filter \
  'git rm --cached --ignore-unmatch .env' \
  --prune-empty --tag-name-filter cat -- --all

# Force push to repo (be careful!)
git push origin --force --all

# Immediately rotate the API key
```

## File Locations

| File | Purpose |
|------|---------|
| `.env.example` | Template for `.env` file - commit to git, shows what keys are needed |
| `.env` | Your actual API keys - NOT committed to git (in `.gitignore`) |
| `.gitignore` | Excludes `.env` and other secret files from git |
| `src/main/java/.../ChatbotServer.java` | Reads `DEEPSEEK_API_KEY` and `OPENAI_API_KEY` on startup |
| `src/main/java/.../DeepSeekChatbot.java` | Uses API key to initialize DeepSeek model |

## See Also

- [DeepSeek API Documentation](https://platform.deepseek.com/docs)
- [Environment Variable Best Practices](https://12factor.net/config)
- [OWASP: Secrets Management](https://owasp.org/www-community/Secrets_Management)
