package com.example.chatbot.server;

import com.example.chatbot.DeepSeekChatbot;
import com.example.chatbot.server.dto.ChatRequest;
import com.example.chatbot.server.dto.ChatResponse;
import com.example.chatbot.server.dto.ChatResult;
import com.example.chatbot.server.dto.TokenUsage;
import dev.langchain4j.model.chat.response.ChatResponseMetadata;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    // Pricing per 1M tokens (USD) - same as DeepSeekChatbot
    private static final double PRICE_INPUT_CACHE_MISS = 0.28;
    private static final double PRICE_OUTPUT = 0.42;

    private final DeepSeekChatbot chatbot;

    public ChatController(DeepSeekChatbot chatbot) {
        this.chatbot = chatbot;
    }

    public void chat(Context ctx) {
        ChatRequest request = ctx.bodyAsClass(ChatRequest.class);

        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            ctx.status(400).json(Map.of("error", "Message is required"));
            return;
        }

        log.info("Received chat request: {}", truncate(request.getMessage(), 50));

        try {
            ChatResult result;
            if (chatbot.isRagEnabled()) {
                result = chatbot.chatWithRagAndMetadata(request.getMessage());
            } else {
                result = chatbot.chatWithMetadata(request.getMessage());
            }

            TokenUsage usage = extractUsage(result.metadata());
            ChatResponse response = new ChatResponse(result.content(), usage);
            ctx.json(response);
        } catch (Exception e) {
            log.error("Chat request failed: {}", e.getMessage(), e);
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    public DeepSeekChatbot getChatbot() {
        return chatbot;
    }

    public static TokenUsage extractUsage(ChatResponseMetadata metadata) {
        if (metadata == null || metadata.tokenUsage() == null) {
            return new TokenUsage(0, 0, 0.0);
        }

        var usage = metadata.tokenUsage();
        int inputTokens = usage.inputTokenCount() != null ? usage.inputTokenCount() : 0;
        int outputTokens = usage.outputTokenCount() != null ? usage.outputTokenCount() : 0;

        double inputCost = (inputTokens / 1_000_000.0) * PRICE_INPUT_CACHE_MISS;
        double outputCost = (outputTokens / 1_000_000.0) * PRICE_OUTPUT;
        double totalCost = inputCost + outputCost;

        return new TokenUsage(inputTokens, outputTokens, totalCost);
    }

    private String truncate(String s, int maxLen) {
        return s.length() <= maxLen ? s : s.substring(0, maxLen) + "...";
    }
}
