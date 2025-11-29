@echo off
title DeepSeek Chatbot Manager
cd /d "%~dp0"
powershell -ExecutionPolicy Bypass -File "%~dp0run-app.ps1"
pause
