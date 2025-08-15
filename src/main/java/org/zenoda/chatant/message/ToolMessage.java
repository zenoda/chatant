package org.zenoda.chatant.message;

import org.zenoda.chatant.ChatMessage;
import org.zenoda.chatant.ChatRole;

public class ToolMessage extends ChatMessage {
    private String toolCallId;

    public ToolMessage() {
        this.setRole(ChatRole.TOOL);
    }

    public String getToolCallId() {
        return toolCallId;
    }

    public void setToolCallId(String toolCallId) {
        this.toolCallId = toolCallId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatMessage.Builder {
        private String toolCallId;

        public Builder toolCallId(String toolCallId) {
            this.toolCallId = toolCallId;
            return this;
        }

        @Override
        public ToolMessage build() {
            ToolMessage toolMessage = new ToolMessage();
            _build(toolMessage);
            toolMessage.setToolCallId(toolCallId);
            return toolMessage;
        }
    }
}
