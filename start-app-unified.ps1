# Start both backend and frontend in unified mode (single terminal with concurrent output)
# Usage: .\start-app-unified.ps1
# Press Ctrl+C to stop both services

$ErrorActionPreference = "Stop"

# Check for DEEPSEEK_API_KEY
if (-not $env:DEEPSEEK_API_KEY) {
    Write-Host "ERROR: DEEPSEEK_API_KEY environment variable is not set." -ForegroundColor Red
    Write-Host "Please set it: `$env:DEEPSEEK_API_KEY = 'your_api_key'" -ForegroundColor Yellow
    exit 1
}

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  LangChain4j Chatbot - Dev Server" -ForegroundColor White
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "  Backend:  http://localhost:8080" -ForegroundColor Green
Write-Host "  Frontend: http://localhost:5173" -ForegroundColor Green
Write-Host ""
Write-Host "  Press Ctrl+C to stop all services" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Register cleanup handler
$backendProcess = $null
$frontendProcess = $null

$cleanup = {
    Write-Host "`nShutting down..." -ForegroundColor Yellow
    if ($backendProcess -and !$backendProcess.HasExited) {
        Stop-Process -Id $backendProcess.Id -Force -ErrorAction SilentlyContinue
    }
    if ($frontendProcess -and !$frontendProcess.HasExited) {
        Stop-Process -Id $frontendProcess.Id -Force -ErrorAction SilentlyContinue
    }
}

try {
    # Start backend process
    Write-Host "[BACKEND] Starting..." -ForegroundColor Green
    $backendProcess = Start-Process -FilePath "mvn" -ArgumentList "compile", "exec:java", "-Pserver" -WorkingDirectory $scriptDir -PassThru -NoNewWindow -RedirectStandardOutput "$scriptDir\backend.log" -RedirectStandardError "$scriptDir\backend-error.log"

    # Wait for backend to be ready
    Write-Host "[BACKEND] Waiting for backend to start..." -ForegroundColor Yellow
    $maxWait = 30
    $waited = 0
    while ($waited -lt $maxWait) {
        Start-Sleep -Seconds 1
        $waited++
        try {
            $response = Invoke-WebRequest -Uri "http://localhost:8080/api/health" -TimeoutSec 2 -ErrorAction SilentlyContinue
            if ($response.StatusCode -eq 200) {
                Write-Host "[BACKEND] Ready!" -ForegroundColor Green
                break
            }
        } catch {
            Write-Host "." -NoNewline -ForegroundColor Gray
        }
    }
    Write-Host ""

    if ($waited -ge $maxWait) {
        Write-Host "[BACKEND] Warning: Backend may not be fully ready" -ForegroundColor Yellow
    }

    # Start frontend process
    Write-Host "[FRONTEND] Starting..." -ForegroundColor Green
    $frontendProcess = Start-Process -FilePath "npm" -ArgumentList "run", "dev" -WorkingDirectory "$scriptDir\frontend" -PassThru -NoNewWindow -RedirectStandardOutput "$scriptDir\frontend.log" -RedirectStandardError "$scriptDir\frontend-error.log"

    # Wait a moment then open browser
    Start-Sleep -Seconds 3
    Write-Host "[BROWSER] Opening http://localhost:5173" -ForegroundColor Cyan
    Start-Process "http://localhost:5173"

    Write-Host ""
    Write-Host "Services running. Tailing logs (Ctrl+C to stop)..." -ForegroundColor Cyan
    Write-Host ""

    # Tail both log files
    while ($true) {
        if (Test-Path "$scriptDir\backend.log") {
            $backendLogs = Get-Content "$scriptDir\backend.log" -Tail 5 -ErrorAction SilentlyContinue
            if ($backendLogs) {
                foreach ($line in $backendLogs) {
                    Write-Host "[BE] $line" -ForegroundColor Gray
                }
            }
        }
        if (Test-Path "$scriptDir\frontend.log") {
            $frontendLogs = Get-Content "$scriptDir\frontend.log" -Tail 5 -ErrorAction SilentlyContinue
            if ($frontendLogs) {
                foreach ($line in $frontendLogs) {
                    Write-Host "[FE] $line" -ForegroundColor DarkCyan
                }
            }
        }

        # Check if processes are still running
        if ($backendProcess.HasExited -and $frontendProcess.HasExited) {
            Write-Host "Both services have stopped." -ForegroundColor Yellow
            break
        }

        Start-Sleep -Seconds 2
    }
}
finally {
    & $cleanup
    # Clean up log files
    Remove-Item "$scriptDir\backend.log" -ErrorAction SilentlyContinue
    Remove-Item "$scriptDir\backend-error.log" -ErrorAction SilentlyContinue
    Remove-Item "$scriptDir\frontend.log" -ErrorAction SilentlyContinue
    Remove-Item "$scriptDir\frontend-error.log" -ErrorAction SilentlyContinue
}
