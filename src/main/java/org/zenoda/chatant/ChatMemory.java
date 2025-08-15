package org.zenoda.chatant;

import java.util.List;

public interface ChatMemory {
    /**
     * 存入新消息
     *
     * @param message 新消息
     */
    void addMessage(String conversationId, ChatMessage message);

    /**
     * 获取记忆中的消息
     *
     * @param conversationId 对话ID
     * @return 消息列表
     */
    List<ChatMessage> getMessages(String conversationId);
}
