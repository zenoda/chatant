package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.PartialMessage;

public class ResultPartialMessage extends PartialMessage {
    public static final String TYPE = "result";
    private String text;

    public ResultPartialMessage() {
        this.setType(TYPE);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String text;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public ResultPartialMessage build() {
            ResultPartialMessage result = new ResultPartialMessage();
            result.setText(text);
            return result;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ResultPartialMessage [type=");
        buffer.append(TYPE);
        buffer.append(", text=");
        buffer.append(text);
        buffer.append("]");
        return buffer.toString();
    }
}
