package com.example.chatbot.server.dto;

public class ChatResponse {
    private String response;
    private TokenUsage usage;

    public ChatResponse() {
    }

    public ChatResponse(String response, TokenUsage usage) {
        this.response = response;
        this.usage = usage;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public TokenUsage getUsage() {
        return usage;
    }

    public void setUsage(TokenUsage usage) {
        this.usage = usage;
    }
}
