package com.example.chatbot;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;

import java.util.Scanner;

/**
 * A simple chatbot using LangChain4j with DeepSeek API.
 * DeepSeek provides an OpenAI-compatible API, so we use the OpenAI integration.
 */
public class DeepSeekChatbot {

    private static final String DEEPSEEK_API_BASE_URL = "https://api.deepseek.com";
    private static final String DEEPSEEK_MODEL = "deepseek-chat"; // or "deepseek-coder" for code tasks

    private final ChatLanguageModel chatModel;
    private final ChatMemory chatMemory;

    public DeepSeekChatbot(String apiKey) {
        // DeepSeek uses OpenAI-compatible API
        this.chatModel = OpenAiChatModel.builder()
                .baseUrl(DEEPSEEK_API_BASE_URL)
                .apiKey(apiKey)
                .modelName(DEEPSEEK_MODEL)
                .temperature(0.7)
                .maxTokens(2048)
                .logRequests(false)
                .logResponses(false)
                .build();

        // Chat memory to maintain conversation context (last 20 messages)
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
    }

    /**
     * Send a message to the chatbot and get a response.
     */
    public String chat(String userMessage) {
        // Add user message to memory
        chatMemory.add(UserMessage.from(userMessage));

        // Generate response using all messages in memory
        Response<AiMessage> response = chatModel.generate(chatMemory.messages());

        // Add AI response to memory
        AiMessage aiMessage = response.content();
        chatMemory.add(aiMessage);

        return aiMessage.text();
    }

    /**
     * Simple one-shot response without memory.
     */
    public String ask(String question) {
        return chatModel.generate(question);
    }

    /**
     * Clear conversation history.
     */
    public void clearHistory() {
        chatMemory.clear();
    }

    public static void main(String[] args) {
        // Get API key from environment variable
        String apiKey = System.getenv("DEEPSEEK_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: DEEPSEEK_API_KEY environment variable is not set.");
            System.err.println("Please set it: export DEEPSEEK_API_KEY=your_api_key");
            System.exit(1);
        }

        DeepSeekChatbot chatbot = new DeepSeekChatbot(apiKey);

        System.out.println("===========================================");
        System.out.println("  DeepSeek Chatbot (powered by LangChain4j)");
        System.out.println("===========================================");
        System.out.println("Type your message and press Enter to chat.");
        System.out.println("Commands: /clear - clear history, /quit - exit");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("/quit") || input.equalsIgnoreCase("/exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("/clear")) {
                chatbot.clearHistory();
                System.out.println("Conversation history cleared.");
                continue;
            }

            try {
                String response = chatbot.chat(input);
                System.out.println("\nDeepSeek: " + response);
                System.out.println();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
