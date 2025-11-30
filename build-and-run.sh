#!/bin/bash

###############################################################################
# Build and Run Script for LangChain4j Chatbot
#
# Usage:
#   ./build-and-run.sh           # Clean, compile, and run backend
#   ./build-and-run.sh --skip-test  # Skip tests during build
#
# This script:
#   1. Cleans previous build artifacts
#   2. Compiles the Java backend
#   3. Runs the application with Server profile
#
###############################################################################

set -e  # Exit on error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_NAME="LangChain4j Chatbot"
BUILD_OPTS=""

# Parse arguments
if [[ "$1" == "--skip-test" ]]; then
    BUILD_OPTS="-DskipTests"
    echo -e "${YELLOW}Note: Tests will be skipped${NC}"
fi

# Print header
echo -e "${BLUE}╔════════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║${NC}  ${PROJECT_NAME} - Build & Run Script"
echo -e "${BLUE}╚════════════════════════════════════════════════════════════════╝${NC}"
echo ""

# Check prerequisites
echo -e "${YELLOW}Checking prerequisites...${NC}"

if ! command -v mvn &> /dev/null; then
    echo -e "${RED}✗ Maven not found. Please install Maven.${NC}"
    echo "  Download: https://maven.apache.org/download.cgi"
    exit 1
fi
echo -e "${GREEN}✓ Maven found: $(mvn -v | head -1)${NC}"

if ! command -v java &> /dev/null; then
    echo -e "${RED}✗ Java not found. Please install Java 21+.${NC}"
    exit 1
fi
JAVA_VERSION=$(java -version 2>&1 | head -1)
echo -e "${GREEN}✓ Java found: $JAVA_VERSION${NC}"

# Check environment variables
echo -e "\n${YELLOW}Checking environment...${NC}"
if [ -z "$DEEPSEEK_API_KEY" ]; then
    echo -e "${RED}✗ DEEPSEEK_API_KEY not set${NC}"
    echo -e "${YELLOW}  Solution 1: Set manually${NC}"
    echo "    export DEEPSEEK_API_KEY=sk-your-key-here"
    echo -e "${YELLOW}  Solution 2: Load from .env${NC}"
    echo "    export \$(cat .env | xargs)"
    exit 1
fi
echo -e "${GREEN}✓ DEEPSEEK_API_KEY is set${NC}"

# Step 1: Clean
echo -e "\n${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${YELLOW}Step 1/3: Cleaning previous build...${NC}"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
mvn clean

# Step 2: Compile
echo -e "\n${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${YELLOW}Step 2/3: Compiling Java code...${NC}"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
mvn compile $BUILD_OPTS

# Check if compile was successful
if [ $? -ne 0 ]; then
    echo -e "\n${RED}✗ Compilation failed${NC}"
    exit 1
fi
echo -e "\n${GREEN}✓ Compilation successful${NC}"

# Step 3: Run
echo -e "\n${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${YELLOW}Step 3/3: Starting application...${NC}"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${GREEN}"
echo "Backend will run at: http://localhost:8080"
echo "  • Chat endpoint: POST /api/chat"
echo "  • Stream endpoint: POST /api/chat/stream"
echo "  • Health check: GET /api/health"
echo ""
echo "In another terminal, run:"
echo "  cd frontend && npm run dev"
echo ""
echo "Then open: http://localhost:5173"
echo -e "${NC}"
echo ""

# Run the application
mvn exec:java -Pserver
