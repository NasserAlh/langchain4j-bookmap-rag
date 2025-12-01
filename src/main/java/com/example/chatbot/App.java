package com.example.chatbot;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX Application entry point for the DeepSeek Chatbot GUI.
 * Uses AtlantaFX Primer theme for modern styling.
 */
public class App extends Application {

    private static final String APP_TITLE = "DeepSeek Chatbot";
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Apply AtlantaFX theme (PrimerDark or PrimerLight)
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        BorderPane root = loader.load();

        // Create scene
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Load custom CSS
        String css = getClass().getResource("/css/app.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Configure stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        // Show window
        primaryStage.show();
    }

    /**
     * Toggle between dark and light themes.
     */
    public static void setDarkMode(boolean dark) {
        if (dark) {
            Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        } else {
            Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
