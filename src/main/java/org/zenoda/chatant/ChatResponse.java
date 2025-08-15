package org.zenoda.chatant;

import org.zenoda.chatant.message.AssistantMessage;

import java.io.InputStream;
import java.util.function.Consumer;

public abstract class ChatResponse {
    private String conversationId;
    private InputStream stream;
    private Throwable error;
    private Consumer<PartialMessage> messageConsumer;
    private Consumer<AssistantMessage> completeConsumer;
    private Consumer<Error> errorConsumer;

    protected String getConversationId() {
        return conversationId;
    }

    protected Throwable getError() {
        return error;
    }

    protected InputStream getStream() {
        return stream;
    }

    protected Consumer<PartialMessage> getMessageConsumer() {
        return messageConsumer;
    }

    protected Consumer<AssistantMessage> getCompleteConsumer() {
        return completeConsumer;
    }

    protected Consumer<Error> getErrorConsumer() {
        return errorConsumer;
    }

    public ChatResponse onError(Consumer<Error> errorConsumer) {
        this.errorConsumer = errorConsumer;
        return this;
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
        private String conversationId;
        private InputStream stream;
        private Throwable error;

        public Builder conversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public Builder stream(InputStream stream) {
            this.stream = stream;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        protected ChatResponse _build(ChatResponse response) {
            response.stream = stream;
            response.error = error;
            response.conversationId = conversationId;
            return response;
        }

        public abstract ChatResponse build();
    }

    public abstract void subscribe();
}
