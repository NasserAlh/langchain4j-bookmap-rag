package com.example.chatbot.server;

import com.example.chatbot.DeepSeekChatbot;
import com.example.chatbot.server.dto.SettingsDto;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SettingsController {

    private static final Logger log = LoggerFactory.getLogger(SettingsController.class);

    private final DeepSeekChatbot chatbot;

    public SettingsController(DeepSeekChatbot chatbot) {
        this.chatbot = chatbot;
    }

    public void getSettings(Context ctx) {
        SettingsDto settings = new SettingsDto(
            chatbot.getCurrentModel(),
            chatbot.getTemperature(),
            chatbot.isRagEnabled(),
            chatbot.isStreamingEnabled(),
            chatbot.isShowUsage()
        );
        ctx.json(settings);
    }

    public void updateSettings(Context ctx) {
        SettingsDto request = ctx.bodyAsClass(SettingsDto.class);

        try {
            if (request.getModel() != null) {
                chatbot.setModel(request.getModel());
            }

            if (request.getTemperature() >= 0.0 && request.getTemperature() <= 2.0) {
                chatbot.setTemperature(request.getTemperature());
            }

            if (request.isStreamingEnabled() != chatbot.isStreamingEnabled()) {
                chatbot.setStreamingEnabled(request.isStreamingEnabled());
            }

            if (request.isShowUsage() != chatbot.isShowUsage()) {
                chatbot.setShowUsage(request.isShowUsage());
            }

            log.info("Settings updated: model={}, temperature={}",
                chatbot.getCurrentModel(), chatbot.getTemperature());

            ctx.json(Map.of("success", true));
        } catch (Exception e) {
            log.error("Failed to update settings: {}", e.getMessage(), e);
            ctx.status(400).json(Map.of("error", e.getMessage()));
        }
    }

    public void toggleRag(Context ctx) {
        boolean newState = chatbot.toggleRagAndReturn();
        log.info("RAG toggled to: {}", newState);
        ctx.json(Map.of("ragEnabled", newState));
    }

    public void ingestDocuments(Context ctx) {
        try {
            log.info("Starting document ingestion...");
            int count = chatbot.runIngestionAndReturnCount();
            log.info("Document ingestion complete: {} documents indexed", count);
            ctx.json(Map.of("documentsIndexed", count));
        } catch (Exception e) {
            log.error("Document ingestion failed: {}", e.getMessage(), e);
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    public void clearHistory(Context ctx) {
        chatbot.clearHistory();
        log.info("Chat history cleared");
        ctx.json(Map.of("success", true));
    }
}
