#!/bin/bash

###############################################################################
# Test API Keys Script
#
# Tests if your API keys work by making real requests to the backend
#
# Usage:
#   ./test-api-keys.sh              (Test with default message)
#   ./test-api-keys.sh "your message"  (Test with custom message)
#
# Prerequisites:
#   1. Backend must be running (run ./build-and-run.sh first)
#   2. API keys must be set (export $(cat .env | xargs))
#
###############################################################################

set -e

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Configuration
API_BASE="http://localhost:8080/api"
MESSAGE="${1:-What is 2+2?}"
TIMEOUT=10

echo -e "${BLUE}╔════════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║${NC}  API Key Test Script"
echo -e "${BLUE}╚════════════════════════════════════════════════════════════════╝${NC}"
echo ""

# Test 1: Check if backend is running
echo -e "${YELLOW}Test 1: Checking if backend is running...${NC}"
if curl -s --max-time $TIMEOUT "$API_BASE/health" > /dev/null 2>&1; then
    echo -e "${GREEN}✓ Backend is running on $API_BASE${NC}"
else
    echo -e "${RED}✗ Backend is NOT running${NC}"
    echo ""
    echo "Solution:"
    echo "  1. Open new terminal"
    echo "  2. Load API keys: export \$(cat .env | xargs)"
    echo "  3. Start backend: ./build-and-run.sh"
    echo "  4. Wait for: 'ChatbotServer started on port 8080'"
    echo "  5. Run this script again"
    exit 1
fi

# Test 2: Check health endpoint
echo ""
echo -e "${YELLOW}Test 2: Testing health endpoint...${NC}"
HEALTH_RESPONSE=$(curl -s "$API_BASE/health")
echo -e "${CYAN}Response: $HEALTH_RESPONSE${NC}"

if echo "$HEALTH_RESPONSE" | grep -q "ok"; then
    echo -e "${GREEN}✓ Health check passed${NC}"
else
    echo -e "${RED}✗ Health check failed${NC}"
    exit 1
fi

# Test 3: Test non-streaming chat (THIS TESTS API KEY)
echo ""
echo -e "${YELLOW}Test 3: Testing chat endpoint with API key...${NC}"
echo -e "${CYAN}Sending message: \"$MESSAGE\"${NC}"
echo ""

CHAT_RESPONSE=$(curl -s -X POST "$API_BASE/chat" \
  -H "Content-Type: application/json" \
  -d "{\"message\": \"$MESSAGE\"}" \
  --max-time $TIMEOUT)

echo -e "${CYAN}Response:${NC}"
echo "$CHAT_RESPONSE" | jq '.' 2>/dev/null || echo "$CHAT_RESPONSE"
echo ""

# Parse response to check for errors
if echo "$CHAT_RESPONSE" | grep -q '"response"'; then
    echo -e "${GREEN}✓ API Key works! Got response from AI${NC}"

    # Extract and display response
    RESPONSE=$(echo "$CHAT_RESPONSE" | jq -r '.response' 2>/dev/null || echo "")
    if [ -n "$RESPONSE" ]; then
        echo ""
        echo -e "${CYAN}AI Response: $RESPONSE${NC}"
    fi

    # Show token usage
    INPUT_TOKENS=$(echo "$CHAT_RESPONSE" | jq -r '.usage.inputTokens' 2>/dev/null || echo "?")
    OUTPUT_TOKENS=$(echo "$CHAT_RESPONSE" | jq -r '.usage.outputTokens' 2>/dev/null || echo "?")
    COST=$(echo "$CHAT_RESPONSE" | jq -r '.usage.estimatedCost' 2>/dev/null || echo "?")

    echo ""
    echo -e "${CYAN}Token Usage:${NC}"
    echo "  Input tokens:  $INPUT_TOKENS"
    echo "  Output tokens: $OUTPUT_TOKENS"
    echo "  Estimated cost: \$$COST"

elif echo "$CHAT_RESPONSE" | grep -q '"code"'; then
    ERROR_CODE=$(echo "$CHAT_RESPONSE" | jq -r '.code' 2>/dev/null || echo "UNKNOWN")
    ERROR_MESSAGE=$(echo "$CHAT_RESPONSE" | jq -r '.message' 2>/dev/null || echo "Unknown error")
    ERROR_ID=$(echo "$CHAT_RESPONSE" | jq -r '.errorId' 2>/dev/null || echo "?")

    echo -e "${RED}✗ API request failed${NC}"
    echo ""
    echo -e "${YELLOW}Error Details:${NC}"
    echo "  Error ID:   $ERROR_ID"
    echo "  Error Code: $ERROR_CODE"
    echo "  Message:    $ERROR_MESSAGE"
    echo ""

    case "$ERROR_CODE" in
        "AUTH_FAILED")
            echo -e "${YELLOW}Solution: Your API key is invalid${NC}"
            echo "  1. Go to: https://platform.deepseek.com/"
            echo "  2. Create a new API key"
            echo "  3. Update .env with new key"
            echo "  4. Restart backend"
            echo "  5. Run this test again"
            ;;
        "RATE_LIMITED")
            echo -e "${YELLOW}Solution: You're being rate limited${NC}"
            echo "  1. Wait 30-60 seconds"
            echo "  2. Run this test again"
            ;;
        "MODEL_ERROR")
            echo -e "${YELLOW}Solution: API is having issues${NC}"
            echo "  1. Check API status page"
            echo "  2. Check your network connection"
            echo "  3. Try again in a few moments"
            ;;
        *)
            echo -e "${YELLOW}See DEBUGGING_CHECKLIST.md for help${NC}"
            ;;
    esac

    exit 1
else
    echo -e "${RED}✗ Unexpected response${NC}"
    echo "Response: $CHAT_RESPONSE"
    exit 1
fi

# Test 4: Test streaming chat
echo ""
echo -e "${YELLOW}Test 4: Testing streaming chat endpoint...${NC}"
echo -e "${CYAN}Testing streaming with message: \"$MESSAGE\"${NC}"
echo ""

STREAM_RESPONSE=$(curl -s -X POST "$API_BASE/chat/stream" \
  -H "Content-Type: application/json" \
  -d "{\"message\": \"$MESSAGE\"}" \
  -N \
  --max-time $TIMEOUT | head -20)

if echo "$STREAM_RESPONSE" | grep -q 'event:'; then
    echo -e "${GREEN}✓ Streaming works!${NC}"
    echo ""
    echo -e "${CYAN}First few events:${NC}"
    echo "$STREAM_RESPONSE" | head -10
else
    echo -e "${YELLOW}⚠ Streaming test inconclusive (may have timed out)${NC}"
fi

# Summary
echo ""
echo -e "${BLUE}════════════════════════════════════════════════════════════════${NC}"
echo -e "${GREEN}✓ API Key Tests Complete!${NC}"
echo ""
echo -e "${CYAN}Your API keys are working. You can now:${NC}"
echo "  1. Start the frontend: cd frontend && npm run dev"
echo "  2. Open http://localhost:5173"
echo "  3. Start chatting!"
echo ""
