package org.zenoda.chatant.message;

import org.zenoda.chatant.ChatMessage;
import org.zenoda.chatant.ChatRole;

public class SystemMessage extends ChatMessage {
    public SystemMessage() {
        setRole(ChatRole.SYSTEM);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatMessage.Builder {
        @Override
        public SystemMessage build() {
            return new SystemMessage();
        }
    }
}
