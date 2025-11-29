package com.example.chatbot.server.dto;

public class SettingsDto {
    private String model;
    private double temperature;
    private boolean ragEnabled;
    private boolean streamingEnabled;
    private boolean showUsage;

    public SettingsDto() {
    }

    public SettingsDto(String model, double temperature, boolean ragEnabled,
                       boolean streamingEnabled, boolean showUsage) {
        this.model = model;
        this.temperature = temperature;
        this.ragEnabled = ragEnabled;
        this.streamingEnabled = streamingEnabled;
        this.showUsage = showUsage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRagEnabled() {
        return ragEnabled;
    }

    public void setRagEnabled(boolean ragEnabled) {
        this.ragEnabled = ragEnabled;
    }

    public boolean isStreamingEnabled() {
        return streamingEnabled;
    }

    public void setStreamingEnabled(boolean streamingEnabled) {
        this.streamingEnabled = streamingEnabled;
    }

    public boolean isShowUsage() {
        return showUsage;
    }

    public void setShowUsage(boolean showUsage) {
        this.showUsage = showUsage;
    }
}
