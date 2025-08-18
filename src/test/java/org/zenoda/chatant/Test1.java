package org.zenoda.chatant;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.zenoda.chatant.adaptor.OpenaiChatRequest;
import org.zenoda.chatant.adaptor.SimpleChatMemory;
import org.zenoda.chatant.message.UserMessage;
import org.junit.Test;

import java.util.List;

public class Test1 {
    @Test
    public void test1() {
        String apiKey = System.getenv("CHATANT_API_KEY");
        String conversationId = "000";
        SimpleChatMemory simpleChatMemory = SimpleChatMemory.builder().build();
        simpleChatMemory.addMessage(conversationId, UserMessage.builder()
                .content("我喜欢北京的天气，你呢？")
                .build());
        ChatAgent.builder()
                .build()
                .apply(OpenaiChatRequest.builder()
                        .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                        .apiKey(apiKey)
                        .modelName("qwen3-32b")
                        .temperature(0)
                        .conversationId(conversationId)
                        .messages(simpleChatMemory.getMessages(conversationId))
                        .build())
                .onComplete(full -> {
                    System.out.println(full);
                    simpleChatMemory.addMessage(conversationId, full);
                })
                .onMessage(msg -> {
                    System.out.println(msg);
                })
                .subscribe();


        simpleChatMemory.addMessage(conversationId, UserMessage.builder()
                .content("我喜欢哪里？")
                .build());
        ChatAgent.builder()
                .build()
                .apply(OpenaiChatRequest.builder()
                        .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                        .apiKey(apiKey)
                        .modelName("qwen3-32b")
                        .temperature(0)
                        .conversationId(conversationId)
                        .messages(simpleChatMemory.getMessages(conversationId))
                        .tools(List.of(ToolSpecification.builder()
                                .name("getWeather")
                                .description("获取天气信息")
                                .parameters(JsonNodeFactory.instance
                                        .objectNode()
                                        .put("description", "参数")
                                        .set("properties", JsonNodeFactory.instance.objectNode()
                                                .set("city", JsonNodeFactory.instance.objectNode()
                                                        .put("type", "string")
                                                        .put("description", "城市"))))
                                .build()))
                        .build())
                .onComplete(full -> {
                    System.out.println(full);
                    simpleChatMemory.addMessage(conversationId, full);
                })
                .onMessage(msg -> {
                    System.out.println(msg);
                })
                .subscribe();
    }
}
