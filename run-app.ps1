# DeepSeek Chatbot Application Manager
# PowerShell Script for Windows 11

$ErrorActionPreference = "SilentlyContinue"
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ScriptDir

# Configuration
$BackendPort = 8080
$FrontendPort = 5173
$PidFile = "$ScriptDir\.app-pids.json"

# Colors
function Write-Header {
    param([string]$Text)
    Write-Host "`n$('=' * 60)" -ForegroundColor Cyan
    Write-Host "  $Text" -ForegroundColor Cyan
    Write-Host "$('=' * 60)`n" -ForegroundColor Cyan
}

function Write-Success { param([string]$Text) Write-Host "[OK] $Text" -ForegroundColor Green }
function Write-Error { param([string]$Text) Write-Host "[ERROR] $Text" -ForegroundColor Red }
function Write-Info { param([string]$Text) Write-Host "[INFO] $Text" -ForegroundColor Yellow }
function Write-Status { param([string]$Text) Write-Host "  $Text" -ForegroundColor White }

# Check if port is in use
function Test-PortInUse {
    param([int]$Port)
    $connection = Get-NetTCPConnection -LocalPort $Port -ErrorAction SilentlyContinue
    return $null -ne $connection
}

# Get process using port
function Get-ProcessByPort {
    param([int]$Port)
    $connection = Get-NetTCPConnection -LocalPort $Port -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($connection) {
        return Get-Process -Id $connection.OwningProcess -ErrorAction SilentlyContinue
    }
    return $null
}

# Save PIDs to file
function Save-Pids {
    param([int]$BackendPid, [int]$FrontendPid)
    @{
        BackendPid = $BackendPid
        FrontendPid = $FrontendPid
        StartTime = (Get-Date).ToString("yyyy-MM-dd HH:mm:ss")
    } | ConvertTo-Json | Set-Content $PidFile
}

# Load PIDs from file
function Get-SavedPids {
    if (Test-Path $PidFile) {
        return Get-Content $PidFile | ConvertFrom-Json
    }
    return $null
}

# Clear PIDs file
function Clear-Pids {
    if (Test-Path $PidFile) {
        Remove-Item $PidFile -Force
    }
}

# Check prerequisites
function Test-Prerequisites {
    Write-Info "Checking prerequisites..."

    $allGood = $true

    # Check Java
    $javaVersion = java -version 2>&1 | Select-String "version"
    if ($javaVersion) {
        Write-Success "Java: $($javaVersion -replace '.*version "([^"]+)".*', '$1')"
    } else {
        Write-Error "Java not found"
        $allGood = $false
    }

    # Check Maven
    $mvnVersion = mvn -version 2>&1 | Select-String "Apache Maven"
    if ($mvnVersion) {
        Write-Success "Maven: $($mvnVersion -replace 'Apache Maven ([^ ]+).*', '$1')"
    } else {
        Write-Error "Maven not found"
        $allGood = $false
    }

    # Check Node.js
    $nodeVersion = node -v 2>&1
    if ($nodeVersion -match "^v") {
        Write-Success "Node.js: $nodeVersion"
    } else {
        Write-Error "Node.js not found"
        $allGood = $false
    }

    # Check npm
    $npmVersion = npm -v 2>&1
    if ($npmVersion -match "^\d") {
        Write-Success "npm: $npmVersion"
    } else {
        Write-Error "npm not found"
        $allGood = $false
    }

    # Check DEEPSEEK_API_KEY
    if ($env:DEEPSEEK_API_KEY) {
        Write-Success "DEEPSEEK_API_KEY: Set ($(($env:DEEPSEEK_API_KEY).Substring(0,8))...)"
    } else {
        Write-Error "DEEPSEEK_API_KEY not set"
        $allGood = $false
    }

    return $allGood
}

# Build the project
function Build-Project {
    Write-Info "Building Java backend..."
    $result = & mvn compile -q 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Backend built successfully"
        return $true
    } else {
        Write-Error "Backend build failed"
        Write-Host $result -ForegroundColor Red
        return $false
    }
}

# Install frontend dependencies
function Install-FrontendDeps {
    if (-not (Test-Path "$ScriptDir\frontend\node_modules")) {
        Write-Info "Installing frontend dependencies..."
        Push-Location "$ScriptDir\frontend"
        npm install 2>&1 | Out-Null
        Pop-Location
        if ($LASTEXITCODE -eq 0) {
            Write-Success "Frontend dependencies installed"
        } else {
            Write-Error "Failed to install frontend dependencies"
            return $false
        }
    }
    return $true
}

# Start backend server
function Start-Backend {
    Write-Info "Starting backend server on port $BackendPort..."

    if (Test-PortInUse $BackendPort) {
        $proc = Get-ProcessByPort $BackendPort
        Write-Error "Port $BackendPort already in use by $($proc.ProcessName) (PID: $($proc.Id))"
        return $null
    }

    # Use cmd /c to properly start Maven in background on Windows
    $process = Start-Process -FilePath "cmd.exe" -ArgumentList "/c", "mvn exec:java -Pserver" `
        -WorkingDirectory $ScriptDir -PassThru -WindowStyle Hidden

    # Wait for server to start
    $maxWait = 45
    $waited = 0
    while (-not (Test-PortInUse $BackendPort) -and $waited -lt $maxWait) {
        Start-Sleep -Seconds 1
        $waited++
        Write-Host "." -NoNewline
    }
    Write-Host ""

    if (Test-PortInUse $BackendPort) {
        Write-Success "Backend started (PID: $($process.Id)) in $waited seconds"
        return $process.Id
    } else {
        Write-Error "Backend failed to start within $maxWait seconds"
        Write-Info "Try running 'mvn exec:java -Pserver' manually to see errors"
        return $null
    }
}

# Start frontend dev server
function Start-Frontend {
    Write-Info "Starting frontend server on port $FrontendPort..."

    if (Test-PortInUse $FrontendPort) {
        $proc = Get-ProcessByPort $FrontendPort
        Write-Error "Port $FrontendPort already in use by $($proc.ProcessName) (PID: $($proc.Id))"
        return $null
    }

    # Use cmd /c to properly start npm in background on Windows
    $process = Start-Process -FilePath "cmd.exe" -ArgumentList "/c", "npm run dev" `
        -WorkingDirectory "$ScriptDir\frontend" -PassThru -WindowStyle Hidden

    # Wait for server to start (increased timeout for first-time compilation)
    $maxWait = 60
    $waited = 0
    while (-not (Test-PortInUse $FrontendPort) -and $waited -lt $maxWait) {
        Start-Sleep -Seconds 1
        $waited++
        Write-Host "." -NoNewline
    }
    Write-Host ""

    if (Test-PortInUse $FrontendPort) {
        Write-Success "Frontend started (PID: $($process.Id)) in $waited seconds"
        return $process.Id
    } else {
        Write-Error "Frontend failed to start within $maxWait seconds"
        Write-Info "Try running 'cd frontend && npm run dev' manually to see errors"
        return $null
    }
}

# Start console mode
function Start-ConsoleMode {
    Write-Info "Starting chatbot in console mode..."
    & mvn exec:java
}

# Show application status
function Show-Status {
    Write-Header "Application Status"

    # Backend status
    Write-Host "Backend (Java/Javalin):" -ForegroundColor White
    if (Test-PortInUse $BackendPort) {
        $proc = Get-ProcessByPort $BackendPort
        Write-Success "  Running on http://localhost:$BackendPort"
        Write-Status "  Process: $($proc.ProcessName) (PID: $($proc.Id))"
        Write-Status "  Memory: $([math]::Round($proc.WorkingSet64 / 1MB, 2)) MB"

        # Test health endpoint
        try {
            $health = Invoke-RestMethod -Uri "http://localhost:$BackendPort/api/health" -TimeoutSec 2
            Write-Success "  Health: $($health.status)"
        } catch {
            Write-Error "  Health check failed"
        }
    } else {
        Write-Error "  Not running"
    }

    Write-Host ""

    # Frontend status
    Write-Host "Frontend (SvelteKit):" -ForegroundColor White
    if (Test-PortInUse $FrontendPort) {
        $proc = Get-ProcessByPort $FrontendPort
        Write-Success "  Running on http://localhost:$FrontendPort"
        Write-Status "  Process: $($proc.ProcessName) (PID: $($proc.Id))"
    } else {
        Write-Error "  Not running"
    }

    Write-Host ""

    # Show saved PIDs info
    $savedPids = Get-SavedPids
    if ($savedPids) {
        Write-Host "Session Info:" -ForegroundColor White
        Write-Status "  Started: $($savedPids.StartTime)"
    }
}

# Kill process tree recursively
function Stop-ProcessTree {
    param([int]$ProcessId)

    # Get all child processes
    Get-CimInstance Win32_Process | Where-Object { $_.ParentProcessId -eq $ProcessId } | ForEach-Object {
        Stop-ProcessTree -ProcessId $_.ProcessId
    }

    # Kill the process itself
    Stop-Process -Id $ProcessId -Force -ErrorAction SilentlyContinue
}

# Stop application
function Stop-Application {
    Write-Info "Stopping application..."

    $stopped = $false

    # Stop backend (find Java process on port 8080)
    if (Test-PortInUse $BackendPort) {
        $proc = Get-ProcessByPort $BackendPort
        if ($proc) {
            Write-Info "Stopping backend (PID: $($proc.Id))..."
            Stop-ProcessTree -ProcessId $proc.Id
            Start-Sleep -Milliseconds 500

            # Double-check and force kill any remaining java processes on the port
            if (Test-PortInUse $BackendPort) {
                $proc = Get-ProcessByPort $BackendPort
                if ($proc) {
                    Stop-Process -Id $proc.Id -Force -ErrorAction SilentlyContinue
                }
            }
            Write-Success "Backend stopped"
            $stopped = $true
        }
    } else {
        Write-Info "Backend was not running"
    }

    # Stop frontend (find Node process on port 5173)
    if (Test-PortInUse $FrontendPort) {
        $proc = Get-ProcessByPort $FrontendPort
        if ($proc) {
            Write-Info "Stopping frontend (PID: $($proc.Id))..."
            Stop-ProcessTree -ProcessId $proc.Id
            Start-Sleep -Milliseconds 500

            # Double-check and force kill any remaining node processes on the port
            if (Test-PortInUse $FrontendPort) {
                $proc = Get-ProcessByPort $FrontendPort
                if ($proc) {
                    Stop-Process -Id $proc.Id -Force -ErrorAction SilentlyContinue
                }
            }
            Write-Success "Frontend stopped"
            $stopped = $true
        }
    } else {
        Write-Info "Frontend was not running"
    }

    Clear-Pids

    if ($stopped) {
        Write-Success "Application stopped successfully"
    }
}

# Open browser
function Open-Browser {
    if (Test-PortInUse $FrontendPort) {
        Write-Info "Opening browser..."
        Start-Process "http://localhost:$FrontendPort"
    } else {
        Write-Error "Frontend is not running"
    }
}

# View logs
function Show-Logs {
    Write-Header "Recent Backend Logs"
    Write-Info "Checking backend health and settings..."

    try {
        $health = Invoke-RestMethod -Uri "http://localhost:$BackendPort/api/health" -TimeoutSec 2
        Write-Success "Health: $($health.status)"

        $settings = Invoke-RestMethod -Uri "http://localhost:$BackendPort/api/settings" -TimeoutSec 2
        Write-Host "`nCurrent Settings:" -ForegroundColor White
        Write-Status "  Model: $($settings.model)"
        Write-Status "  Temperature: $($settings.temperature)"
        Write-Status "  RAG Enabled: $($settings.ragEnabled)"
        Write-Status "  Streaming: $($settings.streamingEnabled)"
    } catch {
        Write-Error "Could not fetch backend info. Is the server running?"
    }
}

# Rebuild project
function Rebuild-Project {
    Write-Info "Rebuilding project..."

    # Clean and compile backend
    Write-Info "Cleaning and compiling backend..."
    & mvn clean compile -q
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Backend rebuilt successfully"
    } else {
        Write-Error "Backend rebuild failed"
    }

    # Reinstall frontend deps
    Write-Info "Reinstalling frontend dependencies..."
    Push-Location "$ScriptDir\frontend"
    Remove-Item -Recurse -Force "node_modules" -ErrorAction SilentlyContinue
    npm install 2>&1 | Out-Null
    Pop-Location
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Frontend rebuilt successfully"
    } else {
        Write-Error "Frontend rebuild failed"
    }
}

# Main menu
function Show-Menu {
    Write-Header "DeepSeek Chatbot Manager"

    Write-Host "  1. Start App with GUI (Backend + Frontend)" -ForegroundColor White
    Write-Host "  2. Start App in Console Mode" -ForegroundColor White
    Write-Host "  3. Show Current Status" -ForegroundColor White
    Write-Host "  4. Stop Application" -ForegroundColor White
    Write-Host "  5. Open Browser" -ForegroundColor White
    Write-Host "  6. Check Prerequisites" -ForegroundColor White
    Write-Host "  7. View Backend Info" -ForegroundColor White
    Write-Host "  8. Rebuild Project" -ForegroundColor White
    Write-Host "  9. Start Backend Only" -ForegroundColor White
    Write-Host " 10. Start Frontend Only" -ForegroundColor White
    Write-Host ""
    Write-Host "  0. Exit" -ForegroundColor Gray
    Write-Host ""

    $choice = Read-Host "Select option"
    return $choice
}

# Main loop
$running = $true
while ($running) {
    $choice = Show-Menu

    switch ($choice) {
        "1" {
            Write-Header "Starting App with GUI"
            if (-not (Test-Prerequisites)) {
                Write-Error "Prerequisites check failed. Please fix the issues above."
                break
            }
            if (-not (Build-Project)) { break }
            if (-not (Install-FrontendDeps)) { break }

            $backendPid = Start-Backend
            if ($backendPid) {
                $frontendPid = Start-Frontend
                if ($frontendPid) {
                    Save-Pids -BackendPid $backendPid -FrontendPid $frontendPid
                    Write-Host ""
                    Write-Success "Application started successfully!"
                    Write-Info "Frontend: http://localhost:$FrontendPort"
                    Write-Info "Backend:  http://localhost:$BackendPort"

                    $openBrowser = Read-Host "`nOpen browser now? (Y/n)"
                    if ($openBrowser -ne "n" -and $openBrowser -ne "N") {
                        Open-Browser
                    }
                }
            }
        }
        "2" {
            Write-Header "Starting Console Mode"
            if (-not (Test-Prerequisites)) {
                Write-Error "Prerequisites check failed"
                break
            }
            if (-not (Build-Project)) { break }
            Start-ConsoleMode
        }
        "3" {
            Show-Status
        }
        "4" {
            Stop-Application
        }
        "5" {
            Open-Browser
        }
        "6" {
            Write-Header "Prerequisites Check"
            Test-Prerequisites | Out-Null
        }
        "7" {
            Show-Logs
        }
        "8" {
            Write-Header "Rebuild Project"
            Stop-Application
            Rebuild-Project
        }
        "9" {
            Write-Header "Starting Backend Only"
            if (-not (Build-Project)) { break }
            Start-Backend | Out-Null
        }
        "10" {
            Write-Header "Starting Frontend Only"
            if (-not (Install-FrontendDeps)) { break }
            Start-Frontend | Out-Null
        }
        "0" {
            Write-Info "Goodbye!"
            $running = $false
        }
        default {
            Write-Error "Invalid option: $choice"
        }
    }

    if ($running) {
        Write-Host ""
        Read-Host "Press Enter to continue"
    }
}
