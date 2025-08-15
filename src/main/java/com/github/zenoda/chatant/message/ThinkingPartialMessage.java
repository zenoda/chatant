package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.PartialMessage;

public class ThinkingPartialMessage extends PartialMessage {
    public static final String TYPE = "thinking";
    private String text;

    public ThinkingPartialMessage() {
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

        public ThinkingPartialMessage build() {
            ThinkingPartialMessage thinkingPartialMessage = new ThinkingPartialMessage();
            thinkingPartialMessage.setText(text);
            return thinkingPartialMessage;
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
        StringBuilder builder = new StringBuilder();
        builder.append("ThinkingPartialMessage [type=");
        builder.append(getType());
        builder.append(", text=");
        builder.append(text);
        builder.append("]");
        return builder.toString();
    }
}
