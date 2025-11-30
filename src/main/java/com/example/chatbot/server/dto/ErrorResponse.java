package com.example.chatbot.server.dto;

import java.time.Instant;
import java.util.UUID;

/**
 * Structured error response with correlation ID for debugging.
 * The errorId can be used to correlate frontend errors with backend logs.
 */
public class ErrorResponse {
    private String errorId;
    private String code;
    private String message;
    private String details;
    private long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(String code, String message, String details) {
        this.errorId = UUID.randomUUID().toString().substring(0, 8);
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = Instant.now().toEpochMilli();
    }

    /**
     * Error codes for different failure scenarios
     */
    public static class Codes {
        public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
        public static final String MODEL_ERROR = "MODEL_ERROR";
        public static final String RATE_LIMITED = "RATE_LIMITED";
        public static final String AUTH_FAILED = "AUTH_FAILED";
        public static final String RAG_ERROR = "RAG_ERROR";
        public static final String CONFIG_ERROR = "CONFIG_ERROR";
        public static final String NETWORK_ERROR = "NETWORK_ERROR";
        public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    }

    // Factory methods for common errors
    public static ErrorResponse validation(String message) {
        return new ErrorResponse(Codes.VALIDATION_ERROR, message, null);
    }

    public static ErrorResponse modelError(String message, Throwable cause) {
        String details = cause != null ? cause.getClass().getSimpleName() + ": " + cause.getMessage() : null;
        return new ErrorResponse(Codes.MODEL_ERROR, message, details);
    }

    public static ErrorResponse rateLimited() {
        return new ErrorResponse(Codes.RATE_LIMITED, "Too many requests. Please wait and try again.", null);
    }

    public static ErrorResponse authFailed() {
        return new ErrorResponse(Codes.AUTH_FAILED, "API authentication failed. Check your API key.", null);
    }

    public static ErrorResponse ragError(String message, Throwable cause) {
        String details = cause != null ? cause.getClass().getSimpleName() + ": " + cause.getMessage() : null;
        return new ErrorResponse(Codes.RAG_ERROR, message, details);
    }

    public static ErrorResponse internal(Throwable cause) {
        String message = cause.getMessage() != null ? cause.getMessage() : "An unexpected error occurred";
        String details = cause.getClass().getSimpleName();
        return new ErrorResponse(Codes.INTERNAL_ERROR, message, details);
    }

    public static ErrorResponse internal(String message, Throwable cause) {
        String details = cause != null ? cause.getClass().getSimpleName() + ": " + cause.getMessage() : null;
        return new ErrorResponse(Codes.INTERNAL_ERROR, message, details);
    }

    // Getters and Setters
    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
