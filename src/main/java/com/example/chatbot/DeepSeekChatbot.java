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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * A chatbot using LangChain4j with DeepSeek API and RAG support for Bookmap documentation.
 * Supports both streaming and non-streaming responses.
 * DeepSeek provides an OpenAI-compatible API, so we use the OpenAI integration.
 */
public class DeepSeekChatbot {

    private static final String DEEPSEEK_API_BASE_URL = "https://api.deepseek.com";
    private static final String DEEPSEEK_MODEL = "deepseek-chat";
    private static final String EMBEDDINGS_PATH = "data/bookmap-embeddings.json";
    private static final String KNOWLEDGE_PATH = "knowledge-materials";

    private final ChatLanguageModel chatModel;
    private final StreamingChatLanguageModel streamingChatModel;
    private final ChatMemory chatMemory;
    private ContentRetriever contentRetriever;
    private EmbeddingModel embeddingModel;
    private boolean ragEnabled = false;
    private boolean streamingEnabled = true;  // Streaming enabled by default

    public DeepSeekChatbot(String deepSeekApiKey) {
        // Non-streaming model (for fallback)
        this.chatModel = OpenAiChatModel.builder()
                .baseUrl(DEEPSEEK_API_BASE_URL)
                .apiKey(deepSeekApiKey)
                .modelName(DEEPSEEK_MODEL)
                .temperature(0.7)
                .maxTokens(2048)
                .logRequests(false)
                .logResponses(false)
                .build();

        // Streaming model for real-time responses
        this.streamingChatModel = OpenAiStreamingChatModel.builder()
                .baseUrl(DEEPSEEK_API_BASE_URL)
                .apiKey(deepSeekApiKey)
                .modelName(DEEPSEEK_MODEL)
                .temperature(0.7)
                .maxTokens(2048)
                .logRequests(false)
                .logResponses(false)
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
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

            this.contentRetriever = new BookmapContentRetriever()
                    .create(store, embeddingModel, 5, 0.6);

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
                    .create(ingestionService.getEmbeddingStore(), embeddingModel, 5, 0.6);
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
        ChatResponse response = chatModel.chat(chatMemory.messages());
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
        return aiMessage.text();
    }

    /**
     * Send a message with streaming response.
     * Prints tokens to console as they arrive.
     */
    public void chatStreaming(String userMessage) {
        chatMemory.add(UserMessage.from(userMessage));

        System.out.print("\nDeepSeek: ");

        CompletableFuture<Void> future = new CompletableFuture<>();
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
                future.complete(null);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("\nError during streaming: " + error.getMessage());
                future.completeExceptionally(error);
            }
        });

        // Wait for streaming to complete
        try {
            future.join();
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
        ChatResponse response = chatModel.chat(chatMemory.messages());
        AiMessage aiMessage = response.aiMessage();
        chatMemory.add(aiMessage);
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

        CompletableFuture<Void> future = new CompletableFuture<>();
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
                future.complete(null);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("\nError during streaming: " + error.getMessage());
                future.completeExceptionally(error);
            }
        });

        try {
            future.join();
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
            return null;
        }

        String context = relevantDocs.stream()
                .map(Content::textSegment)
                .map(TextSegment::text)
                .collect(Collectors.joining("\n\n---\n\n"));

        return """
            Use the following Bookmap API documentation to answer the question.
            If the documentation doesn't contain relevant information, say so.
            Always provide code examples when relevant.

            ## Documentation Context:
            %s

            ## Question:
            %s
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

    public boolean isRagEnabled() {
        return ragEnabled;
    }

    public boolean isStreamingEnabled() {
        return streamingEnabled;
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
        System.out.println("  /stream  - Toggle streaming mode (ON by default)");
        System.out.println("  /rag     - Toggle RAG mode (Bookmap docs)");
        System.out.println("  /ingest  - Build/rebuild knowledge base");
        System.out.println("  /clear   - Clear conversation history");
        System.out.println("  /quit    - Exit");
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
