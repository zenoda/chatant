package org.zenoda.chatant;

import org.zenoda.chatant.adaptor.OpenaiChatRequest;
import org.zenoda.chatant.message.UserMessage;
import org.junit.Test;

import java.util.List;

public class Test1 {
    @Test
    public void test1() {
        String apiKey = System.getenv("CHATANT_API_KEY");
        String conversationId = "000";
        ChatAgent.builder()
                .build()
                .apply(OpenaiChatRequest.builder()
                        .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                        .apiKey(apiKey)
                        .modelName("qwen3-32b")
                        .temperature(0)
                        .conversationId(conversationId)
                        .messages(List.of(UserMessage.builder()
                                .content("雷雨天要注意什么？")
                                .build()))
                        .build())
                .onComplete(full -> {
                    System.out.println(full);
                })
                .onMessage(msg -> {
                    System.out.println(msg);
                })
                .subscribe();
    }
}
