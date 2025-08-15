package org.zenoda.chatant.message;

import org.zenoda.chatant.ChatMessage;
import org.zenoda.chatant.ChatRole;
import org.zenoda.chatant.ToolCallSpecification;

import java.util.List;

public class AssistantMessage extends ChatMessage {
    private List<ToolCallSpecification> toolCalls;
    private String reasoningContent;

    public AssistantMessage() {
        this.setRole(ChatRole.ASSISTANT);
    }

    public String getReasoningContent() {
        return reasoningContent;
    }

    public void setReasoningContent(String reasoningContent) {
        this.reasoningContent = reasoningContent;
    }

    public List<ToolCallSpecification> getToolCalls() {
        return toolCalls;
    }

    public void setToolCalls(List<ToolCallSpecification> toolCalls) {
        this.toolCalls = toolCalls;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AssistantMessage [role=")
                .append(getRole())
                .append(", reasoningContent=")
                .append(getReasoningContent())
                .append(", content=")
                .append(getContent())
                .append(", toolCalls=")
                .append(getToolCalls())
                .append("]");
        return builder.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatMessage.Builder {
        private List<ToolCallSpecification> toolCalls;
        private String reasoningContent;

        public Builder toolCalls(List<ToolCallSpecification> toolCalls) {
            this.toolCalls = toolCalls;
            return this;
        }

        public Builder reasoningContent(String reasoningContent) {
            this.reasoningContent = reasoningContent;
            return this;
        }

        @Override
        public AssistantMessage build() {
            AssistantMessage message = new AssistantMessage();
            _build(message);
            message.setToolCalls(toolCalls);
            message.setReasoningContent(reasoningContent);
            return message;
        }
    }
}
