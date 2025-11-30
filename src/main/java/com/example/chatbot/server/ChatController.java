package com.example.chatbot.server;

import com.example.chatbot.DeepSeekChatbot;
import com.example.chatbot.server.dto.ChatRequest;
import com.example.chatbot.server.dto.ChatResponse;
import com.example.chatbot.server.dto.ChatResult;
import com.example.chatbot.server.dto.ErrorResponse;
import com.example.chatbot.server.dto.TokenUsage;
import dev.langchain4j.model.chat.response.ChatResponseMetadata;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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
            ErrorResponse error = ErrorResponse.validation("Message is required");
            log.warn("[{}] Validation error: empty message", error.getErrorId());
            ctx.status(400).json(error);
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
            ErrorResponse error = categorizeError(e);
            MDC.put("errorId", error.getErrorId());
            log.error("[{}] Chat request failed: {} - {}", error.getErrorId(), error.getCode(), e.getMessage(), e);
            MDC.remove("errorId");
            ctx.status(500).json(error);
        }
    }

    /**
     * Categorizes exceptions into appropriate error codes for better debugging
     */
    private ErrorResponse categorizeError(Exception e) {
        String message = e.getMessage() != null ? e.getMessage() : "Unknown error";
        String lowerMessage = message.toLowerCase();

        // Rate limiting
        if (lowerMessage.contains("rate limit") || lowerMessage.contains("429") || lowerMessage.contains("too many requests")) {
            return ErrorResponse.rateLimited();
        }

        // Authentication issues
        if (lowerMessage.contains("401") || lowerMessage.contains("unauthorized") || lowerMessage.contains("invalid api key")) {
            return ErrorResponse.authFailed();
        }

        // Model/API errors
        if (lowerMessage.contains("api") || lowerMessage.contains("model") || lowerMessage.contains("deepseek")
            || lowerMessage.contains("openai") || lowerMessage.contains("timeout")) {
            return ErrorResponse.modelError("Failed to get response from AI model", e);
        }

        // RAG-related errors
        if (lowerMessage.contains("embedding") || lowerMessage.contains("vector") || lowerMessage.contains("document")) {
            return ErrorResponse.ragError("Document search failed", e);
        }

        // Default: internal error
        return ErrorResponse.internal(e);
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
