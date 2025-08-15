package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.ChatMessage;
import com.github.zenoda.chatant.ChatRole;

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
