package com.example.chatbot.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;

import java.util.Map;

/**
 * Enriches documents with metadata extracted from:
 * 1. YAML frontmatter
 * 2. File path (for category and API type classification)
 */
public class DocumentEnricher {

    private final MetadataExtractor metadataExtractor;

    public DocumentEnricher() {
        this.metadataExtractor = new MetadataExtractor();
    }

    /**
     * Enrich a document's metadata with:
     * - YAML frontmatter fields
     * - Category (javadoc, guide, example)
     * - API type (core, simplified)
     *
     * @param document the document to enrich
     */
    public void enrich(Document document) {
        String content = document.text();
        Metadata metadata = document.metadata();

        // 1. Extract and add YAML frontmatter fields
        Map<String, String> frontmatter = metadataExtractor.extractFrontmatter(content);
        frontmatter.forEach(metadata::put);

        // 2. Determine category and API type from file path
        String filePath = metadata.getString("absolute_directory_path");
        if (filePath != null) {
            classifyFromPath(filePath, metadata);
        }

        // 3. Set default type if not specified in frontmatter
        if (!metadata.containsKey("type")) {
            String category = metadata.getString("category");
            metadata.put("type", category != null ? category : "reference");
        }
    }

    /**
     * Classify document category and API type based on file path.
     */
    private void classifyFromPath(String filePath, Metadata metadata) {
        // Normalize path separators for cross-platform compatibility
        String normalizedPath = filePath.replace("\\", "/");

        if (normalizedPath.contains("bookmap-core-api")) {
            metadata.put("api_type", "core");
            metadata.put("category", "javadoc");
        } else if (normalizedPath.contains("bookmap-simplified-api")) {
            metadata.put("api_type", "simplified");
            metadata.put("category", "javadoc");
        } else if (normalizedPath.contains("/guides/") || normalizedPath.contains("/guides")) {
            metadata.put("api_type", "simplified");
            metadata.put("category", "guide");
        } else if (normalizedPath.contains("/examples/") || normalizedPath.contains("/examples")) {
            metadata.put("api_type", "simplified");
            metadata.put("category", "example");
        }
    }
}
