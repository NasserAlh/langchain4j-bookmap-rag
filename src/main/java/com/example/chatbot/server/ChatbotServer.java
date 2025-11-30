package com.example.chatbot.server;

import com.example.chatbot.DeepSeekChatbot;
import com.example.chatbot.server.dto.ChatRequest;
import com.example.chatbot.server.dto.ErrorResponse;
import com.example.chatbot.server.dto.TokenUsage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ChatbotServer {

    private static final Logger log = LoggerFactory.getLogger(ChatbotServer.class);
    private static final int DEFAULT_PORT = 8080;

    private final Javalin app;
    private final DeepSeekChatbot chatbot;
    private final int port;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatbotServer(DeepSeekChatbot chatbot, int port) {
        this.chatbot = chatbot;
        this.port = port;
        this.app = createApp();
    }

    private Javalin createApp() {
        Javalin javalin = Javalin.create(config -> {
            config.http.asyncTimeout = 300_000L; // 5 minutes for SSE
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.allowHost("http://localhost:5173", "http://127.0.0.1:5173");
                    rule.allowCredentials = true;
                });
            });
        });

        // Register controllers
        ChatController chatController = new ChatController(chatbot);
        SettingsController settingsController = new SettingsController(chatbot);

        // Health check
        javalin.get("/api/health", ctx -> ctx.json(Map.of("status", "ok")));

        // Chat endpoints
        javalin.post("/api/chat", chatController::chat);

        // Streaming chat endpoint - handle SSE manually
        javalin.post("/api/chat/stream", ctx -> {
            ChatRequest request = ctx.bodyAsClass(ChatRequest.class);

            if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
                ErrorResponse error = ErrorResponse.validation("Message is required");
                log.warn("[{}] Validation error: empty message", error.getErrorId());
                ctx.status(400).json(error);
                return;
            }

            log.info("Received streaming chat request: {}", truncate(request.getMessage(), 50));

            // Set SSE headers
            ctx.contentType("text/event-stream");
            ctx.header("Cache-Control", "no-cache");
            ctx.header("Connection", "keep-alive");
            ctx.header("X-Accel-Buffering", "no"); // Disable nginx buffering

            var outputStream = ctx.outputStream();

            CompletableFuture<Void> future = new CompletableFuture<>();

            try {
                chatbot.chatStreamingCallback(
                    request.getMessage(),
                    // onToken
                    token -> {
                        try {
                            String escapedToken = token
                                .replace("\\", "\\\\")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r")
                                .replace("\"", "\\\"");
                            outputStream.write(("event: token\ndata: \"" + escapedToken + "\"\n\n").getBytes());
                            outputStream.flush();
                        } catch (IOException e) {
                            log.warn("Failed to send token: {}", e.getMessage());
                        }
                    },
                    // onComplete
                    (fullResponse, metadata) -> {
                        try {
                            TokenUsage usage = ChatController.extractUsage(metadata);
                            String usageJson = objectMapper.writeValueAsString(usage);
                            outputStream.write(("event: done\ndata: " + usageJson + "\n\n").getBytes());
                            outputStream.flush();
                            future.complete(null);
                        } catch (IOException e) {
                            log.warn("Failed to send completion event: {}", e.getMessage());
                            future.completeExceptionally(e);
                        }
                    },
                    // onError
                    error -> {
                        try {
                            ErrorResponse errorResponse = categorizeStreamingError(error);
                            MDC.put("errorId", errorResponse.getErrorId());
                            log.error("[{}] Streaming error: {} - {}", errorResponse.getErrorId(), errorResponse.getCode(), error.getMessage(), error);
                            String errorJson = objectMapper.writeValueAsString(errorResponse);
                            outputStream.write(("event: error\ndata: " + errorJson + "\n\n").getBytes());
                            outputStream.flush();
                            MDC.remove("errorId");
                        } catch (IOException e) {
                            log.error("Failed to send error event: {}", e.getMessage());
                        }
                        future.completeExceptionally(error);
                    }
                );

                // Wait for streaming to complete
                future.join();

            } catch (Exception e) {
                ErrorResponse errorResponse = categorizeStreamingError(e);
                MDC.put("errorId", errorResponse.getErrorId());
                log.error("[{}] SSE streaming error: {} - {}", errorResponse.getErrorId(), errorResponse.getCode(), e.getMessage(), e);
                MDC.remove("errorId");
                try {
                    String errorJson = objectMapper.writeValueAsString(errorResponse);
                    outputStream.write(("event: error\ndata: " + errorJson + "\n\n").getBytes());
                    outputStream.flush();
                } catch (IOException ignored) {}
            }
        });

        // Settings endpoints
        javalin.get("/api/settings", settingsController::getSettings);
        javalin.post("/api/settings", settingsController::updateSettings);

        // RAG endpoints
        javalin.post("/api/rag/toggle", settingsController::toggleRag);
        javalin.post("/api/rag/ingest", settingsController::ingestDocuments);

        // History endpoint
        javalin.post("/api/history/clear", settingsController::clearHistory);

        // Global exception handler
        javalin.exception(Exception.class, (e, ctx) -> {
            ErrorResponse error = ErrorResponse.internal(e);
            MDC.put("errorId", error.getErrorId());
            log.error("[{}] Unhandled exception: {} - {}", error.getErrorId(), error.getCode(), e.getMessage(), e);
            MDC.remove("errorId");
            ctx.status(500).json(error);
        });

        return javalin;
    }

    /**
     * Categorizes streaming exceptions into appropriate error codes
     */
    private ErrorResponse categorizeStreamingError(Throwable e) {
        String message = e.getMessage() != null ? e.getMessage() : "Unknown error";
        String lowerMessage = message.toLowerCase();

        if (lowerMessage.contains("rate limit") || lowerMessage.contains("429") || lowerMessage.contains("too many requests")) {
            return ErrorResponse.rateLimited();
        }
        if (lowerMessage.contains("401") || lowerMessage.contains("unauthorized") || lowerMessage.contains("invalid api key")) {
            return ErrorResponse.authFailed();
        }
        if (lowerMessage.contains("api") || lowerMessage.contains("model") || lowerMessage.contains("deepseek")
            || lowerMessage.contains("openai") || lowerMessage.contains("timeout")) {
            return ErrorResponse.modelError("Failed to get response from AI model", e);
        }
        if (lowerMessage.contains("embedding") || lowerMessage.contains("vector") || lowerMessage.contains("document")) {
            return ErrorResponse.ragError("Document search failed", e);
        }
        return ErrorResponse.internal(e);
    }

    private String truncate(String s, int maxLen) {
        return s.length() <= maxLen ? s : s.substring(0, maxLen) + "...";
    }

    public void start() {
        app.start(port);
        log.info("ChatbotServer started on port {}", port);
        log.info("API endpoints:");
        log.info("  POST /api/chat          - Send message (non-streaming)");
        log.info("  POST /api/chat/stream   - Send message (SSE streaming)");
        log.info("  GET  /api/settings      - Get current settings");
        log.info("  POST /api/settings      - Update settings");
        log.info("  POST /api/rag/toggle    - Toggle RAG mode");
        log.info("  POST /api/rag/ingest    - Trigger document ingestion");
        log.info("  POST /api/history/clear - Clear chat memory");
        log.info("  GET  /api/health        - Health check");
    }

    public void stop() {
        app.stop();
        log.info("ChatbotServer stopped");
    }

    public static void main(String[] args) {
        String apiKey = System.getenv("DEEPSEEK_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: DEEPSEEK_API_KEY environment variable is not set.");
            System.err.println("Please set it: export DEEPSEEK_API_KEY=your_api_key");
            System.exit(1);
        }

        int port = DEFAULT_PORT;
        String portEnv = System.getenv("PORT");
        if (portEnv != null && !portEnv.isEmpty()) {
            try {
                port = Integer.parseInt(portEnv);
            } catch (NumberFormatException e) {
                log.warn("Invalid PORT value '{}', using default {}", portEnv, DEFAULT_PORT);
            }
        }

        DeepSeekChatbot chatbot = new DeepSeekChatbot(apiKey);

        // Try to initialize RAG if embeddings exist
        chatbot.initializeRag();

        ChatbotServer server = new ChatbotServer(chatbot, port);
        server.start();

        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}
