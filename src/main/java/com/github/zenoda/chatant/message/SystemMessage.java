package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.ChatMessage;
import com.github.zenoda.chatant.ChatRole;

public class SystemMessage extends ChatMessage {
    public SystemMessage() {
        setRole(ChatRole.SYSTEM);
    }
}
