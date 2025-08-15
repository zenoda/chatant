package org.zenoda.chatant.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.zenoda.chatant.ChatResponse;
import org.zenoda.chatant.ToolCallSpecification;
import org.zenoda.chatant.message.AssistantMessage;
import org.zenoda.chatant.message.ResultPartialMessage;
import org.zenoda.chatant.message.ThinkingPartialMessage;
import org.zenoda.chatant.message.ToolCallPartialMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

public class OpenaiChatResponse extends ChatResponse {
    private ObjectMapper objectMapper = new ObjectMapper();

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatResponse.Builder {
        @Override
        public OpenaiChatResponse build() {
            OpenaiChatResponse response = new OpenaiChatResponse();
            _build(response);
            return response;
        }
    }

    @Override
    public void subscribe() {
        if (getError() != null) {
            getErrorConsumer().accept(new Error(getError().getMessage(), getError()));
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(getStream(), StandardCharsets.UTF_8))) {
                AssistantMessage assistantMessage = AssistantMessage.builder().build();
                reader.lines().forEach(line -> {
                    line = line.trim();
                    if (line.isEmpty()) {
                        return;
                    }
                    try {
                        if (line.startsWith("data:")) {
                            line = line.substring("data:".length()).trim();
                            if (line.equals("[DONE]")) {
                                getCompleteConsumer().accept(assistantMessage);
                            } else {
                                ObjectNode data = objectMapper.readValue(line, ObjectNode.class);
                                ArrayNode choices = (ArrayNode) data.get("choices");
                                if (choices != null && !choices.isEmpty()) {
                                    ObjectNode choice0 = (ObjectNode) choices.get(0);
                                    ObjectNode delta = (ObjectNode) choice0.get("delta");
                                    String thinkingText = Optional.ofNullable(delta.get("reasoning_content")).orElse(NullNode.getInstance()).asText("").trim();
                                    String resultText = Optional.ofNullable(delta.get("content")).orElse(NullNode.getInstance()).asText("").trim();
                                    ArrayNode toolCalls = (ArrayNode) delta.get("tool_calls");
                                    if (!thinkingText.isEmpty()) {
                                        assistantMessage.setReasoningContent(Optional.ofNullable(assistantMessage.getReasoningContent()).orElse("") + thinkingText);
                                        getMessageConsumer().accept(ThinkingPartialMessage.builder().text(thinkingText).build());
                                    } else if (!resultText.isEmpty()) {
                                        assistantMessage.setContent(Optional.ofNullable(assistantMessage.getContent()).orElse("") + resultText);
                                        getMessageConsumer().accept(ResultPartialMessage.builder().text(resultText).build());
                                    } else if (toolCalls != null && !toolCalls.isEmpty()) {
                                        toolCalls.valueStream().map(toolCall -> {
                                            int index = toolCall.get("index").asInt();
                                            if (assistantMessage.getToolCalls() == null) {
                                                assistantMessage.setToolCalls(new ArrayList<>());
                                            }
                                            ToolCallSpecification toolCallSpecification = assistantMessage.getToolCalls()
                                                    .stream()
                                                    .filter(tcs -> tcs.getIndex() == index)
                                                    .findFirst()
                                                    .orElse(null);
                                            String partialArguments = Optional.ofNullable(toolCall.get("function").get("arguments")).orElse(NullNode.getInstance()).asText(null);
                                            if (toolCallSpecification == null) {
                                                toolCallSpecification = ToolCallSpecification.builder()
                                                        .index(index)
                                                        .id(toolCall.get("id").asText())
                                                        .function(toolCall.get("function").get("name").asText())
                                                        .arguments(partialArguments)
                                                        .build();
                                                assistantMessage.getToolCalls().add(toolCallSpecification);
                                            } else if (partialArguments != null) {
                                                toolCallSpecification.setArguments(toolCallSpecification.getArguments() + partialArguments);
                                            }
                                            return ToolCallPartialMessage
                                                    .builder()
                                                    .index(toolCall.get("index").asInt())
                                                    .id(toolCallSpecification.getId())
                                                    .name(toolCallSpecification.getFunction())
                                                    .partialArguments(partialArguments)
                                                    .build();
                                        }).forEach(getMessageConsumer());
                                    }
                                }
                            }
                        } else {
                            getErrorConsumer().accept(new Error("Data format is abnormal: " + line));
                        }
                    } catch (Exception e) {
                        getErrorConsumer().accept(new Error(e.getMessage(), e));
                    }
                });
            } catch (Exception e) {
                getErrorConsumer().accept(new Error(e.getMessage(), e));
            }
        }
    }
}
