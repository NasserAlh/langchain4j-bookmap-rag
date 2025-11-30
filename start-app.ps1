# Start both backend and frontend for development
# Usage: .\start-app.ps1

$ErrorActionPreference = "Stop"

# Check for DEEPSEEK_API_KEY
if (-not $env:DEEPSEEK_API_KEY) {
    Write-Host "ERROR: DEEPSEEK_API_KEY environment variable is not set." -ForegroundColor Red
    Write-Host "Please set it: `$env:DEEPSEEK_API_KEY = 'your_api_key'" -ForegroundColor Yellow
    exit 1
}

Write-Host "Starting LangChain4j Chatbot Application..." -ForegroundColor Cyan
Write-Host ""

# Get the script directory
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path

# Start backend in a new PowerShell window
Write-Host "[Backend] Starting Java backend on port 8080..." -ForegroundColor Green
$backendJob = Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$scriptDir'; Write-Host 'Building and starting backend...' -ForegroundColor Cyan; mvn compile exec:java -Pserver" -PassThru

# Give backend a moment to start
Write-Host "[Backend] Waiting for backend to initialize..." -ForegroundColor Yellow
Start-Sleep -Seconds 5

# Start frontend in a new PowerShell window
Write-Host "[Frontend] Starting Svelte frontend on port 5173..." -ForegroundColor Green
$frontendJob = Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$scriptDir\frontend'; Write-Host 'Starting frontend dev server...' -ForegroundColor Cyan; npm run dev" -PassThru

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Application Starting!" -ForegroundColor White
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "  Backend:  http://localhost:8080" -ForegroundColor Green
Write-Host "  Frontend: http://localhost:5173" -ForegroundColor Green
Write-Host ""
Write-Host "  Press Ctrl+C in each window to stop" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan

# Open browser after a short delay
Start-Sleep -Seconds 3
Write-Host ""
Write-Host "Opening browser..." -ForegroundColor Cyan
Start-Process "http://localhost:5173"
