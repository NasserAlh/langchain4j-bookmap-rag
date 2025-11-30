# Build & Run Scripts

Quick scripts to clean, compile, and execute the Java backend.

## Available Scripts

### Linux / macOS
**File:** `build-and-run.sh`

```bash
# Make executable (one-time)
chmod +x build-and-run.sh

# Run with environment variables
export $(cat .env | xargs)
./build-and-run.sh

# Skip tests
./build-and-run.sh --skip-test
```

### Windows (Batch)
**File:** `build-and-run.bat`

```cmd
# Set API key first (or load from .env)
set DEEPSEEK_API_KEY=sk-your-key-here

# Run
build-and-run.bat

# Skip tests
build-and-run.bat --skip-test
```

### Windows (PowerShell) - Recommended
**File:** `build-and-run.ps1`

```powershell
# Allow scripts to run (one-time, if needed)
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

# Set API key first (or load from .env)
$env:DEEPSEEK_API_KEY = "sk-your-key-here"

# Run
.\build-and-run.ps1

# Skip tests
.\build-and-run.ps1 -SkipTest
```

## What These Scripts Do

1. **Check Prerequisites**
   - Verify Maven is installed
   - Verify Java 21+ is installed
   - Verify `DEEPSEEK_API_KEY` environment variable is set

2. **Clean**
   - Remove previous build artifacts
   - Clear the `target/` directory

3. **Compile**
   - Compile Java source code
   - Download dependencies
   - Run tests (unless `--skip-test` is used)

4. **Run**
   - Start the backend server on port 8080
   - Display API endpoints
   - Show instructions for starting frontend

## Quick Setup

### Step 1: Load API Key
**Linux/macOS:**
```bash
export $(cat .env | xargs)
```

**Windows PowerShell:**
```powershell
Get-Content .env | ForEach-Object {
    $name, $value = $_ -split '=', 2
    [Environment]::SetEnvironmentVariable($name, $value)
}
```

**Windows Command Prompt:**
```cmd
set DEEPSEEK_API_KEY=sk-your-key-here
```

### Step 2: Run Script

**Linux/macOS:**
```bash
./build-and-run.sh
```

**Windows PowerShell:**
```powershell
.\build-and-run.ps1
```

**Windows Batch:**
```cmd
build-and-run.bat
```

### Step 3: Start Frontend (New Terminal)

```bash
cd frontend
npm run dev
```

### Step 4: Open Browser

Navigate to: **http://localhost:5173**

## Troubleshooting

### "Maven not found"
Install Maven: https://maven.apache.org/download.cgi

### "Java not found"
Install Java 21+: https://www.oracle.com/java/technologies/downloads/

### "DEEPSEEK_API_KEY not set"
1. Make sure you copied `.env.example` to `.env`
2. Add your API key to `.env`
3. Load it before running the script:
   - Linux/macOS: `export $(cat .env | xargs)`
   - PowerShell: See above
   - Batch: `set DEEPSEEK_API_KEY=...`

### "Permission denied" (Linux/macOS)
Make the script executable:
```bash
chmod +x build-and-run.sh
```

### "cannot be loaded because running scripts is disabled" (PowerShell)
Allow scripts to run:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

## Manual Commands

If you prefer to run commands manually:

```bash
# Clean
mvn clean

# Compile
mvn compile

# Run
mvn exec:java -Pserver
```

Or skip compilation and just run:
```bash
mvn exec:java -Pserver
```

## Environment Variables

These scripts check for:
- `DEEPSEEK_API_KEY` - Required, your DeepSeek API key
- `OPENAI_API_KEY` - Optional, for OpenAI support
- `LLM_PROVIDER` - Optional, defaults to `deepseek`
- `PORT` - Optional, defaults to 8080

## Output

Successful build shows:
```
Step 1/3: Cleaning previous build...
[INFO] BUILD SUCCESS

Step 2/3: Compiling Java code...
[INFO] BUILD SUCCESS

Step 3/3: Starting application...
ChatbotServer started on port 8080
```

Then the server is ready to receive requests.

## See Also

- [QUICKSTART.md](QUICKSTART.md) - 5-minute setup guide
- [docs/API_KEY_MANAGEMENT.md](docs/API_KEY_MANAGEMENT.md) - API key setup
- [REFERENCE.md](REFERENCE.md) - Quick reference commands
