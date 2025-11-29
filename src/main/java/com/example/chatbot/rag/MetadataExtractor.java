package com.example.chatbot.rag;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts YAML frontmatter metadata from markdown documents.
 *
 * Expected frontmatter format:
 * ---
 * source_file: example.md
 * type: guide
 * topic: getting-started, annotations
 * package: velox.api.layer1.simplified
 * ---
 */
public class MetadataExtractor {

    // Pattern matches YAML frontmatter block (handles both Unix and Windows line endings)
    private static final Pattern FRONTMATTER_PATTERN =
            Pattern.compile("^---\\r?\\n(.*?)\\r?\\n---", Pattern.DOTALL);

    /**
     * Extract YAML frontmatter as key-value pairs.
     *
     * @param content the full document content
     * @return map of metadata key-value pairs, empty map if no frontmatter
     */
    public Map<String, String> extractFrontmatter(String content) {
        Matcher matcher = FRONTMATTER_PATTERN.matcher(content);
        if (!matcher.find()) {
            return Map.of();
        }

        Map<String, String> metadata = new HashMap<>();
        String yaml = matcher.group(1);

        for (String line : yaml.split("\\r?\\n")) {
            int colonIndex = line.indexOf(':');
            if (colonIndex > 0) {
                String key = line.substring(0, colonIndex).trim();
                String value = line.substring(colonIndex + 1).trim();
                metadata.put(key, value);
            }
        }

        return metadata;
    }

    /**
     * Remove frontmatter from content (for cleaner text in embeddings).
     *
     * @param content the full document content
     * @return content without the YAML frontmatter block
     */
    public String removeFrontmatter(String content) {
        return FRONTMATTER_PATTERN.matcher(content).replaceFirst("").trim();
    }

    /**
     * Check if the content has YAML frontmatter.
     *
     * @param content the document content
     * @return true if frontmatter exists
     */
    public boolean hasFrontmatter(String content) {
        return FRONTMATTER_PATTERN.matcher(content).find();
    }
}
