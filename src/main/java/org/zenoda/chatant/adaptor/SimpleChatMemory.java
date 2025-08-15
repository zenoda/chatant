package org.zenoda.chatant.adaptor;

import org.zenoda.chatant.ChatMemory;
import org.zenoda.chatant.ChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleChatMemory implements ChatMemory {
    private static int historySize = 10;
    private final Map<String, List<ChatMessage>> memory;

    public SimpleChatMemory() {
        this.memory = new HashMap<>();
    }

    @Override
    public void addMessage(String conversationId, ChatMessage message) {
        List<ChatMessage> messages = memory.get(conversationId);
        if (messages == null) {
            messages = new ArrayList<>();
            this.memory.put(conversationId, messages);
        }
        messages.add(message);
        while (messages.size() > historySize) {
            messages.removeFirst();
        }
    }

    @Override
    public List<ChatMessage> getMessages(String conversationId) {
        return memory.get(conversationId);
    }

    public static class Builder {
        public SimpleChatMemory build() {
            return new SimpleChatMemory();
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
