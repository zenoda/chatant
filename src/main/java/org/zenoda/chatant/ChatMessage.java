package org.zenoda.chatant;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.zenoda.chatant.message.AssistantMessage;
import org.zenoda.chatant.message.SystemMessage;
import org.zenoda.chatant.message.ToolMessage;
import org.zenoda.chatant.message.UserMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SystemMessage.class, name = "SYSTEM"),
        @JsonSubTypes.Type(value = UserMessage.class, name = "USER"),
        @JsonSubTypes.Type(value = ToolMessage.class, name = "TOOL"),
        @JsonSubTypes.Type(value = AssistantMessage.class, name = "ASSISTANT")
})
public abstract class ChatMessage {
    private ChatRole role;
    private String content;

    public ChatRole getRole() {
        return role;
    }

    public void setRole(ChatRole role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static abstract class Builder {
        private String content;

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        protected void _build(ChatMessage chatMessage) {
            chatMessage.setContent(content);
        }

        public abstract ChatMessage build();
    }
}
