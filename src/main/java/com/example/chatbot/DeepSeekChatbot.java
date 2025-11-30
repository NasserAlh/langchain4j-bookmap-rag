package com.example.chatbot;

import com.example.chatbot.rag.BookmapContentRetriever;
import com.example.chatbot.rag.BookmapIngestionService;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import com.example.chatbot.server.dto.ChatResult;
import dev.langchain4j.model.chat.response.ChatResponseMetadata;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A chatbot using LangChain4j with DeepSeek API and RAG support for Bookmap documentation.
 * Supports both streaming and non-streaming responses.
 * DeepSeek provides an OpenAI-compatible API, so we use the OpenAI integration.
 */
public class DeepSeekChatbot {

    private static final String DEEPSEEK_API_BASE_URL = "https://api.deepseek.com";
    private static final String EMBEDDINGS_PATH = "data/bookmap-embeddings.json";
    private static final String KNOWLEDGE_PATH = "knowledge-materials";
    private static final int MAX_RETRIES = 3;
    private static final int MAX_TOKENS = 4096;

    // Pricing per 1M tokens (USD)
    private static final double PRICE_INPUT_CACHE_HIT = 0.028;
    private static final double PRICE_INPUT_CACHE_MISS = 0.28;
    private static final double PRICE_OUTPUT = 0.42;

    private ChatLanguageModel chatModel;
    private StreamingChatLanguageModel streamingChatModel;
    private final ChatMemory chatMemory;
    private ContentRetriever contentRetriever;
    private EmbeddingModel embeddingModel;
    private String currentModel = "deepseek-chat";
    private double temperature = 0.7;
    private boolean ragEnabled = false;
    private boolean streamingEnabled = true;  // Streaming enabled by default
    private boolean showUsage = true;  // Show token usage by default

    private final String apiKey;

    public DeepSeekChatbot(String deepSeekApiKey) {
        this.apiKey = deepSeekApiKey;
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        rebuildModels();
    }

    /**
     * Rebuild chat models with current settings (model name, temperature).
     */
    private void rebuildModels() {
        this.chatModel = OpenAiChatModel.builder()
                .baseUrl(DEEPSEEK_API_BASE_URL)
                .apiKey(apiKey)
                .modelName(currentModel)
                .temperature(temperature)
                .maxTokens(MAX_TOKENS)
                .logRequests(false)
                .logResponses(false)
                .build();

        this.streamingChatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(DEEPSEEK_API_BASE_URL)
                .apiKey(apiKey)
                .modelName(currentModel)
                .temperature(temperature)
                .maxTokens(MAX_TOKENS)
                .logRequests(false)
                .logResponses(false)
                .build();
    }

    /**
     * Initialize RAG system by loading pre-built embeddings.
     * Requires OPENAI_API_KEY environment variable for the embedding model.
     */
    public boolean initializeRag() {
        String openAiApiKey = System.getenv("OPENAI_API_KEY");
        if (openAiApiKey == null || openAiApiKey.isEmpty()) {
            System.err.println("Warning: OPENAI_API_KEY not set. RAG features disabled.");
            return false;
        }

        Path embeddingsPath = Path.of(EMBEDDINGS_PATH);
        if (!Files.exists(embeddingsPath)) {
            System.out.println("No embeddings found. Run /ingest first to build the knowledge base.");
            return false;
        }

        try {
            System.out.println("Loading embeddings from " + EMBEDDINGS_PATH + "...");
            InMemoryEmbeddingStore<TextSegment> store =
                    InMemoryEmbeddingStore.fromFile(embeddingsPath.toString());

            this.embeddingModel = OpenAiEmbeddingModel.builder()
                    .apiKey(openAiApiKey)
                    .modelName("text-embedding-3-small")
                    .build();

            // Use higher minScore (0.7) to reduce irrelevant matches
            // and more results (8) to get better coverage
            this.contentRetriever = new BookmapContentRetriever()
                    .create(store, embeddingModel, 8, 0.7);

            ragEnabled = true;
            System.out.println("RAG initialized successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to initialize RAG: " + e.getMessage());
            return false;
        }
    }

    /**
     * Run document ingestion to build/rebuild the embedding store.
     */
    public void runIngestion() {
        String openAiApiKey = System.getenv("OPENAI_API_KEY");
        if (openAiApiKey == null || openAiApiKey.isEmpty()) {
            System.err.println("Error: OPENAI_API_KEY not set. Cannot run ingestion.");
            return;
        }

        Path knowledgePath = Path.of(KNOWLEDGE_PATH);
        if (!Files.exists(knowledgePath)) {
            System.err.println("Error: Knowledge directory not found: " + KNOWLEDGE_PATH);
            return;
        }

        try {
            // Ensure data directory exists
            Files.createDirectories(Path.of("data"));

            BookmapIngestionService ingestionService =
                    BookmapIngestionService.withOpenAi(openAiApiKey);
            int count = ingestionService.ingest(knowledgePath);
            ingestionService.saveToFile(EMBEDDINGS_PATH);

            // Re-initialize RAG with new embeddings
            this.embeddingModel = ingestionService.getEmbeddingModel();
            this.contentRetriever = new BookmapContentRetriever()
                    .create(ingestionService.getEmbeddingStore(), embeddingModel, 8, 0.7);
            ragEnabled = true;

            System.out.printf("Ingestion complete: %d documents indexed.%n", count);
        } catch (Exception e) {
            System.err.println("Ingestion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Send a message to the chatbot and get a response (non-streaming).
     */
    public String chat(String userMessage) {
        chatMemory.add(UserMessage.from(userMessage));
        ChatResponse response = chatWithRetry();
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
        displayUsage(response);
        return aiMessage.text();
    }

    /**
     * Execute chat with retry logic for transient errors.
     */
    private ChatResponse chatWithRetry() {
        Exception lastException = null;
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                return chatModel.chat(chatMemory.messages());
            } catch (Exception e) {
                lastException = e;
                String message = e.getMessage() != null ? e.getMessage().toLowerCase() : "";

                // Retry on server errors (500, 503) or rate limits (429)
                boolean isRetryable = message.contains("500") ||
                                      message.contains("503") ||
                                      message.contains("429") ||
                                      message.contains("server") ||
                                      message.contains("overload") ||
                                      message.contains("rate");

                if (isRetryable && attempt < MAX_RETRIES - 1) {
                    long waitTime = (long) Math.pow(2, attempt) * 1000;
                    System.out.printf("[Retry %d/%d in %ds due to: %s]%n",
                            attempt + 1, MAX_RETRIES, waitTime / 1000,
                            e.getMessage() != null ? e.getMessage().substring(0, Math.min(50, e.getMessage().length())) : "unknown error");
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                } else {
                    throw e;
                }
            }
        }
        throw new RuntimeException("Max retries exceeded", lastException);
    }

    /**
     * Display token usage and estimated cost.
     */
    private void displayUsage(ChatResponse response) {
        if (!showUsage || response.metadata() == null) {
            return;
        }
        try {
            var usage = response.metadata().tokenUsage();
            if (usage != null) {
                int inputTokens = usage.inputTokenCount() != null ? usage.inputTokenCount() : 0;
                int outputTokens = usage.outputTokenCount() != null ? usage.outputTokenCount() : 0;

                // Estimate cost (assume cache miss for simplicity)
                double inputCost = (inputTokens / 1_000_000.0) * PRICE_INPUT_CACHE_MISS;
                double outputCost = (outputTokens / 1_000_000.0) * PRICE_OUTPUT;
                double totalCost = inputCost + outputCost;

                System.out.printf("[Tokens: %d in, %d out | Est. cost: $%.6f]%n",
                        inputTokens, outputTokens, totalCost);
            }
        } catch (Exception e) {
            // Silently ignore usage display errors
        }
    }

    /**
     * Send a message with streaming response.
     * Prints tokens to console as they arrive.
     */
    public void chatStreaming(String userMessage) {
        chatMemory.add(UserMessage.from(userMessage));

        System.out.print("\nDeepSeek: ");

        CompletableFuture<ChatResponse> future = new CompletableFuture<>();
        StringBuilder fullResponse = new StringBuilder();

        streamingChatModel.chat(chatMemory.messages(), new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                System.out.print(partialResponse);
                fullResponse.append(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {
                System.out.println();
                System.out.println();
                // Add the complete response to memory
                chatMemory.add(response.aiMessage());
                future.complete(response);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("\nError during streaming: " + error.getMessage());
                future.completeExceptionally(error);
            }
        });

        // Wait for streaming to complete
        try {
            ChatResponse response = future.join();
            displayUsage(response);
        } catch (Exception e) {
            System.err.println("Streaming interrupted: " + e.getMessage());
        }
    }

    /**
     * Send a message with RAG-enhanced context (non-streaming).
     */
    public String chatWithRag(String userMessage) {
        if (contentRetriever == null) {
            return chat(userMessage);
        }

        String augmentedMessage = buildRagContext(userMessage);
        if (augmentedMessage == null) {
            return chat(userMessage);
        }

        chatMemory.add(UserMessage.from(augmentedMessage));
        ChatResponse response = chatWithRetry();
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
        displayUsage(response);
        return aiMessage.text();
    }

    /**
     * Send a message with RAG-enhanced context (streaming).
     */
    public void chatWithRagStreaming(String userMessage) {
        if (contentRetriever == null) {
            chatStreaming(userMessage);
            return;
        }

        String augmentedMessage = buildRagContext(userMessage);
        if (augmentedMessage == null) {
            chatStreaming(userMessage);
            return;
        }

        chatMemory.add(UserMessage.from(augmentedMessage));

        System.out.print("\nDeepSeek: ");

        CompletableFuture<ChatResponse> future = new CompletableFuture<>();
        StringBuilder fullResponse = new StringBuilder();

        streamingChatModel.chat(chatMemory.messages(), new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                System.out.print(partialResponse);
                fullResponse.append(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {
                System.out.println();
                System.out.println();
                chatMemory.add(response.aiMessage());
                future.complete(response);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("\nError during streaming: " + error.getMessage());
                future.completeExceptionally(error);
            }
        });

        try {
            ChatResponse response = future.join();
            displayUsage(response);
        } catch (Exception e) {
            System.err.println("Streaming interrupted: " + e.getMessage());
        }
    }

    /**
     * Build RAG context from retrieved documents.
     * @return augmented message with context, or null if no relevant docs found
     */
    private String buildRagContext(String userMessage) {
        List<Content> relevantDocs = contentRetriever.retrieve(Query.from(userMessage));

        if (relevantDocs.isEmpty()) {
            System.out.println("[RAG] No relevant documents found for query: " + userMessage);
            return null;
        }

        // Log retrieved documents for debugging
        System.out.println("[RAG] Retrieved " + relevantDocs.size() + " documents:");
        for (int i = 0; i < relevantDocs.size(); i++) {
            TextSegment segment = relevantDocs.get(i).textSegment();
            String source = segment.metadata().getString("source");
            String preview = segment.text().substring(0, Math.min(100, segment.text().length())).replace("\n", " ");
            System.out.printf("[RAG]   %d. %s: %s...%n", i + 1, source != null ? source : "unknown", preview);
        }

        String context = relevantDocs.stream()
                .map(Content::textSegment)
                .map(TextSegment::text)
                .collect(Collectors.joining("\n\n---\n\n"));

        return """
            You are a technical assistant for Bookmap API documentation.

            CRITICAL RULES:
            1. ONLY use information that is EXPLICITLY stated in the documentation context below.
            2. DO NOT invent, assume, or hallucinate any methods, fields, parameters, or behaviors.
            3. If the documentation doesn't contain specific information, say "This is not documented in the provided context."
            4. When showing code examples, ONLY use methods and signatures that appear in the documentation.
            5. If you're uncertain whether something exists, say so explicitly.
            6. Quote or reference the documentation directly when possible.

            ## Documentation Context:
            %s

            ## User Question:
            %s

            Remember: Only answer based on the documentation above. Do not make up information.
            """.formatted(context, userMessage);
    }

    /**
     * Simple one-shot response without memory.
     */
    public String ask(String question) {
        return chatModel.chat(question);
    }

    /**
     * Clear conversation history.
     */
    public void clearHistory() {
        chatMemory.clear();
    }

    /**
     * Toggle RAG mode on/off.
     */
    public void toggleRag() {
        if (contentRetriever == null) {
            System.out.println("RAG not initialized. Run /ingest first or set OPENAI_API_KEY.");
            return;
        }
        ragEnabled = !ragEnabled;
        System.out.println("RAG mode: " + (ragEnabled ? "ON" : "OFF"));
    }

    /**
     * Toggle streaming mode on/off.
     */
    public void toggleStreaming() {
        streamingEnabled = !streamingEnabled;
        System.out.println("Streaming mode: " + (streamingEnabled ? "ON" : "OFF"));
    }

    /**
     * Toggle usage display on/off.
     */
    public void toggleUsage() {
        showUsage = !showUsage;
        System.out.println("Usage display: " + (showUsage ? "ON" : "OFF"));
    }

    /**
     * Set the temperature for response generation.
     * Recommended values:
     * - 0.0: Coding/Math (deterministic)
     * - 1.0: Data Analysis (balanced)
     * - 1.3: General Conversation
     * - 1.5: Creative Writing
     *
     * @param temp temperature value (0.0 to 2.0)
     */
    public void setTemperature(double temp) {
        if (temp < 0.0 || temp > 2.0) {
            System.out.println("Temperature must be between 0.0 and 2.0");
            return;
        }
        this.temperature = temp;
        rebuildModels();
        System.out.printf("Temperature set to %.1f%n", temperature);
        System.out.println("  Hint: 0.0=Coding, 1.0=Analysis, 1.3=Chat, 1.5=Creative");
    }

    /**
     * Switch between DeepSeek models.
     *
     * @param model "deepseek-chat" or "deepseek-reasoner"
     */
    public void setModel(String model) {
        if (!model.equals("deepseek-chat") && !model.equals("deepseek-reasoner")) {
            System.out.println("Invalid model. Use: deepseek-chat or deepseek-reasoner");
            return;
        }
        this.currentModel = model;
        rebuildModels();
        System.out.println("Model switched to: " + currentModel);
        if (model.equals("deepseek-reasoner")) {
            System.out.println("  Note: Reasoner model uses Chain-of-Thought for complex reasoning.");
            System.out.println("  Note: Function calling is NOT supported with reasoner model.");
        }
    }

    public boolean isRagEnabled() {
        return ragEnabled;
    }

    public boolean isStreamingEnabled() {
        return streamingEnabled;
    }

    public String getCurrentModel() {
        return currentModel;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isShowUsage() {
        return showUsage;
    }

    public void setStreamingEnabled(boolean enabled) {
        this.streamingEnabled = enabled;
    }

    public void setShowUsage(boolean showUsage) {
        this.showUsage = showUsage;
    }

    /**
     * Chat with metadata returned (for HTTP API).
     */
    public ChatResult chatWithMetadata(String userMessage) {
        chatMemory.add(UserMessage.from(userMessage));
        ChatResponse response = chatWithRetry();
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
        return new ChatResult(aiMessage.text(), response.metadata());
    }

    /**
     * Chat with RAG and metadata returned (for HTTP API).
     */
    public ChatResult chatWithRagAndMetadata(String userMessage) {
        if (contentRetriever == null) {
            return chatWithMetadata(userMessage);
        }

        String augmentedMessage = buildRagContext(userMessage);
        if (augmentedMessage == null) {
            return chatWithMetadata(userMessage);
        }

        chatMemory.add(UserMessage.from(augmentedMessage));
        ChatResponse response = chatWithRetry();
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
        return new ChatResult(aiMessage.text(), response.metadata());
    }

    /**
     * Streaming chat with callbacks (for HTTP SSE).
     */
    public void chatStreamingCallback(String userMessage,
                                      Consumer<String> onToken,
                                      BiConsumer<String, ChatResponseMetadata> onComplete,
                                      Consumer<Throwable> onError) {
        chatMemory.add(UserMessage.from(userMessage));
        StringBuilder fullResponse = new StringBuilder();

        streamingChatModel.chat(chatMemory.messages(), new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                fullResponse.append(partialResponse);
                onToken.accept(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {
                chatMemory.add(response.aiMessage());
                onComplete.accept(fullResponse.toString(), response.metadata());
            }

            @Override
            public void onError(Throwable error) {
                onError.accept(error);
            }
        });
    }

    /**
     * Streaming chat with RAG and callbacks (for HTTP SSE).
     */
    public void chatWithRagStreamingCallback(String userMessage,
                                             Consumer<String> onToken,
                                             BiConsumer<String, ChatResponseMetadata> onComplete,
                                             Consumer<Throwable> onError) {
        if (contentRetriever == null) {
            chatStreamingCallback(userMessage, onToken, onComplete, onError);
            return;
        }

        String augmentedMessage = buildRagContext(userMessage);
        if (augmentedMessage == null) {
            chatStreamingCallback(userMessage, onToken, onComplete, onError);
            return;
        }

        chatMemory.add(UserMessage.from(augmentedMessage));
        StringBuilder fullResponse = new StringBuilder();

        streamingChatModel.chat(chatMemory.messages(), new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                fullResponse.append(partialResponse);
                onToken.accept(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {
                chatMemory.add(response.aiMessage());
                onComplete.accept(fullResponse.toString(), response.metadata());
            }

            @Override
            public void onError(Throwable error) {
                onError.accept(error);
            }
        });
    }

    /**
     * Toggle RAG and return new state (for HTTP API).
     */
    public boolean toggleRagAndReturn() {
        if (contentRetriever == null) {
            return false;
        }
        ragEnabled = !ragEnabled;
        return ragEnabled;
    }

    /**
     * Run ingestion and return document count (for HTTP API).
     */
    public int runIngestionAndReturnCount() {
        String openAiApiKey = System.getenv("OPENAI_API_KEY");
        if (openAiApiKey == null || openAiApiKey.isEmpty()) {
            throw new RuntimeException("OPENAI_API_KEY not set. Cannot run ingestion.");
        }

        Path knowledgePath = Path.of(KNOWLEDGE_PATH);
        if (!Files.exists(knowledgePath)) {
            throw new RuntimeException("Knowledge directory not found: " + KNOWLEDGE_PATH);
        }

        try {
            Files.createDirectories(Path.of("data"));

            BookmapIngestionService ingestionService =
                    BookmapIngestionService.withOpenAi(openAiApiKey);
            int count = ingestionService.ingest(knowledgePath);
            ingestionService.saveToFile(EMBEDDINGS_PATH);

            this.embeddingModel = ingestionService.getEmbeddingModel();
            this.contentRetriever = new BookmapContentRetriever()
                    .create(ingestionService.getEmbeddingStore(), embeddingModel, 8, 0.7);
            ragEnabled = true;

            return count;
        } catch (Exception e) {
            throw new RuntimeException("Ingestion failed: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
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
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  /stream      - Toggle streaming mode (ON by default)");
        System.out.println("  /rag         - Toggle RAG mode (Bookmap docs)");
        System.out.println("  /ingest      - Build/rebuild knowledge base");
        System.out.println("  /clear       - Clear conversation history");
        System.out.println("  /temp <val>  - Set temperature (0.0-2.0)");
        System.out.println("  /model <m>   - Switch model (deepseek-chat/deepseek-reasoner)");
        System.out.println("  /usage       - Toggle token usage display");
        System.out.println("  /quit        - Exit");
        System.out.println();

        // Try to initialize RAG if embeddings exist
        chatbot.initializeRag();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder indicator = new StringBuilder();
            if (chatbot.isStreamingEnabled()) indicator.append(" [Stream]");
            if (chatbot.isRagEnabled()) indicator.append(" [RAG]");

            System.out.print("You" + indicator + ": ");
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

            if (input.equalsIgnoreCase("/rag")) {
                chatbot.toggleRag();
                continue;
            }

            if (input.equalsIgnoreCase("/stream")) {
                chatbot.toggleStreaming();
                continue;
            }

            if (input.equalsIgnoreCase("/ingest")) {
                chatbot.runIngestion();
                continue;
            }

            if (input.equalsIgnoreCase("/usage")) {
                chatbot.toggleUsage();
                continue;
            }

            if (input.toLowerCase().startsWith("/temp")) {
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) {
                    System.out.printf("Current temperature: %.1f%n", chatbot.getTemperature());
                    System.out.println("Usage: /temp <value>  (0.0-2.0)");
                    System.out.println("  0.0 = Coding/Math, 1.0 = Analysis, 1.3 = Chat, 1.5 = Creative");
                } else {
                    try {
                        double temp = Double.parseDouble(parts[1]);
                        chatbot.setTemperature(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid temperature value. Use a number between 0.0 and 2.0");
                    }
                }
                continue;
            }

            if (input.toLowerCase().startsWith("/model")) {
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) {
                    System.out.println("Current model: " + chatbot.getCurrentModel());
                    System.out.println("Usage: /model <model-name>");
                    System.out.println("  Available: deepseek-chat, deepseek-reasoner");
                } else {
                    chatbot.setModel(parts[1].trim());
                }
                continue;
            }

            try {
                if (chatbot.isStreamingEnabled()) {
                    // Streaming mode
                    if (chatbot.isRagEnabled()) {
                        chatbot.chatWithRagStreaming(input);
                    } else {
                        chatbot.chatStreaming(input);
                    }
                } else {
                    // Non-streaming mode
                    String response;
                    if (chatbot.isRagEnabled()) {
                        response = chatbot.chatWithRag(input);
                    } else {
                        response = chatbot.chat(input);
                    }
                    System.out.println("\nDeepSeek: " + response);
                    System.out.println();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
