@echo off
REM ###############################################################################
REM Build and Run Script for LangChain4j Chatbot (Windows)
REM
REM Usage:
REM   build-and-run.bat              (Clean, compile, and run backend)
REM   build-and-run.bat --skip-test  (Skip tests during build)
REM
REM This script:
REM   1. Cleans previous build artifacts
REM   2. Compiles the Java backend
REM   3. Runs the application with Server profile
REM
REM ###############################################################################

setlocal enabledelayedexpansion

REM Colors not available in cmd, use simple formatting
echo.
echo ========================================================================
echo   LangChain4j Chatbot - Build ^& Run Script
echo ========================================================================
echo.

REM Check for Maven
echo Checking prerequisites...
mvn -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Maven not found. Please install Maven.
    echo   Download: https://maven.apache.org/download.cgi
    exit /b 1
)
echo [OK] Maven found

REM Check for Java
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Java not found. Please install Java 21+.
    exit /b 1
)
echo [OK] Java found

REM Check environment variable
echo.
echo Checking environment...
if not defined DEEPSEEK_API_KEY (
    echo [ERROR] DEEPSEEK_API_KEY not set
    echo.
    echo Solution 1: Set manually
    echo   set DEEPSEEK_API_KEY=sk-your-key-here
    echo.
    echo Solution 2: Load from .env file
    echo   Create .env file first, then run this script
    exit /b 1
)
echo [OK] DEEPSEEK_API_KEY is set

REM Parse arguments
set BUILD_OPTS=
if "%1"=="--skip-test" (
    set BUILD_OPTS=-DskipTests
    echo.
    echo Note: Tests will be skipped
)

REM Step 1: Clean
echo.
echo ========================================================================
echo Step 1/3: Cleaning previous build...
echo ========================================================================
call mvn clean
if errorlevel 1 (
    echo [ERROR] Clean failed
    exit /b 1
)

REM Step 2: Compile
echo.
echo ========================================================================
echo Step 2/3: Compiling Java code...
echo ========================================================================
call mvn compile %BUILD_OPTS%
if errorlevel 1 (
    echo [ERROR] Compilation failed
    exit /b 1
)
echo.
echo [OK] Compilation successful

REM Step 3: Run
echo.
echo ========================================================================
echo Step 3/3: Starting application...
echo ========================================================================
echo.
echo Backend will run at: http://localhost:8080
echo   - Chat endpoint: POST /api/chat
echo   - Stream endpoint: POST /api/chat/stream
echo   - Health check: GET /api/health
echo.
echo In another terminal (cmd/PowerShell), run:
echo   cd frontend
echo   npm run dev
echo.
echo Then open: http://localhost:5173
echo.
echo ========================================================================
echo.

call mvn exec:java -Pserver
endlocal
