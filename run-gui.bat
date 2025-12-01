@echo off
REM Run JavaFX GUI with JDK 25
REM Uses Launcher class to properly bootstrap JavaFX from classpath

set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8
set PATH=%JAVA_HOME%\bin;%PATH%

echo DeepSeek Chatbot GUI
echo Using JDK 25: %JAVA_HOME%
echo.

REM Compile if needed
if not exist target\classes\com\example\chatbot\Launcher.class (
    echo Compiling...
    call mvn compile dependency:copy-dependencies -DoutputDirectory=target/lib -q
    if errorlevel 1 (
        echo Compilation failed!
        pause
        exit /b 1
    )
)

REM Run the GUI with native access enabled to suppress warnings
"%JAVA_HOME%\bin\java" --enable-native-access=ALL-UNNAMED -cp "target/classes;target/lib/*" com.example.chatbot.Launcher
