@echo off
REM Use JDK 25 for this project only (system default JDK 21 unchanged)
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8
set PATH=%JAVA_HOME%\bin;%PATH%

echo Using JDK 25: %JAVA_HOME%
java -version
echo.

REM Run Maven with arguments, or default to exec:java if no args
if "%~1"=="" (
    mvn exec:java
) else (
    mvn %*
)
