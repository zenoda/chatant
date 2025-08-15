package com.github.zenoda.chatant;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ChatAgent {
    private HttpClient httpClient;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private HttpClient httpClient;

        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public ChatAgent build() {
            ChatAgent chatAgent = new ChatAgent();
            chatAgent.httpClient = Optional.ofNullable(httpClient).orElse(HttpClient.newHttpClient());
            return chatAgent;
        }
    }

    public ChatResponse apply(ChatRequest request) {
        HttpRequest httpRequest = request.buildHttpRequest();
        InputStream stream = null;
        Throwable error = null;
        try {
            stream = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream()).body();
        } catch (IOException | InterruptedException e) {
            error = e;
        }
        return request.responseBuilder()
                .stream(stream)
                .error(error)
                .build();
    }
}
