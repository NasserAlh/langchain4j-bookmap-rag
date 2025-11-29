# Bookmap RAG Chatbot - User Manual

A chatbot powered by DeepSeek and LangChain4j with RAG (Retrieval-Augmented Generation) support for Bookmap API documentation.

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setup](#setup)
3. [Running the Chatbot](#running-the-chatbot)
4. [Commands](#commands)
5. [Streaming Mode](#streaming-mode)
6. [RAG Mode](#rag-mode)
7. [Example Usage](#example-usage)
8. [Troubleshooting](#troubleshooting)

---

## Prerequisites

- **Java 21** (Eclipse Adoptium recommended)
- **Maven** (for building)
- **API Keys:**
  - `DEEPSEEK_API_KEY` - Required for the chat model
  - `OPENAI_API_KEY` - Required for RAG embeddings

---

## Setup

### 1. Clone and Build

```bash
cd langchain4j-bookmap-rag
mvn clean compile
```

### 2. Set Environment Variables

**Windows (PowerShell):**
```powershell
$env:DEEPSEEK_API_KEY = "your-deepseek-api-key"
$env:OPENAI_API_KEY = "your-openai-api-key"
```

**Windows (Command Prompt):**
```cmd
set DEEPSEEK_API_KEY=your-deepseek-api-key
set OPENAI_API_KEY=your-openai-api-key
```

**Linux/macOS/Git Bash:**
```bash
export DEEPSEEK_API_KEY=your-deepseek-api-key
export OPENAI_API_KEY=your-openai-api-key
```

---

## Running the Chatbot

```bash
mvn exec:java
```

You'll see:
```
===========================================
  DeepSeek Chatbot (powered by LangChain4j)
===========================================
Type your message and press Enter to chat.

Commands:
  /stream  - Toggle streaming mode (ON by default)
  /rag     - Toggle RAG mode (Bookmap docs)
  /ingest  - Build/rebuild knowledge base
  /clear   - Clear conversation history
  /quit    - Exit

You [Stream]:
```

---

## Commands

| Command | Description |
|---------|-------------|
| `/stream` | Toggle streaming mode on/off. When ON (default), responses appear token-by-token in real-time. |
| `/rag` | Toggle RAG mode on/off. When ON, queries are augmented with relevant Bookmap documentation. |
| `/ingest` | Build or rebuild the knowledge base from `knowledge-materials/`. Run this once before using RAG, or after updating documentation. |
| `/clear` | Clear conversation history. Useful for starting a fresh context. |
| `/quit` or `/exit` | Exit the chatbot. |

---

## Streaming Mode

Streaming mode displays responses in real-time as tokens are generated, providing a more interactive experience.

### How It Works

When streaming is enabled:
- The prompt shows `You [Stream]:`
- Responses appear character-by-character as they're generated
- You see the response being "typed out" in real-time
- No waiting for the complete response before seeing any output

### Toggling Streaming

```
You [Stream]: /stream
Streaming mode: OFF

You: Hello!

DeepSeek: Hello! How can I help you today?

You: /stream
Streaming mode: ON

You [Stream]: Hello!

DeepSeek: H e l l o !   H o w   c a n   I   h e l p ...
```

### Combining with RAG

Streaming works with RAG mode enabled:
```
You [Stream] [RAG]: How do I implement a depth listener?

DeepSeek: To implement a depth listener, you need to...
```

The prompt indicator shows all active modes: `[Stream]`, `[RAG]`, or `[Stream] [RAG]`.

---

## RAG Mode

RAG (Retrieval-Augmented Generation) enhances the chatbot's responses by automatically retrieving relevant Bookmap documentation and including it in the context.

### First-Time Setup

Before using RAG, you must build the knowledge base:

```
You: /ingest
Loading documents from knowledge-materials
Loaded 421 documents
Document breakdown:
  - javadoc: 412 files
  - guide: 6 files
  - example: 3 files
Generating embeddings...
Stored 421 embeddings
Embeddings saved to data/bookmap-embeddings.json
Ingestion complete: 421 documents indexed.
```

This creates `data/bookmap-embeddings.json` which is reused on subsequent runs.

### Enabling RAG

```
You [Stream]: /rag
RAG mode: ON

You [Stream] [RAG]: How do I receive depth updates?
```

When RAG is enabled:
- The prompt shows `[RAG]` indicator
- Your questions are matched against Bookmap documentation
- Relevant docs are included in the context sent to DeepSeek
- Responses are grounded in actual API documentation

### Disabling RAG

```
You [Stream] [RAG]: /rag
RAG mode: OFF

You [Stream]:
```

---

## Example Usage

### Example 1: Getting Started with Bookmap Add-ons

```
You [Stream] [RAG]: What are the minimum requirements to create a Bookmap add-on?

DeepSeek: To create a Bookmap add-on, you need:

1. **Required Annotations:**
   - `@Layer1SimpleAttachable` - Makes the add-on attachable to instruments
   - `@Layer1ApiVersion` - Specifies API version compatibility
   - `@Layer1StrategyName` - Display name shown in Bookmap UI

2. **Required Implementation:**
   Implement either:
   - `CustomModule` interface (requires `initialize()` and `stop()` methods)
   - `CustomModuleAdapter` (provides empty defaults, recommended for simple add-ons)

**Minimal example:**
```java
@Layer1SimpleAttachable
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class HelloBookmap implements CustomModuleAdapter {
}
```
```

### Example 2: Finding Code Examples

```
You [Stream] [RAG]: Show me an example of a DOM implementation

DeepSeek: Here's the NasserDom example, which implements a Depth of Market display:

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Nasser Dom")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy
public class NasserDom implements CustomModule, DepthDataListener, TradeDataListener {

    @Override
    public void onDepth(boolean isBid, int price, int size) {
        // Handle depth updates
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        // Handle trade updates
    }
}
```

Key features:
- Uses `DepthDataListener` for order book updates
- Uses `TradeDataListener` for volume profile tracking
- Batch processes updates for performance
```

### Example 3: API-Specific Questions

```
You [Stream] [RAG]: What methods does the Api interface provide for placing orders?

DeepSeek: The `Api` interface provides these order-related methods:

- `sendOrder(OrderSendParameters params)` - Send a new order
- `updateOrder(OrderUpdateParameters params)` - Modify an existing order
- `cancelOrder(OrderCancelParameters params)` - Cancel an order

Use `SimpleOrderSendParametersBuilder` to construct order parameters:
```java
api.sendOrder(
    SimpleOrderSendParametersBuilder.newBuilder()
        .setOrderType(OrderType.LMT)
        .setSide(Side.BUY)
        .setSize(1)
        .setLimitPrice(price)
        .build()
);
```
```

---

## Troubleshooting

### "DEEPSEEK_API_KEY environment variable is not set"

Set your DeepSeek API key before running:
```bash
export DEEPSEEK_API_KEY=your-key-here
```

### "OPENAI_API_KEY not set. RAG features disabled."

RAG requires an OpenAI API key for embeddings:
```bash
export OPENAI_API_KEY=your-key-here
```

### "No embeddings found. Run /ingest first"

The knowledge base hasn't been built yet. Run `/ingest` to create it.

### "Knowledge directory not found: knowledge-materials"

Ensure the `knowledge-materials/` directory exists in the project root with the Bookmap documentation files.

### Slow Response Times

- First query after startup may be slower due to model loading
- Large context (many retrieved documents) increases response time
- Consider reducing `maxResults` in `BookmapContentRetriever` if needed

### Irrelevant Results

Try being more specific in your query. The RAG system uses semantic matching, so:
- Instead of: "orders"
- Try: "How do I place a limit order with stop loss?"

---

## Architecture Overview

```
┌──────────────────┐     ┌──────────────────┐
│   User Query     │────▶│  RAG Retriever   │
└──────────────────┘     │  (if RAG ON)     │
                         └────────┬─────────┘
                                  │
                         ┌────────▼─────────┐
                         │ Embedding Store  │
                         │ (421 documents)  │
                         └────────┬─────────┘
                                  │
                         ┌────────▼─────────┐
                         │ Context Builder  │
                         │ (Top 5 matches)  │
                         └────────┬─────────┘
                                  │
                         ┌────────▼─────────┐
                         │   DeepSeek LLM   │
                         │ (Streaming/Sync) │
                         └────────┬─────────┘
                                  │
┌──────────────────┐              │
│    Response      │◀─────────────┘
│  (token stream   │
│   or complete)   │
└──────────────────┘
```

---

## File Locations

| Path | Description |
|------|-------------|
| `knowledge-materials/` | Source documentation (markdown files) |
| `data/bookmap-embeddings.json` | Serialized embedding store |
| `src/main/java/com/example/chatbot/` | Application source code |

---

## Cost Considerations

- **Embedding (one-time):** ~$0.002 for 421 documents using `text-embedding-3-small`
- **Chat queries:** Standard DeepSeek API pricing per token
- **Re-ingestion:** Only needed when documentation changes
