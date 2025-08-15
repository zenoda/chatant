package org.zenoda.chatant.message;

import org.zenoda.chatant.ChatMessage;
import org.zenoda.chatant.ChatRole;

public class SystemMessage extends ChatMessage {
    public SystemMessage() {
        setRole(ChatRole.SYSTEM);
    }
}
