package com.example.chatbot.view;

import com.example.chatbot.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the main chat window.
 */
public class MainController implements Initializable {

    @FXML
    private ListView<String> chatHistoryList;

    @FXML
    private ToggleButton ragToggle;

    @FXML
    private ToggleButton streamToggle;

    @FXML
    private ToggleButton themeToggle;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private VBox messagesContainer;

    @FXML
    private TextArea inputField;

    @FXML
    private Button sendButton;

    @FXML
    private Label modelLabel;

    @FXML
    private Label tempLabel;

    @FXML
    private Label tokenLabel;

    @FXML
    private Label costLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup toggle button handlers
        setupToggleButtons();

        // Setup send button
        setupSendButton();

        // Setup input field
        setupInputField();

        // Initialize chat history
        initializeChatHistory();
    }

    private void setupToggleButtons() {
        // RAG toggle
        ragToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            ragToggle.setText(newVal ? "ON" : "OFF");
            System.out.println("RAG mode: " + (newVal ? "ON" : "OFF"));
        });

        // Stream toggle
        streamToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            streamToggle.setText(newVal ? "ON" : "OFF");
            System.out.println("Streaming: " + (newVal ? "ON" : "OFF"));
        });

        // Theme toggle
        themeToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            themeToggle.setText(newVal ? "Dark" : "Light");
            App.setDarkMode(newVal);
        });
    }

    private void setupSendButton() {
        sendButton.setOnAction(e -> sendMessage());
    }

    private void setupInputField() {
        // Handle Enter key to send, Shift+Enter for new line
        inputField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                if (event.isShiftDown()) {
                    // Insert new line
                    inputField.insertText(inputField.getCaretPosition(), "\n");
                } else {
                    // Send message
                    event.consume();
                    sendMessage();
                }
            }
        });
    }

    private void initializeChatHistory() {
        // Add some placeholder items
        chatHistoryList.getItems().addAll(
            "Session 1 - Today",
            "Session 2 - Yesterday"
        );
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        // Add user message to chat
        addUserMessage(message);

        // Clear input
        inputField.clear();

        // TODO: Connect to DeepSeekChatbot and get response
        // For now, add a placeholder response
        addAIMessage("This is a placeholder response. The chatbot integration is coming soon!");

        // Scroll to bottom
        chatScrollPane.setVvalue(1.0);
    }

    private void addUserMessage(String message) {
        Label userLabel = new Label("You: " + message);
        userLabel.setWrapText(true);
        userLabel.getStyleClass().addAll("user-message");
        messagesContainer.getChildren().add(userLabel);
    }

    private void addAIMessage(String message) {
        Label aiLabel = new Label("DeepSeek: " + message);
        aiLabel.setWrapText(true);
        aiLabel.getStyleClass().addAll("ai-message");
        messagesContainer.getChildren().add(aiLabel);
    }

    /**
     * Update the token usage display.
     */
    public void updateTokenUsage(int inputTokens, int outputTokens, double cost) {
        tokenLabel.setText(String.format("Tokens: %d in, %d out", inputTokens, outputTokens));
        costLabel.setText(String.format("Cost: $%.4f", cost));
    }

    /**
     * Update the model display.
     */
    public void updateModel(String model, double temperature) {
        modelLabel.setText("Model: " + model);
        tempLabel.setText(String.format("Temp: %.1f", temperature));
    }
}
