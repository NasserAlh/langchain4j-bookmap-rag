# Bug Fixes Report: RAG Hallucination Issue

## Executive Summary

The Svelte 5 frontend chatbot was generating hallucinated responses about Bookmap API classes despite having RAG (Retrieval-Augmented Generation) enabled. The root cause was a missing code path in the streaming endpoint that bypassed RAG entirely.

---

## Issue Description

**Symptom:** When asking "what this class does? InstrumentInfo.BuilderBase", the chatbot returned detailed but **completely fabricated** information including:
- Non-existent methods like `setCurrency()`, `setTickSize()`, `setDescription()`
- Fictional code examples with made-up API signatures
- Invented use cases and explanations

**Expected Behavior:** The chatbot should answer based only on the actual Bookmap API documentation stored in the RAG vector database.

---

## Root Cause Analysis

### Bug #1: Streaming Endpoint Ignored RAG (Critical)

**File:** `src/main/java/com/example/chatbot/server/ChatbotServer.java:78`

```java
// BEFORE (Bug): Always called non-RAG method
chatbot.chatStreamingCallback(request.getMessage(), ...);

// AFTER (Fix): Check RAG status first
if (chatbot.isRagEnabled()) {
    chatbot.chatWithRagStreamingCallback(request.getMessage(), ...);
} else {
    chatbot.chatStreamingCallback(request.getMessage(), ...);
}
```

**Impact:** 100% of streaming requests bypassed RAG, even when RAG was enabled in the UI.

### Bug #2: Permissive RAG Prompt Encouraged Hallucination

**File:** `src/main/java/com/example/chatbot/DeepSeekChatbot.java:384-395`

```java
// BEFORE (Bug): Encouraged making up examples
return """
    Use the following Bookmap API documentation to answer the question.
    If the documentation doesn't contain relevant information, say so.
    Always provide code examples when relevant.  // <-- This line caused hallucinations!
    ...
    """;

// AFTER (Fix): Strict grounding instructions
return """
    You are a technical assistant for Bookmap API documentation.

    CRITICAL RULES:
    1. ONLY use information that is EXPLICITLY stated in the documentation context below.
    2. DO NOT invent, assume, or hallucinate any methods, fields, parameters, or behaviors.
    3. If the documentation doesn't contain specific information, say "This is not documented in the provided context."
    4. When showing code examples, ONLY use methods and signatures that appear in the documentation.
    5. If you're uncertain whether something exists, say so explicitly.
    6. Quote or reference the documentation directly when possible.
    ...
    """;
```

### Bug #3: No Debugging Visibility

There was no logging to verify:
- Whether RAG was being used for streaming requests
- Which documents were being retrieved
- The similarity scores of retrieved documents

---

## Fixes Applied

| # | Fix | File | Line |
|---|-----|------|------|
| 1 | Added RAG check to streaming endpoint | `ChatbotServer.java` | 78-172 |
| 2 | Added `RAG enabled: {}` log statement | `ChatbotServer.java` | 66 |
| 3 | Rewrote RAG prompt with anti-hallucination rules | `DeepSeekChatbot.java` | 394-412 |
| 4 | Added retrieved document logging | `DeepSeekChatbot.java` | 380-387 |
| 5 | Increased minScore from 0.6 to 0.7 | `DeepSeekChatbot.java` | 124-127 |
| 6 | Increased maxResults from 5 to 8 | `DeepSeekChatbot.java` | 124-127 |

---

## Before vs After Comparison

### Before (Hallucinated Response)
```
The InstrumentInfo.BuilderBase class provides a fluent interface...

// Made-up code:
builder.setSymbol("BTCUSDT")
       .setCurrency("USDT")        // Does not exist
       .setTickSize(0.01)          // Does not exist
       .setDescription("...")      // Does not exist
```

### After (Accurate Response)
```
InstrumentInfo.BuilderBase is an abstract static class that extends
InstrumentCoreInfo.BuilderBase...

Field management - It contains protected fields:
- pips (minimal price increment/tick size)     // Actual field
- multiplier                                    // Actual field
- fullName                                      // Actual field
- isFullDepth                                   // Actual field
...
```

---

## Lessons Learned

### 1. Test All Code Paths
The non-streaming endpoint (`/api/chat`) correctly used RAG, but the streaming endpoint (`/api/chat/stream`) did not. Both paths should have been tested independently.

**Action Item:** Create integration tests that verify RAG is applied to both streaming and non-streaming endpoints.

### 2. Never Trust "Always Provide Examples"
Telling an LLM to "always provide code examples" when it may not have sufficient context is an invitation to hallucinate.

**Action Item:** RAG prompts should explicitly forbid fabrication and require the model to admit uncertainty.

### 3. Add Observability to RAG Systems
Without logging, there was no way to know:
- If RAG was being used
- What documents were retrieved
- Whether the retrieval was relevant

**Action Item:** Always log:
- RAG enabled/disabled status per request
- Number of documents retrieved
- Document sources and preview snippets
- Similarity scores

### 4. Consistent Code Patterns
The non-streaming endpoint used a controller method that checked `isRagEnabled()`:
```java
if (chatbot.isRagEnabled()) {
    result = chatbot.chatWithRagAndMetadata(request.getMessage());
} else {
    result = chatbot.chatWithMetadata(request.getMessage());
}
```

But the streaming endpoint was implemented inline without the same check. Copy-paste or rushed implementation led to the bug.

**Action Item:** Extract common patterns into reusable methods or use a consistent template for all endpoints.

### 5. RAG Threshold Tuning Matters
A minScore of 0.6 was too permissive, potentially returning marginally relevant documents that confused the model.

**Action Item:** Start with a higher threshold (0.7-0.8) and lower it only if recall becomes an issue.

---

## Verification Steps

1. Start the server: `mvn exec:java -Pserver`
2. Open frontend: http://localhost:5173
3. Ensure RAG is enabled (green toggle in sidebar)
4. Clear chat history
5. Ask: "what this class does? InstrumentInfo.BuilderBase"
6. Verify server logs show:
   ```
   RAG enabled: true
   [RAG] Retrieved X documents:
   [RAG]   1. InstrumentInfo.BuilderBase.md: ...
   ```
7. Verify response contains only documented fields (pips, multiplier, fullName, etc.)

---

## Files Modified

```
src/main/java/com/example/chatbot/
├── DeepSeekChatbot.java          # RAG prompt + logging + thresholds
└── server/
    └── ChatbotServer.java        # Streaming endpoint RAG fix
```

---

## Conclusion

This bug highlights the importance of:
1. **Testing feature flags across all code paths**
2. **Defensive prompting for RAG systems**
3. **Observable AI systems with proper logging**
4. **Consistent implementation patterns**

The fix ensures the chatbot now provides accurate, documentation-grounded responses instead of confidently hallucinating plausible-sounding but incorrect information.
