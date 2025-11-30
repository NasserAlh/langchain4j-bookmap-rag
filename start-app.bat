@echo off
REM Start both backend and frontend for development
REM Usage: start-app.bat

REM Check for DEEPSEEK_API_KEY
if "%DEEPSEEK_API_KEY%"=="" (
    echo ERROR: DEEPSEEK_API_KEY environment variable is not set.
    echo Please set it: set DEEPSEEK_API_KEY=your_api_key
    exit /b 1
)

echo.
echo Starting LangChain4j Chatbot Application...
echo.

REM Get the script directory
set SCRIPT_DIR=%~dp0

REM Start backend in a new window
echo [Backend] Starting Java backend on port 8080...
start "Chatbot Backend" cmd /k "cd /d %SCRIPT_DIR% && echo Building and starting backend... && mvn compile exec:java -Pserver"

REM Give backend time to start
echo [Backend] Waiting for backend to initialize...
timeout /t 5 /nobreak > nul

REM Start frontend in a new window
echo [Frontend] Starting Svelte frontend on port 5173...
start "Chatbot Frontend" cmd /k "cd /d %SCRIPT_DIR%frontend && echo Starting frontend dev server... && npm run dev"

echo.
echo ========================================
echo   Application Starting!
echo ========================================
echo.
echo   Backend:  http://localhost:8080
echo   Frontend: http://localhost:5173
echo.
echo   Close the terminal windows to stop
echo ========================================

REM Open browser after delay
timeout /t 3 /nobreak > nul
echo.
echo Opening browser...
start http://localhost:5173
