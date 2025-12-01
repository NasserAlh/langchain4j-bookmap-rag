# LangChain4j Bookmap RAG Chatbot

A Java chatbot using LangChain4j with DeepSeek API and RAG (Retrieval-Augmented Generation) support for Bookmap trading platform documentation.

---

## Quick Start

### Prerequisites

- **Java 25** (project uses JDK 25 via Maven Toolchains, system default JDK 21 unchanged)
- **Maven 3.6+**
- **DeepSeek API key** (required)
- **OpenAI API key** (optional, for RAG features)

### JDK Setup

This project uses JDK 25 for JavaFX 25 GUI support. Your system default JDK remains unchanged.

```powershell
# Install JDK 25 (Windows)
winget install EclipseAdoptium.Temurin.25.JDK

# Maven automatically uses JDK 25 via toolchains (configured in ~/.m2/toolchains.xml)
mvn clean compile
```

### Environment Setup

```bash
# Required: DeepSeek API for chat functionality
export DEEPSEEK_API_KEY=your_deepseek_api_key

# Optional: OpenAI API for embeddings (RAG features)
export OPENAI_API_KEY=your_openai_api_key
```

### Running the Chatbot (CLI)

```bash
# Compile and run CLI chatbot
mvn clean compile exec:java
```

### Running the GUI (JavaFX 25)

The GUI requires JDK 25 due to JavaFX 25. Use the provided script:

```batch
# Windows - recommended way to run GUI
run-gui.bat
```

**Why a script?** The official `javafx-maven-plugin` (v0.0.8) hasn't been updated since October 2021 and doesn't support JDK 25. The script directly invokes JDK 25's `java` command with proper classpath configuration.

Alternative manual command:
```batch
mvn compile dependency:copy-dependencies -DoutputDirectory=target/lib
"C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8\bin\java" --enable-native-access=ALL-UNNAMED -cp "target/classes;target/lib/*" com.example.chatbot.Launcher
```

---

## Usage

### Basic Chat

Once running, type your message and press Enter. The chatbot maintains conversation history (last 20 messages).

```
You [Stream]: What is LangChain4j?

DeepSeek: LangChain4j is a Java library for building applications with LLMs...
```

### Commands

| Command | Description |
|---------|-------------|
| `/stream` | Toggle streaming mode (ON by default) |
| `/rag` | Toggle RAG mode for Bookmap documentation |
| `/ingest` | Build/rebuild the knowledge base from `knowledge-materials/` |
| `/clear` | Clear conversation history |
| `/temp <value>` | Set temperature (0.0-2.0) |
| `/model <name>` | Switch model (`deepseek-chat` or `deepseek-reasoner`) |
| `/usage` | Toggle token usage display |
| `/quit` | Exit the chatbot |

### Temperature Guidelines

| Value | Use Case |
|-------|----------|
| 0.0 | Coding/Math (deterministic) |
| 1.0 | Data Analysis (balanced) |
| 1.3 | General Conversation |
| 1.5 | Creative Writing |

### Using RAG Mode

RAG mode augments your queries with relevant Bookmap API documentation:

1. **First-time setup**: Run `/ingest` to build the knowledge base (~30 seconds, ~$0.002)
2. **Enable RAG**: Type `/rag` to toggle RAG mode on
3. **Ask questions**: Ask about Bookmap add-on development

```
You [Stream] [RAG]: How do I create a basic Bookmap add-on?

DeepSeek: Based on the documentation, here's how to create a basic add-on...
```

### Prompt Indicators

The prompt shows active modes:
- `[Stream]` - Streaming responses enabled
- `[RAG]` - RAG mode enabled (Bookmap docs)

---

## Features

- **Streaming responses** - See tokens as they arrive
- **RAG support** - Query Bookmap API documentation
- **Conversation memory** - Maintains context across messages
- **Retry logic** - Automatic retries on transient errors
- **Token usage tracking** - Monitor API costs
- **Model switching** - Use `deepseek-chat` or `deepseek-reasoner`
- **JavaFX 25 GUI** - Modern desktop interface with AtlantaFX theme (Phase 1 complete, run with `run-gui.bat`)

---

# RAG Processing Guide for Bookmap Knowledge Base

This guide explains how to chunk and process the files under `knowledge-materials/` for effective RAG (Retrieval-Augmented Generation) outcomes.

---

## Table of Contents

1. [Knowledge Base Overview](#knowledge-base-overview)
2. [Why No Chunking Is Needed](#why-no-chunking-is-needed)
3. [Document Loading Strategy](#document-loading-strategy)
4. [Metadata Extraction](#metadata-extraction)
5. [Embedding Generation](#embedding-generation)
6. [Implementation Code](#implementation-code)
7. [Retrieval Configuration](#retrieval-configuration)
8. [Quality Optimization Tips](#quality-optimization-tips)

---

## Knowledge Base Overview

### Directory Structure

```
knowledge-materials/
├── javadoc/
│   ├── bookmap-core-api/           # 351 files - Core API reference
│   └── bookmap-simplified-api.../  # 61 files  - Simplified API reference
├── guides/                          # 6 files   - Topic-focused tutorials
│   ├── GettingStarted.md
│   ├── DataListeners.md
│   ├── OrderBookManagement.md
│   ├── HistoricalData.md
│   ├── ApiInterface.md
│   └── OrderPlacement.md
└── examples/                        # 3 files   - Complete code examples
    ├── NasserDom.md
    ├── OnTrade.md
    └── OrderBlockStrategy.md
```

### Document Statistics

| Category | Count | Avg Tokens | Description |
|----------|-------|------------|-------------|
| Javadoc (Core API) | 351 | 100-300 | One class/interface per file |
| Javadoc (Simplified API) | 61 | 100-300 | One class/interface per file |
| Guides | 6 | 100-400 | Topic-focused tutorials |
| Examples | 3 | 200-500 | Complete working code |
| **Total** | **421** | - | All atomic units |

---

## Why No Chunking Is Needed

### Traditional RAG Problem

Most RAG systems require chunking because source documents are large (10,000+ tokens). Chunking introduces problems:

- **Broken code examples**: Splitting mid-function loses context
- **Incomplete explanations**: Concepts split across chunks
- **Lost relationships**: Class→method→parameter hierarchies broken
- **Overlap complexity**: Need overlap to maintain context, wastes tokens

### Our Solution: Pre-Chunked Documents

The knowledge base is already structured as atomic, logically-bounded documents:

| Document Type | Natural Boundaries | Why It Works |
|--------------|-------------------|--------------|
| **Javadocs** | One class/interface per file | Each file is a complete API reference unit |
| **Guides** | One topic per file | Each guide is self-contained with code examples |
| **Examples** | One complete add-on per file | Full working code with explanations |

### Benefits

1. **No chunking logic** - Simpler ingestion pipeline
2. **Complete context** - Every retrieved document is coherent
3. **Preserved code** - No broken examples or split functions
4. **Better retrieval** - Documents match semantic queries naturally
5. **Easier maintenance** - Update individual files without reprocessing

---

## Document Loading Strategy

### Unified Loading (Recommended)

Since all documents are atomic, use a single recursive loader:

```java
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.MarkdownDocumentParser;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;

public class DocumentLoader {

    private final Path knowledgePath;

    public DocumentLoader(Path knowledgePath) {
        this.knowledgePath = knowledgePath;
    }

    /**
     * Load all 421 markdown files as atomic documents.
     * No splitting required - each file is already a coherent unit.
     */
    public List<Document> loadAll() {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:**.md");

        return FileSystemDocumentLoader.loadDocumentsRecursively(
            knowledgePath,
            matcher,
            new MarkdownDocumentParser()
        );
    }
}
```

### Category-Specific Loading (Optional)

For testing or partial updates:

```java
public List<Document> loadByCategory(String category) {
    Path categoryPath = switch (category) {
        case "javadoc" -> knowledgePath.resolve("javadoc");
        case "guides" -> knowledgePath.resolve("guides");
        case "examples" -> knowledgePath.resolve("examples");
        default -> throw new IllegalArgumentException("Unknown category: " + category);
    };

    PathMatcher matcher = FileSystems.getDefault()
        .getPathMatcher("glob:**.md");

    return FileSystemDocumentLoader.loadDocumentsRecursively(
        categoryPath, matcher, new MarkdownDocumentParser()
    );
}
```

---

## Metadata Extraction

### YAML Frontmatter Structure

All documents have YAML frontmatter with rich metadata:

**Javadoc files:**
```yaml
---
source_file: Api.html
package: velox.api.layer1.simplified
classes: Api
methods: registerIndicator, sendOrder, updateOrder, ...
total_methods: 27
---
```

**Guide files:**
```yaml
---
source_file: guides/GettingStarted.md
type: guide
topic: getting-started, minimum-requirements, annotations
package: velox.api.layer1.simplified
related_classes: CustomModule, CustomModuleAdapter
annotations: Layer1SimpleAttachable, Layer1ApiVersion, ...
---
```

**Example files:**
```yaml
---
source_file: examples/NasserDom.md
type: example
topic: depth-of-market, volume-profile, gui, swing
package: velox.api.layer1.simplified
implements: CustomModule, DepthDataListener, TradeDataListener
annotations: Layer1SimpleAttachable, Layer1StrategyName, ...
---
```

### Metadata Extractor Implementation

```java
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetadataExtractor {

    private static final Pattern FRONTMATTER_PATTERN =
        Pattern.compile("^---\\n(.*?)\\n---", Pattern.DOTALL);

    /**
     * Extract YAML frontmatter as key-value pairs.
     */
    public Map<String, String> extractFrontmatter(String content) {
        Matcher matcher = FRONTMATTER_PATTERN.matcher(content);
        if (!matcher.find()) {
            return Map.of();
        }

        Map<String, String> metadata = new HashMap<>();
        String yaml = matcher.group(1);

        for (String line : yaml.split("\n")) {
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
     * Remove frontmatter from content (for cleaner embeddings).
     */
    public String removeFrontmatter(String content) {
        return FRONTMATTER_PATTERN.matcher(content).replaceFirst("").trim();
    }
}
```

### Enriching Document Metadata

```java
public void enrichMetadata(Document document) {
    String content = document.text();
    MetadataExtractor extractor = new MetadataExtractor();

    // 1. Extract YAML frontmatter
    Map<String, String> frontmatter = extractor.extractFrontmatter(content);
    frontmatter.forEach((k, v) -> document.metadata().put(k, v));

    // 2. Determine category from path
    String filePath = document.metadata().getString("absolute_directory_path");
    if (filePath != null) {
        if (filePath.contains("bookmap-core-api")) {
            document.metadata().put("api_type", "core");
            document.metadata().put("category", "javadoc");
        } else if (filePath.contains("bookmap-simplified-api")) {
            document.metadata().put("api_type", "simplified");
            document.metadata().put("category", "javadoc");
        } else if (filePath.contains("/guides/")) {
            document.metadata().put("api_type", "simplified");
            document.metadata().put("category", "guide");
        } else if (filePath.contains("/examples/")) {
            document.metadata().put("api_type", "simplified");
            document.metadata().put("category", "example");
        }
    }

    // 3. Extract document type from frontmatter or infer
    if (!document.metadata().containsKey("type")) {
        String category = document.metadata().getString("category");
        document.metadata().put("type", category != null ? category : "reference");
    }
}
```

### Key Metadata Fields for Filtering

| Field | Values | Use Case |
|-------|--------|----------|
| `category` | javadoc, guide, example | Filter by document type |
| `api_type` | core, simplified | Filter by API level |
| `package` | velox.api.layer1.* | Filter by Java package |
| `topic` | getting-started, depth-of-market, ... | Topic-based filtering |
| `classes` | Api, CustomModule, ... | Find specific class docs |
| `methods` | registerIndicator, onDepth, ... | Find method documentation |
| `annotations` | Layer1SimpleAttachable, ... | Find annotation usage |
| `implements` | DepthDataListener, ... | Find interface implementations |

---

## Embedding Generation

### Embedding Model Selection

**Recommended: OpenAI `text-embedding-3-small`**

```java
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
    .apiKey(System.getenv("OPENAI_API_KEY"))
    .modelName("text-embedding-3-small")
    .build();
```

| Feature | Value |
|---------|-------|
| Dimensions | 1536 |
| Max input | 8191 tokens |
| Cost | $0.02 / 1M tokens |
| Quality | Excellent for code/docs |

### Cost Estimation

```
421 documents × ~200 tokens avg = ~84,200 tokens
Cost: 84,200 / 1,000,000 × $0.02 = ~$0.002
```

One-time cost is negligible. Re-embedding after updates is cheap.

### Adding New Documents

To add new documentation files:

1. Place the file in the appropriate folder based on type:
   - `knowledge-materials/javadoc/bookmap-core-api/` - Core API javadocs
   - `knowledge-materials/javadoc/bookmap-simplified-api-.../` - Simplified API javadocs
   - `knowledge-materials/guides/` - Tutorial guides
   - `knowledge-materials/examples/` - Code examples

2. Run `/ingest` in the chatbot to rebuild embeddings (~30 seconds, ~$0.002)

### Batch Embedding

```java
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.embedding.Embedding;

// Convert documents to segments (1:1 since no chunking)
List<TextSegment> segments = documents.stream()
    .map(doc -> TextSegment.from(doc.text(), doc.metadata()))
    .toList();

// Generate embeddings in batch
List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
```

---

## Implementation Code

### Complete Ingestion Service

```java
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookmapIngestionService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final MetadataExtractor metadataExtractor;

    public BookmapIngestionService(EmbeddingModel embeddingModel,
                                    EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
        this.metadataExtractor = new MetadataExtractor();
    }

    /**
     * Ingest all documents from knowledge-materials.
     * No chunking needed - each document is an atomic unit.
     */
    public void ingest(Path knowledgePath) {
        System.out.println("Loading documents from " + knowledgePath);

        // 1. Load all markdown files
        DocumentLoader loader = new DocumentLoader(knowledgePath);
        List<Document> documents = loader.loadAll();
        System.out.printf("Loaded %d documents%n", documents.size());

        // 2. Enrich metadata and convert to segments
        List<TextSegment> segments = new ArrayList<>();
        for (Document doc : documents) {
            enrichMetadata(doc);
            segments.add(TextSegment.from(doc.text(), doc.metadata()));
        }

        // Print category summary
        printSummary(segments);

        // 3. Generate embeddings
        System.out.println("Generating embeddings...");
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        // 4. Store embeddings
        embeddingStore.addAll(embeddings, segments);
        System.out.printf("Stored %d embeddings%n", embeddings.size());
    }

    private void enrichMetadata(Document document) {
        String content = document.text();

        // Extract YAML frontmatter
        Map<String, String> frontmatter = metadataExtractor.extractFrontmatter(content);
        frontmatter.forEach((k, v) -> document.metadata().put(k, v));

        // Determine category from path
        String filePath = document.metadata().getString("absolute_directory_path");
        if (filePath != null) {
            if (filePath.contains("bookmap-core-api")) {
                document.metadata().put("api_type", "core");
                document.metadata().put("category", "javadoc");
            } else if (filePath.contains("bookmap-simplified-api")) {
                document.metadata().put("api_type", "simplified");
                document.metadata().put("category", "javadoc");
            } else if (filePath.contains("/guides/")) {
                document.metadata().put("api_type", "simplified");
                document.metadata().put("category", "guide");
            } else if (filePath.contains("/examples/")) {
                document.metadata().put("api_type", "simplified");
                document.metadata().put("category", "example");
            }
        }
    }

    private void printSummary(List<TextSegment> segments) {
        Map<String, Long> counts = segments.stream()
            .collect(Collectors.groupingBy(
                s -> s.metadata().getString("category"),
                Collectors.counting()
            ));

        System.out.println("Document breakdown:");
        counts.forEach((cat, count) ->
            System.out.printf("  - %s: %d files%n", cat, count));
    }
}
```

### Persistence with JSON Serialization

```java
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

// After ingestion - save to file
InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();
// ... ingest documents ...
store.serializeToFile("bookmap-embeddings.json");

// On subsequent runs - load from file
InMemoryEmbeddingStore<TextSegment> loaded =
    InMemoryEmbeddingStore.fromFile("bookmap-embeddings.json");
```

---

## Retrieval Configuration

### Basic Content Retriever

```java
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.content.retriever.ContentRetriever;

ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(5)           // Top 5 most relevant documents
    .minScore(0.7)           // Minimum similarity threshold
    .build();
```

### Metadata Filtering

```java
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

// Filter by category
ContentRetriever guideRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(3)
    .filter(metadataKey("category").isEqualTo("guide"))
    .build();

// Filter by API type
ContentRetriever simplifiedApiRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(5)
    .filter(metadataKey("api_type").isEqualTo("simplified"))
    .build();

// Combine filters
ContentRetriever exampleRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(3)
    .filter(metadataKey("category").isEqualTo("example")
        .and(metadataKey("topic").containsString("volume-profile")))
    .build();
```

### Dynamic Filtering Based on Query

```java
ContentRetriever dynamicRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(5)
    .dynamicFilter(query -> {
        String text = query.text().toLowerCase();

        // Route to examples for "how to" questions
        if (text.contains("example") || text.contains("how to") ||
            text.contains("implement")) {
            return metadataKey("category").isIn("example", "guide");
        }

        // Route to specific class docs
        if (text.contains("custommodule")) {
            return metadataKey("classes").containsString("CustomModule");
        }

        // Default: no filter (search everything)
        return null;
    })
    .build();
```

---

## Quality Optimization Tips

### 1. Retrieval Parameters

| Parameter | Recommended | Rationale |
|-----------|-------------|-----------|
| `maxResults` | 5 | Balance context vs. noise |
| `minScore` | 0.7 | Filter irrelevant results |

Adjust based on testing:
- Lower `minScore` (0.5-0.6) for broader recall
- Increase `maxResults` (7-10) for complex queries

### 2. Query Enhancement

Prepend context to improve retrieval:

```java
String enhancedQuery = "Bookmap add-on development: " + userQuery;
```

### 3. Category-Aware Retrieval

Match query type to document category:

| Query Pattern | Best Category | Filter |
|---------------|---------------|--------|
| "How do I...", "Show me example" | examples, guides | `category IN (example, guide)` |
| "What is...", "Explain..." | guides, javadoc | No filter |
| "Api for...", "Method to..." | javadoc | `category = javadoc` |
| Class/interface names | javadoc | `classes CONTAINS <name>` |

### 4. Evaluation Checklist

Test retrieval quality with these query types:

```java
Map<String, String> testQueries = Map.of(
    // Basic API questions
    "What interfaces do I need for a basic add-on?",
    "CustomModule, CustomModuleAdapter",

    // Annotation questions
    "What annotations are required for trading?",
    "Layer1TradingStrategy",

    // Method-specific
    "How do I receive depth updates?",
    "DepthDataListener, onDepth",

    // Code examples
    "Show me a DOM implementation",
    "NasserDom",

    // Complex integration
    "How to place orders with stop loss?",
    "SimpleOrderSendParametersBuilder"
);
```

### 5. When to Consider Chunking

The current structure works well because documents are already atomic. Consider chunking only if:

- Adding very long documents (>2000 tokens)
- Documents cover multiple unrelated topics
- Retrieval consistently returns irrelevant context

If chunking becomes necessary, use:
```java
DocumentSplitter splitter = DocumentSplitters.recursive(
    700,   // tokens per chunk
    100,   // overlap
    new OpenAiTokenCountEstimator("text-embedding-3-small")
);
```

---

## Summary

### Key Principles

1. **No chunking needed** - Documents are pre-structured as atomic units
2. **Rich metadata** - YAML frontmatter enables powerful filtering
3. **Category-aware retrieval** - Match query intent to document type
4. **Simple pipeline** - Load → Enrich metadata → Embed → Store

### Processing Pipeline

```
                    ┌─────────────────┐
                    │  knowledge-     │
                    │  materials/     │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │ FileSystem      │
                    │ DocumentLoader  │
                    │ (421 files)     │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │ Metadata        │
                    │ Enrichment      │
                    │ (YAML + path)   │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │ OpenAI          │
                    │ Embeddings      │
                    │ (text-emb-3-s)  │
                    └────────┬────────┘
                             │
                    ┌────────▼────────┐
                    │ Embedding       │
                    │ Store           │
                    │ (InMemory/DB)   │
                    └─────────────────┘
```

### Expected Results

- **421 embeddings** stored (1:1 with documents)
- **~$0.002** one-time embedding cost
- **<3 sec** typical retrieval time
- **>80%** relevant documents in top-5 for well-formed queries
