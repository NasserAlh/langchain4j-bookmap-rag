package com.example.chatbot;

/**
 * Launcher class for JavaFX application.
 * This is needed when running JavaFX from the classpath (not module-path)
 * because the main class cannot directly extend Application in that case.
 */
public class Launcher {
    public static void main(String[] args) {
        App.main(args);
    }
}
