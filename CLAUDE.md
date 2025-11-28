# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java 21 chatbot application using **LangChain4j** (v1.0.0-beta1) to interact with DeepSeek's OpenAI-compatible API. The project serves as both a functional chatbot and a learning platform for LangChain4j features.

## Build Commands

```bash
# Compile
mvn clean compile

# Run the chatbot
mvn exec:java

# Package as JAR
mvn package

# View dependency tree
mvn dependency:tree
```

## Environment Setup

Requires `DEEPSEEK_API_KEY` environment variable:
```bash
export DEEPSEEK_API_KEY=your_api_key
```

## Architecture

**Single-module Maven project** with one main class:

- `com.example.chatbot.DeepSeekChatbot` - Console REPL chatbot
  - Uses `OpenAiChatModel` configured for DeepSeek endpoint (`https://api.deepseek.com`)
  - `MessageWindowChatMemory` maintains last 20 messages for context
  - Model: `deepseek-chat` (or `deepseek-coder` for code tasks)
  - Commands: `/clear` (reset memory), `/quit` or `/exit` (end session)

**Key LangChain4j patterns used:**
- Builder pattern for model configuration
- `ChatMemory` interface for conversation state
- `Response<AiMessage>` for typed API responses

## Directory Structure

| Directory | Purpose |
|-----------|---------|
| `src/main/java/` | Application source code |
| `langchain4j-doc/` | LangChain4j example code (document loaders, parsers, embedding stores) |
| `knowledge-materials/` | Reference documentation for Bookmap trading platform APIs |
| `target/` | Maven build output |

## Dependencies

Three direct dependencies (managed in `pom.xml`):
- `dev.langchain4j:langchain4j` - Core framework
- `dev.langchain4j:langchain4j-open-ai` - OpenAI-compatible integration
- `org.slf4j:slf4j-simple` - Logging

## LangChain4j Examples

The `langchain4j-doc/` directory contains reference implementations:
- `InMemoryEmbeddingStoreExample.java` - Vector store for semantic search
- `FileSystemDocumentLoaderTest.java` / `ClassPathDocumentLoaderTest.java` - Document loading
- `MarkdownDocumentParserTest.java` / `YamlDocumentParserTest.java` - Document parsing
