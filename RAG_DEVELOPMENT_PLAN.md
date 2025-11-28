# RAG System Development Plan for Bookmap API Documentation

This document outlines a comprehensive plan for building a Retrieval-Augmented Generation (RAG) system using LangChain4j to provide intelligent Q&A capabilities over the Bookmap trading platform API documentation.

---

## Executive Summary

**Objective:** Build a RAG-powered chatbot that can answer questions about Bookmap add-on development by retrieving relevant documentation from the knowledge base.

**Knowledge Base:**
- 421 markdown files total
- 351 Core API javadoc files (`javadoc/bookmap-core-api/`)
- 61 Simplified API javadoc files (`javadoc/bookmap-simplified-api-documentation-2025-11-20/`)
- 6 Guide files (`guides/`) - logically divided from comprehensive guide
- 3 Example files (`examples/`) - complete code examples

**Tech Stack:**
- Java 21
- LangChain4j v1.9.0
- OpenAI Embeddings (`text-embedding-3-small`)
- DeepSeek API (OpenAI-compatible) for chat
- InMemoryEmbeddingStore (development) / Persistent store (production)

**Document Splitting Strategy:** No splitting required
- All files are now atomic units with logical boundaries
- Guides: 6 topic-focused files (100-400 tokens each)
- Examples: 3 complete code examples (200-500 tokens each)
- Javadocs: 412 class/interface reference files (100-300 tokens each)

---

## Table of Contents

1. [Phase 1: Project Setup](#phase-1-project-setup)
2. [Phase 2: Document Ingestion Pipeline](#phase-2-document-ingestion-pipeline)
3. [Phase 3: Embedding Store & Retrieval](#phase-3-embedding-store--retrieval)
4. [Phase 4: RAG Integration with Chat Model](#phase-4-rag-integration-with-chat-model)
5. [Phase 5: Advanced Features](#phase-5-advanced-features)
6. [Phase 6: Testing & Optimization](#phase-6-testing--optimization)
7. [Implementation Details](#implementation-details)

---

## Phase 1: Project Setup

### 1.1 Update Dependencies

Update `pom.xml` with required LangChain4j modules:

```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <langchain4j.version>1.9.0</langchain4j.version>
</properties>

<dependencies>
    <!-- LangChain4j Core -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

    <!-- LangChain4j OpenAI (for DeepSeek) -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

    <!-- Markdown Document Parser -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-document-parser-markdown</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

    <!-- YAML Document Parser (for frontmatter) -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-document-parser-yaml</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

    <!-- SLF4J for logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>2.0.9</version>
    </dependency>
</dependencies>
```

### 1.2 Project Structure

```
src/main/java/com/example/
├── chatbot/
│   └── DeepSeekChatbot.java          # Existing chatbot (refactor)
├── rag/
│   ├── BookmapRagApplication.java    # Main RAG application entry point
│   ├── config/
│   │   └── RagConfig.java            # Configuration holder
│   ├── ingestion/
│   │   ├── DocumentIngestionService.java
│   │   ├── MetadataExtractor.java    # Extract YAML frontmatter
│   │   └── CustomMarkdownSplitter.java
│   ├── retrieval/
│   │   ├── BookmapContentRetriever.java
│   │   └── MetadataFilterHelper.java
│   ├── assistant/
│   │   ├── BookmapAssistant.java     # AI Service interface
│   │   └── BookmapAssistantImpl.java
│   └── store/
│       └── EmbeddingStoreManager.java
└── util/
    └── PathConstants.java
```

---

## Phase 2: Document Ingestion Pipeline

### 2.1 Document Loading Strategy

All documents are now atomic units and can be loaded uniformly:

```java
public class DocumentLoader {

    private final Path knowledgePath;

    public DocumentLoader(Path knowledgePath) {
        this.knowledgePath = knowledgePath;
    }

    /**
     * Load all markdown files from the knowledge base.
     * All files are atomic units - no splitting required:
     * - Javadocs: 412 files (one class/interface per file)
     * - Guides: 6 topic-focused files
     * - Examples: 3 complete code examples
     */
    public List<Document> loadAllDocuments() {
        PathMatcher markdownMatcher = FileSystems.getDefault().getPathMatcher("glob:**.md");

        return FileSystemDocumentLoader.loadDocumentsRecursively(
            knowledgePath,
            markdownMatcher,
            new MarkdownDocumentParser()
        );
    }

    /**
     * Load specific document categories if needed
     */
    public List<Document> loadByCategory(String category) {
        Path categoryPath = switch (category) {
            case "javadoc" -> knowledgePath.resolve("javadoc");
            case "guides" -> knowledgePath.resolve("guides");
            case "examples" -> knowledgePath.resolve("examples");
            default -> knowledgePath;
        };

        PathMatcher markdownMatcher = FileSystems.getDefault().getPathMatcher("glob:**.md");
        return FileSystemDocumentLoader.loadDocumentsRecursively(
            categoryPath, markdownMatcher, new MarkdownDocumentParser()
        );
    }
}
```

### 2.2 Metadata Extraction

The knowledge-materials files have YAML frontmatter that should be extracted as metadata:

```yaml
---
source_file: Api.html
package: velox.api.layer1.simplified
classes: Api
methods: registerIndicator, sendOrder, updateOrder, ...
---
```

**Metadata fields to extract:**
| Field | Purpose | Filterable |
|-------|---------|------------|
| `source_file` | Original file reference | Yes |
| `package` | Java package | Yes |
| `classes` | Class names | Yes |
| `methods` | Method names | Yes |
| `type` | Document type (guide/example/reference) | Yes |
| `topic` | Topic tags | Yes |
| `annotations` | Required annotations | Yes |

### 2.3 Document Structure (No Splitting Required)

The knowledge base is now structured as atomic, logically-bounded documents:

| Document Type | Count | Avg Size | Category | Description |
|--------------|-------|----------|----------|-------------|
| Javadoc files | 412 | 100-300 tokens | `javadoc` | One class/interface per file |
| Guide files | 6 | 100-400 tokens | `guides` | Topic-focused tutorials |
| Example files | 3 | 200-500 tokens | `examples` | Complete working code |

**Guide Files (`guides/`):**
| File | Topic | Contents |
|------|-------|----------|
| `GettingStarted.md` | Minimum requirements, annotations | Basic add-on structure |
| `DataListeners.md` | Market data callbacks | TimeListener, DepthDataListener, TradeDataListener, etc. |
| `OrderBookManagement.md` | Order book building | MBP/MBO implementations |
| `HistoricalData.md` | Historical data handling | Pre-subscription, backfill, replay |
| `ApiInterface.md` | Api interface methods | Listeners, indicators, settings |
| `OrderPlacement.md` | Order submission | SimpleOrderSendParametersBuilder |

**Example Files (`examples/`):**
| File | Topic | Contents |
|------|-------|----------|
| `NasserDom.md` | DOM with volume profile | Complete Swing GUI, batch processing |
| `OnTrade.md` | Volume profile panel | POC/Value Area calculation |
| `OrderBlockStrategy.md` | Trading strategy | Settings, indicators, order placement |

**Benefits of this structure:**
- No splitting logic needed - each file is a complete, coherent unit
- Metadata filtering by `type` (guide/example/reference)
- Better retrieval quality - no broken code examples or incomplete explanations
- Easier maintenance - update individual topics without reprocessing everything

### 2.4 Ingestion Pipeline Implementation

```java
public class DocumentIngestionService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final MetadataExtractor metadataExtractor = new MetadataExtractor();

    public DocumentIngestionService(EmbeddingModel embeddingModel,
                                    EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    /**
     * Ingest all documents as atomic units (no splitting required)
     */
    public void ingestDocuments(Path knowledgePath) {
        List<TextSegment> allSegments = new ArrayList<>();

        // Load all markdown files recursively
        System.out.println("Loading all documents...");
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.md");
        List<Document> allDocs = FileSystemDocumentLoader.loadDocumentsRecursively(
            knowledgePath, matcher, new MarkdownDocumentParser()
        );

        // Enrich metadata and convert to segments
        for (Document doc : allDocs) {
            enrichMetadata(doc);
            allSegments.add(TextSegment.from(doc.text(), doc.metadata()));
        }

        System.out.printf("  Loaded %d documents:%n", allDocs.size());
        printCategorySummary(allSegments);

        // Generate embeddings and store
        System.out.printf("Generating embeddings for %d segments...%n", allSegments.size());
        List<Embedding> embeddings = embeddingModel.embedAll(allSegments).content();

        embeddingStore.addAll(embeddings, allSegments);
        System.out.printf("Ingestion complete: %d segments stored%n", allSegments.size());
    }

    private void printCategorySummary(List<TextSegment> segments) {
        Map<String, Long> counts = segments.stream()
            .collect(Collectors.groupingBy(
                s -> s.metadata().getString("category"),
                Collectors.counting()
            ));
        counts.forEach((cat, count) -> System.out.printf("    - %s: %d files%n", cat, count));
    }

    private void enrichMetadata(Document document) {
        String content = document.text();

        // Extract YAML frontmatter
        Map<String, String> frontmatter = metadataExtractor.extractFrontmatter(content);
        frontmatter.forEach((k, v) -> document.metadata().put(k, v));

        // Add document category based on path
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
}
```

---

## Phase 3: Embedding Store & Retrieval

### 3.1 Embedding Model: OpenAI text-embedding-3-small

```java
EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
    .apiKey(System.getenv("OPENAI_API_KEY"))
    .modelName("text-embedding-3-small")
    .build();
```

**Why `text-embedding-3-small`:**

| Feature | Value |
|---------|-------|
| Dimensions | 1536 |
| Max input tokens | 8191 |
| Cost | $0.02 / 1M tokens |
| Quality | Excellent for technical documentation |

**Estimated embedding cost for your knowledge base:**
- ~412 javadoc files × ~200 tokens avg = ~82,400 tokens
- ~6 guide files × ~300 tokens avg = ~1,800 tokens
- ~3 example files × ~400 tokens avg = ~1,200 tokens
- **Total: ~85,400 tokens ≈ $0.002** (one-time cost)

### 3.2 Embedding Store Options

**Development: InMemoryEmbeddingStore**
```java
InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

// Persist to file for faster subsequent loads
embeddingStore.serializeToFile("bookmap-embeddings.json");

// Load from file
InMemoryEmbeddingStore<TextSegment> loaded =
    InMemoryEmbeddingStore.fromFile("bookmap-embeddings.json");
```

**Production Options:**
- PostgreSQL with pgvector
- MongoDB Atlas Vector Search
- Elasticsearch
- Chroma
- Pinecone

### 3.3 Content Retriever Configuration

```java
ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(5)                    // Return top 5 relevant chunks
    .minScore(0.7)                    // Minimum similarity threshold
    .dynamicFilter(query -> {
        // Dynamic filtering based on query context
        if (query.text().contains("CustomModule")) {
            return metadataKey("classes").containsString("CustomModule");
        }
        return null;  // No filter
    })
    .build();
```

### 3.4 Metadata Filtering Examples

```java
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

// Filter by package
Filter packageFilter = metadataKey("package")
    .isEqualTo("velox.api.layer1.simplified");

// Filter by document type
Filter typeFilter = metadataKey("type")
    .isIn("guide", "example");

// Combine filters
Filter combinedFilter = packageFilter.and(typeFilter);
```

---

## Phase 4: RAG Integration with Chat Model

### 4.1 AI Service Interface

Define an interface for the Bookmap assistant:

```java
public interface BookmapAssistant {

    @SystemMessage("""
        You are an expert assistant for Bookmap trading platform add-on development.
        You help developers understand and use the Bookmap Simplified API.

        When answering questions:
        1. Provide accurate Java code examples when relevant
        2. Reference specific interfaces, classes, and methods
        3. Explain required annotations and their purposes
        4. Include best practices and common patterns

        If you're unsure about something, say so rather than guessing.
        Always mention which interface or class you're referencing.
        """)
    String chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
```

### 4.2 Building the Assistant

```java
public class BookmapAssistantBuilder {

    public BookmapAssistant build(
            EmbeddingStore<TextSegment> embeddingStore,
            EmbeddingModel embeddingModel) {

        // Chat model using DeepSeek
        ChatModel chatModel = OpenAiChatModel.builder()
            .baseUrl("https://api.deepseek.com")
            .apiKey(System.getenv("DEEPSEEK_API_KEY"))
            .modelName("deepseek-chat")
            .temperature(0.3)  // Lower for more factual responses
            .build();

        // Content retriever
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .maxResults(5)
            .minScore(0.7)
            .build();

        // Chat memory per user
        ChatMemoryProvider memoryProvider = memoryId ->
            MessageWindowChatMemory.withMaxMessages(20);

        // Build AI Service
        return AiServices.builder(BookmapAssistant.class)
            .chatModel(chatModel)
            .contentRetriever(contentRetriever)
            .chatMemoryProvider(memoryProvider)
            .build();
    }
}
```

### 4.3 Main Application

```java
public class BookmapRagApplication {

    private static final Path KNOWLEDGE_PATH = Paths.get("knowledge-materials");
    private static final Path STORE_PATH = Paths.get("bookmap-embeddings.json");

    public static void main(String[] args) {
        // Initialize OpenAI embedding model
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .modelName("text-embedding-3-small")
            .build();

        // Initialize or load embedding store
        InMemoryEmbeddingStore<TextSegment> embeddingStore =
            loadOrCreateEmbeddingStore(embeddingModel);

        // Build assistant
        BookmapAssistant assistant = new BookmapAssistantBuilder()
            .build(embeddingStore, embeddingModel);

        // Interactive chat loop
        Scanner scanner = new Scanner(System.in);
        String sessionId = UUID.randomUUID().toString();

        System.out.println("Bookmap API Assistant ready. Type /quit to exit.");
        System.out.println("Commands: /quit, /clear (reset memory), /reindex (rebuild embeddings)");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("/quit")) break;
            if (input.equalsIgnoreCase("/clear")) {
                sessionId = UUID.randomUUID().toString();
                System.out.println("Memory cleared.");
                continue;
            }
            if (input.equalsIgnoreCase("/reindex")) {
                reindexDocuments(embeddingModel, embeddingStore);
                continue;
            }
            if (input.isEmpty()) continue;

            String response = assistant.chat(sessionId, input);
            System.out.println("\nAssistant: " + response);
        }
    }

    private static InMemoryEmbeddingStore<TextSegment> loadOrCreateEmbeddingStore(
            EmbeddingModel embeddingModel) {

        if (Files.exists(STORE_PATH)) {
            System.out.println("Loading existing embedding store from " + STORE_PATH);
            return InMemoryEmbeddingStore.fromFile(STORE_PATH.toString());
        }

        System.out.println("Creating new embedding store (first run)...");
        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        // Ingest documents using hybrid strategy
        DocumentIngestionService ingestionService =
            new DocumentIngestionService(embeddingModel, store);
        ingestionService.ingestDocuments(KNOWLEDGE_PATH);

        // Persist for future use
        store.serializeToFile(STORE_PATH.toString());
        System.out.println("Embedding store saved to " + STORE_PATH);

        return store;
    }

    private static void reindexDocuments(EmbeddingModel embeddingModel,
                                         InMemoryEmbeddingStore<TextSegment> store) {
        System.out.println("Reindexing all documents...");

        // Clear existing embeddings
        store = new InMemoryEmbeddingStore<>();

        // Re-ingest
        DocumentIngestionService ingestionService =
            new DocumentIngestionService(embeddingModel, store);
        ingestionService.ingestDocuments(KNOWLEDGE_PATH);

        // Save
        store.serializeToFile(STORE_PATH.toString());
        System.out.println("Reindexing complete.");
    }
}
```

---

## Phase 5: Advanced Features

### 5.1 Query Routing

Route queries to specialized retrievers based on intent:

```java
public class QueryRouter {

    public ContentRetriever route(String query) {
        // Simple keyword-based routing
        if (containsAny(query, "order", "trade", "buy", "sell")) {
            return tradingRetriever;  // Filter: type=trading
        }
        if (containsAny(query, "depth", "book", "MBP", "MBO")) {
            return orderBookRetriever;  // Filter: topic=order-book
        }
        if (containsAny(query, "example", "code", "implement")) {
            return exampleRetriever;  // Filter: type=example
        }
        return defaultRetriever;
    }
}
```

### 5.2 Hybrid Search

Combine semantic search with keyword search:

```java
// Semantic retriever
ContentRetriever semanticRetriever = EmbeddingStoreContentRetriever.builder()
    .embeddingStore(embeddingStore)
    .embeddingModel(embeddingModel)
    .maxResults(3)
    .build();

// Keyword retriever (BM25-style)
ContentRetriever keywordRetriever = new KeywordContentRetriever(documents);

// Combine results
List<Content> combined = Stream.concat(
    semanticRetriever.retrieve(query).stream(),
    keywordRetriever.retrieve(query).stream()
).distinct().collect(Collectors.toList());
```

### 5.3 Re-ranking

Re-rank retrieved documents using the LLM:

```java
public class LlmReranker {

    private final ChatModel chatModel;

    public List<Content> rerank(String query, List<Content> candidates) {
        String prompt = """
            Query: %s

            Rank these document chunks by relevance (most relevant first):
            %s

            Return only the numbers in order, separated by commas.
            """.formatted(query, formatCandidates(candidates));

        String response = chatModel.chat(prompt);
        return reorderByRanking(candidates, parseRanking(response));
    }
}
```

### 5.4 Context Compression

Compress retrieved context to fit more relevant information:

```java
public class ContextCompressor {

    private final ChatModel chatModel;

    public String compress(String query, List<Content> contents) {
        String combinedContent = contents.stream()
            .map(Content::textSegment)
            .map(TextSegment::text)
            .collect(Collectors.joining("\n\n---\n\n"));

        String prompt = """
            Given this query: "%s"

            Extract only the parts of this documentation that are relevant:

            %s

            Return a compressed version containing only relevant information.
            """.formatted(query, combinedContent);

        return chatModel.chat(prompt);
    }
}
```

---

## Phase 6: Testing & Optimization

### 6.1 Test Queries

Create a comprehensive test suite with expected behaviors:

```java
public class RagTestSuite {

    private static final Map<String, String> TEST_QUERIES = Map.of(
        // Basic API questions
        "What interfaces do I need to implement for a basic Bookmap add-on?",
        "CustomModule or CustomModuleAdapter",

        // Annotation questions
        "What annotations are required for a trading strategy?",
        "Layer1SimpleAttachable, Layer1ApiVersion, Layer1TradingStrategy",

        // Method-specific questions
        "How do I receive depth updates in my add-on?",
        "DepthDataListener, onDepth",

        // Code example questions
        "Show me an example of placing a limit order",
        "SimpleOrderSendParametersBuilder",

        // Complex integration questions
        "How do I build a DOM display with volume profile?",
        "NasserDom example"
    );

    public void runTests(BookmapAssistant assistant) {
        TEST_QUERIES.forEach((query, expectedContent) -> {
            String response = assistant.chat("test", query);
            boolean passed = response.toLowerCase()
                .contains(expectedContent.toLowerCase());
            System.out.printf("[%s] %s%n",
                passed ? "PASS" : "FAIL", query);
        });
    }
}
```

### 6.2 Retrieval Quality Metrics

```java
public class RetrievalMetrics {

    public double measurePrecision(List<Content> retrieved, Set<String> relevant) {
        long relevantRetrieved = retrieved.stream()
            .filter(c -> relevant.contains(c.textSegment().metadata().getString("source_file")))
            .count();
        return (double) relevantRetrieved / retrieved.size();
    }

    public double measureRecall(List<Content> retrieved, Set<String> relevant) {
        long relevantRetrieved = retrieved.stream()
            .filter(c -> relevant.contains(c.textSegment().metadata().getString("source_file")))
            .count();
        return (double) relevantRetrieved / relevant.size();
    }

    public double measureMRR(List<Content> retrieved, Set<String> relevant) {
        for (int i = 0; i < retrieved.size(); i++) {
            String sourceFile = retrieved.get(i).textSegment()
                .metadata().getString("source_file");
            if (relevant.contains(sourceFile)) {
                return 1.0 / (i + 1);
            }
        }
        return 0.0;
    }
}
```

### 6.3 Optimization Parameters

| Parameter | Range | Recommended | Notes |
|-----------|-------|-------------|-------|
| `maxResults` | 3-10 | **5** | More results = more context but slower |
| `minScore` | 0.5-0.9 | **0.7** | Lower for broader recall |
| `temperature` | 0.0-1.0 | **0.3** | Lower for factual responses |

**Document Structure Summary:**

| Document Type | Count | Splitting | Rationale |
|--------------|-------|-----------|-----------|
| Javadoc files | 412 | None | Already atomic, avg 100-300 tokens |
| Guide files | 6 | None | Logically divided, avg 100-400 tokens |
| Example files | 3 | None | Complete code examples, avg 200-500 tokens |

---

## Implementation Details

### 7.1 Custom Metadata Extractor

```java
public class MetadataExtractor {

    private static final Pattern FRONTMATTER_PATTERN =
        Pattern.compile("^---\\n(.*?)\\n---", Pattern.DOTALL);

    public Map<String, String> extractFrontmatter(String content) {
        Matcher matcher = FRONTMATTER_PATTERN.matcher(content);
        if (!matcher.find()) {
            return Collections.emptyMap();
        }

        Map<String, String> metadata = new HashMap<>();
        String yaml = matcher.group(1);

        for (String line : yaml.split("\n")) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                metadata.put(parts[0].trim(), parts[1].trim());
            }
        }

        return metadata;
    }

    public String removeFromtmatter(String content) {
        return FRONTMATTER_PATTERN.matcher(content).replaceFirst("");
    }
}
```

### 7.2 Custom Document Transformer

```java
public class BookmapDocumentTransformer implements DocumentTransformer {

    private final MetadataExtractor metadataExtractor = new MetadataExtractor();

    @Override
    public Document transform(Document document) {
        String content = document.text();

        // Extract and add frontmatter metadata
        Map<String, String> frontmatter = metadataExtractor.extractFrontmatter(content);
        frontmatter.forEach((k, v) -> document.metadata().put(k, v));

        // Determine document category
        String filePath = document.metadata().getString("absolute_directory_path");
        if (filePath.contains("bookmap-core-api")) {
            document.metadata().put("api_type", "core");
        } else if (filePath.contains("bookmap-simplified-api")) {
            document.metadata().put("api_type", "simplified");
        } else {
            document.metadata().put("api_type", "guide");
        }

        return document;
    }
}
```

### 7.3 Streaming Response Support

```java
public interface BookmapStreamingAssistant {

    @SystemMessage("You are an expert Bookmap add-on development assistant...")
    TokenStream chat(@MemoryId String memoryId, @UserMessage String userMessage);
}

// Usage with streaming
StreamingChatModel streamingModel = OpenAiStreamingChatModel.builder()
    .baseUrl("https://api.deepseek.com")
    .apiKey(System.getenv("DEEPSEEK_API_KEY"))
    .modelName("deepseek-chat")
    .build();

BookmapStreamingAssistant assistant = AiServices.builder(BookmapStreamingAssistant.class)
    .streamingChatModel(streamingModel)
    .contentRetriever(contentRetriever)
    .build();

// Stream response
assistant.chat(sessionId, userQuery)
    .onPartialResponse(System.out::print)
    .onComplete(response -> System.out.println("\n[Done]"))
    .onError(Throwable::printStackTrace)
    .start();
```

---

## Milestones Checklist

### Foundation
- [ ] Update pom.xml with new dependencies (LangChain4j 1.9.0, markdown parser)
- [ ] Set up OpenAI API key environment variable
- [ ] Create project structure (`rag/`, `ingestion/`, `retrieval/`, etc.)
- [ ] Test OpenAI embedding model connection

### Ingestion Pipeline
- [ ] Implement MetadataExtractor for YAML frontmatter
- [ ] Build DocumentLoader (unified loading, no splitting needed)
- [ ] Implement DocumentIngestionService
- [ ] Ingest all 421 markdown files (412 javadoc + 6 guides + 3 examples)
- [ ] Verify embedding store persistence (JSON serialization)

### Retrieval & Chat Integration
- [ ] Configure EmbeddingStoreContentRetriever
- [ ] Build BookmapAssistant AI Service interface
- [ ] Integrate with DeepSeek chat model
- [ ] Implement CLI chat loop with commands

### Testing & Refinement
- [ ] Create test query suite (see Section 6.1)
- [ ] Measure retrieval precision/recall
- [ ] Tune minScore and maxResults parameters
- [ ] Add metadata filtering for api_type

### Advanced Features (Optional)
- [ ] Implement query routing by topic
- [ ] Add streaming response support
- [ ] Re-ranking with LLM
- [ ] Hybrid search (semantic + keyword)

---

## Success Criteria

1. **Retrieval Accuracy:** >80% of test queries return relevant documents in top-5
2. **Response Quality:** Answers reference correct APIs, methods, and code patterns
3. **Performance:** <3 second response time for typical queries
4. **Coverage:** Can answer questions about all major Bookmap API features
5. **Reliability:** Graceful handling of unknown queries with honest uncertainty

---

## Resources & References

- [LangChain4j Documentation](https://docs.langchain4j.dev)
- [LangChain4j RAG Tutorial](https://docs.langchain4j.dev/tutorials/rag)
- [OpenAI Embeddings API](https://platform.openai.com/docs/guides/embeddings)
- [DeepSeek API Documentation](https://api-docs.deepseek.com)
- [Example Code: InMemoryEmbeddingStoreExample.java](langchain4j-doc/InMemoryEmbeddingStoreExample.java)
- [Example Code: FileSystemDocumentLoaderTest.java](langchain4j-doc/FileSystemDocumentLoaderTest.java)
- [Example Code: MarkdownDocumentParserTest.java](langchain4j-doc/MarkdownDocumentParserTest.java)

---

## Environment Variables Required

```bash
# OpenAI API key for embeddings
export OPENAI_API_KEY=sk-...

# DeepSeek API key for chat
export DEEPSEEK_API_KEY=sk-...
```
