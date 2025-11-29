package com.example.chatbot.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for ingesting Bookmap knowledge base documents into an embedding store.
 *
 * Key features:
 * - No chunking (documents are pre-structured as atomic units)
 * - YAML frontmatter metadata extraction
 * - Path-based category classification
 * - Batch embedding generation with progress indicator
 */
public class BookmapIngestionService {

    private static final int BATCH_SIZE = 50;  // Process 50 documents at a time for progress updates

    private final EmbeddingModel embeddingModel;
    private final InMemoryEmbeddingStore<TextSegment> embeddingStore;
    private final DocumentEnricher enricher;

    public BookmapIngestionService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = new InMemoryEmbeddingStore<>();
        this.enricher = new DocumentEnricher();
    }

    /**
     * Create an ingestion service with OpenAI embeddings.
     *
     * @param openAiApiKey the OpenAI API key
     * @return configured ingestion service
     */
    public static BookmapIngestionService withOpenAi(String openAiApiKey) {
        EmbeddingModel model = OpenAiEmbeddingModel.builder()
                .apiKey(openAiApiKey)
                .modelName("text-embedding-3-small")
                .build();
        return new BookmapIngestionService(model);
    }

    /**
     * Ingest all documents from the knowledge-materials directory.
     * No chunking is performed - each document is treated as an atomic unit.
     *
     * @param knowledgePath path to the knowledge-materials directory
     * @return number of documents ingested
     */
    public int ingest(Path knowledgePath) {
        System.out.println("Loading documents from " + knowledgePath);

        // 1. Load all markdown files recursively (no chunking needed)
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.md");
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively(
                knowledgePath,
                matcher,
                new TextDocumentParser()
        );
        System.out.printf("Loaded %d documents%n", documents.size());

        // 2. Enrich metadata for each document
        System.out.print("Enriching metadata");
        int enrichCount = 0;
        for (Document doc : documents) {
            enricher.enrich(doc);
            enrichCount++;
            if (enrichCount % 100 == 0) {
                System.out.print(".");
            }
        }
        System.out.println(" Done!");

        // 3. Convert to TextSegments (1:1 mapping, no splitting)
        List<TextSegment> segments = documents.stream()
                .map(doc -> TextSegment.from(doc.text(), doc.metadata()))
                .toList();

        // Print category summary
        printSummary(segments);

        // 4. Generate embeddings in batches with progress indicator
        System.out.println("Generating embeddings...");
        List<Embedding> allEmbeddings = generateEmbeddingsWithProgress(segments);

        // 5. Store embeddings
        embeddingStore.addAll(allEmbeddings, segments);
        System.out.printf("Stored %d embeddings%n", allEmbeddings.size());

        return documents.size();
    }

    /**
     * Generate embeddings in batches with a progress indicator.
     */
    private List<Embedding> generateEmbeddingsWithProgress(List<TextSegment> segments) {
        List<Embedding> allEmbeddings = new ArrayList<>();
        int totalSegments = segments.size();
        int processed = 0;

        // Calculate total batches
        int totalBatches = (int) Math.ceil((double) totalSegments / BATCH_SIZE);

        for (int i = 0; i < totalSegments; i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, totalSegments);
            List<TextSegment> batch = segments.subList(i, end);

            // Generate embeddings for this batch
            List<Embedding> batchEmbeddings = embeddingModel.embedAll(batch).content();
            allEmbeddings.addAll(batchEmbeddings);

            processed += batch.size();
            int currentBatch = (i / BATCH_SIZE) + 1;

            // Print progress bar
            printProgress(processed, totalSegments, currentBatch, totalBatches);
        }

        System.out.println();  // New line after progress bar
        return allEmbeddings;
    }

    /**
     * Print a progress bar to the console.
     */
    private void printProgress(int current, int total, int currentBatch, int totalBatches) {
        int percentage = (int) ((current / (double) total) * 100);
        int barLength = 30;
        int filledLength = (int) (barLength * current / (double) total);

        StringBuilder bar = new StringBuilder();
        bar.append("\r[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        bar.append(String.format("] %3d%% (%d/%d docs) - Batch %d/%d",
                percentage, current, total, currentBatch, totalBatches));

        System.out.print(bar);
        System.out.flush();
    }

    /**
     * Save the embedding store to a JSON file for persistence.
     *
     * @param filePath path to save the JSON file
     */
    public void saveToFile(String filePath) {
        System.out.print("Saving embeddings to " + filePath + "...");
        embeddingStore.serializeToFile(filePath);
        System.out.println(" Done!");
    }

    /**
     * Load an embedding store from a previously saved JSON file.
     *
     * @param filePath path to the JSON file
     * @return the loaded embedding store
     */
    public static InMemoryEmbeddingStore<TextSegment> loadFromFile(String filePath) {
        return InMemoryEmbeddingStore.fromFile(filePath);
    }

    /**
     * Get the embedding store for use with content retrievers.
     */
    public InMemoryEmbeddingStore<TextSegment> getEmbeddingStore() {
        return embeddingStore;
    }

    /**
     * Get the embedding model for use with content retrievers.
     */
    public EmbeddingModel getEmbeddingModel() {
        return embeddingModel;
    }

    private void printSummary(List<TextSegment> segments) {
        Map<String, Long> counts = segments.stream()
                .collect(Collectors.groupingBy(
                        s -> {
                            String category = s.metadata().getString("category");
                            return category != null ? category : "unknown";
                        },
                        Collectors.counting()
                ));

        System.out.println("Document breakdown:");
        counts.forEach((cat, count) ->
                System.out.printf("  - %s: %d files%n", cat, count));
    }
}
