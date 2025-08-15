package com.github.zenoda.chatant;

import java.net.http.HttpRequest;
import java.util.List;

public abstract class ChatRequest {
    private String baseUrl;
    private String apiKey;
    private String modelName;
    private Float temperature;
    private Integer topK;
    private Float topP;
    private Integer seed;
    private Long maxTokens;
    private Integer timeout;
    private String conversationId;
    private List<? extends ChatMessage> messages;
    private List<ToolSpecification> tools;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getTopK() {
        return topK;
    }

    public void setTopK(Integer topK) {
        this.topK = topK;
    }

    public Float getTopP() {
        return topP;
    }

    public void setTopP(Float topP) {
        this.topP = topP;
    }

    public Integer getSeed() {
        return seed;
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public Long getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Long maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public List<? extends ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<? extends ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ToolSpecification> getTools() {
        return tools;
    }

    public void setTools(List<ToolSpecification> tools) {
        this.tools = tools;
    }

    public abstract HttpRequest buildHttpRequest();

    public abstract static class Builder {
        protected String baseUrl;
        protected String apiKey;
        protected String modelName;
        protected Float temperature;
        protected Integer topK;
        protected Float topP;
        protected Integer seed;
        protected Long maxTokens;
        protected Integer timeout;
        protected String conversationId;
        protected List<? extends ChatMessage> messages;
        protected List<ToolSpecification> tools;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder modelName(String modelName) {
            this.modelName = modelName;
            return this;
        }

        public Builder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder topK(int topK) {
            this.topK = topK;
            return this;
        }

        public Builder topP(float topP) {
            this.topP = topP;
            return this;
        }

        public Builder seed(int seed) {
            this.seed = seed;
            return this;
        }

        public Builder maxTokens(long maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder conversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public Builder messages(List<? extends ChatMessage> messages) {
            this.messages = messages;
            return this;
        }

        public Builder tools(List<ToolSpecification> tools) {
            this.tools = tools;
            return this;
        }

        protected void _build(ChatRequest chatRequest) {
            chatRequest.baseUrl = baseUrl;
            chatRequest.apiKey = apiKey;
            chatRequest.modelName = modelName;
            chatRequest.temperature = temperature;
            chatRequest.topK = topK;
            chatRequest.topP = topP;
            chatRequest.seed = seed;
            chatRequest.maxTokens = maxTokens;
            chatRequest.timeout = timeout;
            chatRequest.conversationId = conversationId;
            chatRequest.messages = messages;
            chatRequest.tools = tools;
        }

        public abstract ChatRequest build();
    }

    public abstract ChatResponse.Builder responseBuilder();
}
