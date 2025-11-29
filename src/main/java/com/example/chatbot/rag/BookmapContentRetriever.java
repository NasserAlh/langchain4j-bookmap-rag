package com.example.chatbot.rag;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * Factory for creating content retrievers with category-aware filtering.
 *
 * Supports dynamic filtering based on query patterns:
 * - "how to" / "example" queries → examples and guides
 * - Class name queries → javadoc
 * - General queries → no filter (search all)
 */
public class BookmapContentRetriever {

    /**
     * Create a content retriever with dynamic category filtering.
     *
     * @param store      the embedding store
     * @param model      the embedding model (must match the one used for ingestion)
     * @param maxResults maximum number of results to return
     * @param minScore   minimum similarity score threshold (0.0 to 1.0)
     * @return configured content retriever
     */
    public ContentRetriever create(
            EmbeddingStore<TextSegment> store,
            EmbeddingModel model,
            int maxResults,
            double minScore) {

        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .embeddingModel(model)
                .maxResults(maxResults)
                .minScore(minScore)
                .dynamicFilter(query -> {
                    String text = query.text().toLowerCase();

                    // Route "how to" and example requests to examples/guides
                    if (text.contains("example") ||
                        text.contains("how to") ||
                        text.contains("how do i") ||
                        text.contains("show me") ||
                        text.contains("implement")) {
                        return metadataKey("category").isIn("example", "guide");
                    }

                    // Route API/method questions to javadoc
                    if (text.contains("api") ||
                        text.contains("method") ||
                        text.contains("interface") ||
                        text.contains("class")) {
                        return metadataKey("category").isEqualTo("javadoc");
                    }

                    // Default: no filter (search everything)
                    return null;
                })
                .build();
    }

    /**
     * Create a simple retriever without dynamic filtering.
     */
    public ContentRetriever createSimple(
            EmbeddingStore<TextSegment> store,
            EmbeddingModel model,
            int maxResults,
            double minScore) {

        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .embeddingModel(model)
                .maxResults(maxResults)
                .minScore(minScore)
                .build();
    }

    /**
     * Create a retriever filtered to a specific category.
     *
     * @param category one of: "javadoc", "guide", "example"
     */
    public ContentRetriever createForCategory(
            EmbeddingStore<TextSegment> store,
            EmbeddingModel model,
            String category,
            int maxResults,
            double minScore) {

        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .embeddingModel(model)
                .maxResults(maxResults)
                .minScore(minScore)
                .filter(metadataKey("category").isEqualTo(category))
                .build();
    }
}
