package com.example.chatbot.server.dto;

import dev.langchain4j.model.chat.response.ChatResponseMetadata;

public record ChatResult(String content, ChatResponseMetadata metadata) {}
