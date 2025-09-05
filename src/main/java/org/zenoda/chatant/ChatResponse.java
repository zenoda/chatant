package org.zenoda.chatant;

import org.zenoda.chatant.message.AssistantMessage;

import java.io.InputStream;
import java.util.function.Consumer;

public abstract class ChatResponse {
    private InputStream inputStream;
    private Boolean streaming;
    private Throwable error;
    private Consumer<PartialMessage> messageConsumer;
    private Consumer<AssistantMessage> completeConsumer;

    protected Boolean getStreaming() {
        return streaming;
    }

    protected Throwable getError() {
        return error;
    }

    protected InputStream getInputStream() {
        return inputStream;
    }

    protected Consumer<PartialMessage> getMessageConsumer() {
        return messageConsumer;
    }

    protected Consumer<AssistantMessage> getCompleteConsumer() {
        return completeConsumer;
    }

    public ChatResponse onMessage(Consumer<PartialMessage> messageConsumer) {
        this.messageConsumer = messageConsumer;
        return this;
    }

    public ChatResponse onComplete(Consumer<AssistantMessage> completeConsumer) {
        this.completeConsumer = completeConsumer;
        return this;
    }

    public abstract static class Builder {
        private InputStream inputStream;
        private Boolean streaming;
        private Throwable error;

        public Builder streaming(Boolean streaming) {
            this.streaming = streaming;
            return this;
        }

        public Builder inputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        protected void _build(ChatResponse response) {
            response.inputStream = inputStream;
            response.streaming = streaming;
            response.error = error;
        }

        public abstract ChatResponse build();
    }

    public abstract void subscribe();
}
