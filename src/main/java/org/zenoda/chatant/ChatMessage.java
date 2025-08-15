package org.zenoda.chatant;

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
