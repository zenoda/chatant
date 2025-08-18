package org.zenoda.chatant.message;

import org.zenoda.chatant.PartialMessage;

public class ErrorPartialMessage extends PartialMessage {
    public static final String TYPE = "error";
    private String error;

    public ErrorPartialMessage() {
        setType(TYPE);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String error;

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public ErrorPartialMessage build() {
            ErrorPartialMessage errorPartialMessage = new ErrorPartialMessage();
            errorPartialMessage.error = error;
            return errorPartialMessage;
        }
    }

    @Override
    public String toString() {
        return "ErrorPartialMessage[type=" + getType() + ", error=" + getError() + "]";
    }
}
