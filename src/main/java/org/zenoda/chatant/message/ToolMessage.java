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
}
