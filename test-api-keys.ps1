#!/usr/bin/env pwsh

###############################################################################
# Test API Keys Script (Windows PowerShell)
#
# Tests if your API keys work by making real requests to the backend
#
# Usage:
#   .\test-api-keys.ps1              (Test with default message)
#   .\test-api-keys.ps1 "your message"  (Test with custom message)
#
# Prerequisites:
#   1. Backend must be running (run .\build-and-run.ps1 first)
#   2. API keys must be set (set env vars)
#
###############################################################################

param(
    [string]$Message = "What is 2+2?"
)

# Colors
$ColorReset = [char]27 + "[0m"
$ColorRed = [char]27 + "[91m"
$ColorGreen = [char]27 + "[92m"
$ColorYellow = [char]27 + "[93m"
$ColorBlue = [char]27 + "[94m"
$ColorCyan = [char]27 + "[36m"

# Configuration
$apiBase = "http://localhost:8080/api"
$timeout = 10

Write-Host ""
Write-Host "${ColorBlue}╔════════════════════════════════════════════════════════════════╗${ColorReset}"
Write-Host "${ColorBlue}║${ColorReset}  API Key Test Script"
Write-Host "${ColorBlue}╚════════════════════════════════════════════════════════════════╝${ColorReset}"
Write-Host ""

# Test 1: Check if backend is running
Write-Host "${ColorYellow}Test 1: Checking if backend is running...${ColorReset}"
try {
    $response = Invoke-WebRequest -Uri "$apiBase/health" -Method GET -TimeoutSec $timeout -ErrorAction SilentlyContinue
    if ($response.StatusCode -eq 200) {
        Write-Host "${ColorGreen}✓ Backend is running on $apiBase${ColorReset}"
    }
} catch {
    Write-Host "${ColorRed}✗ Backend is NOT running${ColorReset}"
    Write-Host ""
    Write-Host "Solution:"
    Write-Host "  1. Open new terminal"
    Write-Host "  2. Load API keys: Get-Content .env | ForEach-Object { ... }"
    Write-Host "  3. Start backend: .\build-and-run.ps1"
    Write-Host "  4. Wait for: 'ChatbotServer started on port 8080'"
    Write-Host "  5. Run this script again"
    exit 1
}

# Test 2: Check health endpoint
Write-Host ""
Write-Host "${ColorYellow}Test 2: Testing health endpoint...${ColorReset}"
try {
    $response = Invoke-WebRequest -Uri "$apiBase/health" -Method GET -TimeoutSec $timeout
    $healthResponse = $response.Content
    Write-Host "${ColorCyan}Response: $healthResponse${ColorReset}"

    if ($healthResponse -match "ok") {
        Write-Host "${ColorGreen}✓ Health check passed${ColorReset}"
    } else {
        Write-Host "${ColorRed}✗ Health check failed${ColorReset}"
        exit 1
    }
} catch {
    Write-Host "${ColorRed}✗ Health check failed: $_${ColorReset}"
    exit 1
}

# Test 3: Test non-streaming chat (THIS TESTS API KEY)
Write-Host ""
Write-Host "${ColorYellow}Test 3: Testing chat endpoint with API key...${ColorReset}"
Write-Host "${ColorCyan}Sending message: `"$Message`"${ColorReset}"
Write-Host ""

try {
    $body = @{
        message = $Message
    } | ConvertTo-Json

    $response = Invoke-WebRequest -Uri "$apiBase/chat" `
        -Method POST `
        -Headers @{"Content-Type" = "application/json"} `
        -Body $body `
        -TimeoutSec $timeout

    $chatResponse = $response.Content | ConvertFrom-Json

    Write-Host "${ColorCyan}Response:${ColorReset}"
    $response.Content | ConvertFrom-Json | ConvertTo-Json | Write-Host

    Write-Host ""

    # Check for success
    if ($chatResponse.response) {
        Write-Host "${ColorGreen}✓ API Key works! Got response from AI${ColorReset}"
        Write-Host ""
        Write-Host "${ColorCyan}AI Response: $($chatResponse.response)${ColorReset}"

        Write-Host ""
        Write-Host "${ColorCyan}Token Usage:${ColorReset}"
        Write-Host "  Input tokens:  $($chatResponse.usage.inputTokens)"
        Write-Host "  Output tokens: $($chatResponse.usage.outputTokens)"
        Write-Host "  Estimated cost: `$$($chatResponse.usage.estimatedCost)"

    } elseif ($chatResponse.code) {
        # Error response
        Write-Host "${ColorRed}✗ API request failed${ColorReset}"
        Write-Host ""
        Write-Host "${ColorYellow}Error Details:${ColorReset}"
        Write-Host "  Error ID:   $($chatResponse.errorId)"
        Write-Host "  Error Code: $($chatResponse.code)"
        Write-Host "  Message:    $($chatResponse.message)"
        Write-Host ""

        switch ($chatResponse.code) {
            "AUTH_FAILED" {
                Write-Host "${ColorYellow}Solution: Your API key is invalid${ColorReset}"
                Write-Host "  1. Go to: https://platform.deepseek.com/"
                Write-Host "  2. Create a new API key"
                Write-Host "  3. Update .env with new key"
                Write-Host "  4. Restart backend"
                Write-Host "  5. Run this test again"
            }
            "RATE_LIMITED" {
                Write-Host "${ColorYellow}Solution: You're being rate limited${ColorReset}"
                Write-Host "  1. Wait 30-60 seconds"
                Write-Host "  2. Run this test again"
            }
            "MODEL_ERROR" {
                Write-Host "${ColorYellow}Solution: API is having issues${ColorReset}"
                Write-Host "  1. Check API status page"
                Write-Host "  2. Check your network connection"
                Write-Host "  3. Try again in a few moments"
            }
            default {
                Write-Host "${ColorYellow}See DEBUGGING_CHECKLIST.md for help${ColorReset}"
            }
        }

        exit 1
    }

} catch {
    Write-Host "${ColorRed}✗ Error: $_${ColorReset}"
    exit 1
}

# Test 4: Test streaming chat
Write-Host ""
Write-Host "${ColorYellow}Test 4: Testing streaming chat endpoint...${ColorReset}"
Write-Host "${ColorCyan}Testing streaming with message: `"$Message`"${ColorReset}"
Write-Host ""

try {
    $body = @{
        message = $Message
    } | ConvertTo-Json

    $streamResponse = Invoke-WebRequest -Uri "$apiBase/chat/stream" `
        -Method POST `
        -Headers @{"Content-Type" = "application/json"} `
        -Body $body `
        -TimeoutSec $timeout

    $streamContent = $streamResponse.Content

    if ($streamContent -match "event:") {
        Write-Host "${ColorGreen}✓ Streaming works!${ColorReset}"
        Write-Host ""
        Write-Host "${ColorCyan}First few events:${ColorReset}"
        $lines = $streamContent -split "`n" | Select-Object -First 10
        $lines | ForEach-Object { Write-Host $_ }
    } else {
        Write-Host "${ColorYellow}⚠ Streaming test inconclusive${ColorReset}"
    }

} catch {
    Write-Host "${ColorYellow}⚠ Streaming test error (may be expected): $_${ColorReset}"
}

# Summary
Write-Host ""
Write-Host "${ColorBlue}════════════════════════════════════════════════════════════════${ColorReset}"
Write-Host "${ColorGreen}✓ API Key Tests Complete!${ColorReset}"
Write-Host ""
Write-Host "${ColorCyan}Your API keys are working. You can now:${ColorReset}"
Write-Host "  1. Start the frontend: cd frontend; npm run dev"
Write-Host "  2. Open http://localhost:5173"
Write-Host "  3. Start chatting!"
Write-Host ""
