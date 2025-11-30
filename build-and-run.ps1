#!/usr/bin/env pwsh

###############################################################################
# Build and Run Script for LangChain4j Chatbot (Windows PowerShell)
#
# Usage:
#   .\build-and-run.ps1              # Clean, compile, and run backend
#   .\build-and-run.ps1 -SkipTest    # Skip tests during build
#
# This script:
#   1. Cleans previous build artifacts
#   2. Compiles the Java backend
#   3. Runs the application with Server profile
#
# Note: If you get "cannot be loaded because running scripts is disabled",
# run this first:
#   Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
#
###############################################################################

param(
    [switch]$SkipTest = $false
)

# Colors for output
$ColorReset = [char]27 + "[0m"
$ColorRed = [char]27 + "[91m"
$ColorGreen = [char]27 + "[92m"
$ColorYellow = [char]27 + "[93m"
$ColorBlue = [char]27 + "[94m"

# Configuration
$projectName = "LangChain4j Chatbot"
$buildOpts = ""

if ($SkipTest) {
    $buildOpts = "-DskipTests"
    Write-Host "${ColorYellow}Note: Tests will be skipped${ColorReset}"
}

# Print header
Write-Host ""
Write-Host "${ColorBlue}╔════════════════════════════════════════════════════════════════╗${ColorReset}"
Write-Host "${ColorBlue}║${ColorReset}  ${projectName} - Build & Run Script"
Write-Host "${ColorBlue}╚════════════════════════════════════════════════════════════════╝${ColorReset}"
Write-Host ""

# Check prerequisites
Write-Host "${ColorYellow}Checking prerequisites...${ColorReset}"

# Check Maven
$mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCmd) {
    Write-Host "${ColorRed}✗ Maven not found. Please install Maven.${ColorReset}"
    Write-Host "  Download: https://maven.apache.org/download.cgi"
    exit 1
}
$mvnVersion = & mvn -v 2>$null | Select-Object -First 1
Write-Host "${ColorGreen}✓ Maven found: $mvnVersion${ColorReset}"

# Check Java
$javaCmd = Get-Command java -ErrorAction SilentlyContinue
if (-not $javaCmd) {
    Write-Host "${ColorRed}✗ Java not found. Please install Java 21+.${ColorReset}"
    exit 1
}
$javaVersion = & java -version 2>&1 | Select-Object -First 1
Write-Host "${ColorGreen}✓ Java found: $javaVersion${ColorReset}"

# Check environment variables
Write-Host ""
Write-Host "${ColorYellow}Checking environment...${ColorReset}"
if ([string]::IsNullOrEmpty($env:DEEPSEEK_API_KEY)) {
    Write-Host "${ColorRed}✗ DEEPSEEK_API_KEY not set${ColorReset}"
    Write-Host "${ColorYellow}  Solution 1: Set manually${ColorReset}"
    Write-Host '    $env:DEEPSEEK_API_KEY = "sk-your-key-here"'
    Write-Host "${ColorYellow}  Solution 2: Load from .env${ColorReset}"
    Write-Host '    Get-Content .env | ForEach-Object {'
    Write-Host '      $name, $value = $_ -split "=", 2'
    Write-Host '      [Environment]::SetEnvironmentVariable($name, $value)'
    Write-Host '    }'
    exit 1
}
Write-Host "${ColorGreen}✓ DEEPSEEK_API_KEY is set${ColorReset}"

# Step 1: Clean
Write-Host ""
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
Write-Host "${ColorYellow}Step 1/3: Cleaning previous build...${ColorReset}"
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
& mvn clean
if ($LASTEXITCODE -ne 0) {
    Write-Host "${ColorRed}✗ Clean failed${ColorReset}"
    exit 1
}

# Step 2: Compile
Write-Host ""
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
Write-Host "${ColorYellow}Step 2/3: Compiling Java code...${ColorReset}"
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
if ($buildOpts) {
    & mvn compile $buildOpts.Split()
} else {
    & mvn compile
}

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "${ColorRed}✗ Compilation failed${ColorReset}"
    exit 1
}
Write-Host ""
Write-Host "${ColorGreen}✓ Compilation successful${ColorReset}"

# Step 3: Run
Write-Host ""
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
Write-Host "${ColorYellow}Step 3/3: Starting application...${ColorReset}"
Write-Host "${ColorBlue}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${ColorReset}"
Write-Host ""
Write-Host "${ColorGreen}Backend will run at: http://localhost:8080${ColorReset}"
Write-Host "  • Chat endpoint: POST /api/chat"
Write-Host "  • Stream endpoint: POST /api/chat/stream"
Write-Host "  • Health check: GET /api/health"
Write-Host ""
Write-Host "${ColorGreen}In another terminal (PowerShell), run:${ColorReset}"
Write-Host "  cd frontend; npm run dev"
Write-Host ""
Write-Host "${ColorGreen}Then open: http://localhost:5173${ColorReset}"
Write-Host ""

# Run the application
& mvn exec:java -Pserver
