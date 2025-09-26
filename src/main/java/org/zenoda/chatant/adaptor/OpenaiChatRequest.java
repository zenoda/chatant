package org.zenoda.chatant.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.zenoda.chatant.*;
import org.zenoda.chatant.message.AssistantMessage;
import org.zenoda.chatant.message.SystemMessage;
import org.zenoda.chatant.message.ToolMessage;
import org.zenoda.chatant.message.UserMessage;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

public class OpenaiChatRequest extends ChatRequest {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatRequest.Builder {
        @Override
        public ChatRequest build() {
            OpenaiChatRequest chatRequest = new OpenaiChatRequest();
            _build(chatRequest);
            return chatRequest;
        }
    }

    @Override
    public ChatResponse.Builder responseBuilder() {
        return OpenaiChatResponse.builder();
    }

    private String mapRole(ChatRole role) {
        String roleName;
        switch (role) {
            case TOOL:
                roleName = "tool";
                break;
            case USER:
                roleName = "user";
                break;
            case ASSISTANT:
                roleName = "assistant";
                break;
            case SYSTEM:
                roleName = "system";
                break;
            default:
                roleName = "unknown";
        }
        return roleName;
    }

    private String createRequestBody() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestJsonNode = mapper.createObjectNode()
                .put("model", getModelName())
                .put("stream", getStreaming());
        if (getTemperature() != null) {
            requestJsonNode.put("temperature", getTemperature());
        }
        if (getTopP() != null) {
            requestJsonNode.put("top_p", getTopP());
        }
        if (getSeed() != null) {
            requestJsonNode.put("seed", getSeed());
        }
        if (getMaxTokens() != null) {
            requestJsonNode.put("max_tokens", getMaxTokens());
        }
        if (getTimeout() != null) {
            requestJsonNode.put("timeout", getTimeout());
        }
        ArrayNode messageArrayNode = requestJsonNode.putArray("messages");
        getMessages().forEach(message -> {
            ObjectNode messageJsonNode = mapper.createObjectNode()
                    .put("role", mapRole(message.getRole()));
            if (message.getRole() == ChatRole.USER) {
                UserMessage userMessage = (UserMessage) message;
                if (userMessage.getMediaList() == null || userMessage.getMediaList().isEmpty()) {
                    messageJsonNode.put("content", userMessage.getContent());
                } else {
                    ArrayNode contentArrayNode = messageJsonNode.putArray("content");
                    userMessage.getMediaList().forEach(media -> {
                        ObjectNode mediaContentJsonNode = mapper.createObjectNode();
                        if (media.getType() == MediaType.IMAGE) {
                            mediaContentJsonNode.put("type", "image_url");
                            ObjectNode imageUrlJsonNode = mediaContentJsonNode.putObject("image_url");
                            imageUrlJsonNode.put("url", media.getUrl());
                        } else if (media.getType() == MediaType.AUDIO) {
                            mediaContentJsonNode.put("type", "input_audio");
                            ObjectNode inputAudioJsonNode = mediaContentJsonNode.putObject("input_audio");
                            inputAudioJsonNode.put("data", media.getUrl());
                        } else if (media.getType() == MediaType.VIDEO) {
                            mediaContentJsonNode.put("type", "video_url");
                            ObjectNode videoUrlJsonNode = mediaContentJsonNode.putObject("video_url");
                            videoUrlJsonNode.put("url", media.getUrl());
                        }
                        contentArrayNode.add(mediaContentJsonNode);
                    });
                    ObjectNode textContentJsonNode = mapper.createObjectNode();
                    textContentJsonNode.put("type", "text");
                    textContentJsonNode.put("text", Optional.ofNullable(userMessage.getContent()).orElse(""));
                    contentArrayNode.add(textContentJsonNode);
                }
            } else if (message.getRole() == ChatRole.ASSISTANT) {
                AssistantMessage assistantMessage = (AssistantMessage) message;
                messageJsonNode.put("content", Optional.ofNullable(assistantMessage.getContent()).orElse(""));
                messageJsonNode.put("reasoning_content", Optional.ofNullable(assistantMessage.getReasoningContent()).orElse(""));
                if (assistantMessage.getToolCalls() != null && !assistantMessage.getToolCalls().isEmpty()) {
                    ArrayNode toolCallsNode = messageJsonNode.putArray("tool_calls");
                    assistantMessage.getToolCalls().forEach(toolCall -> {
                        ObjectNode toolCallNode = mapper.createObjectNode();
                        toolCallsNode.add(toolCallNode);
                        toolCallNode.put("id", toolCall.getId())
                                .put("index", toolCall.getIndex())
                                .put("type", "function");
                        ObjectNode functionNode = toolCallNode.putObject("function");
                        functionNode.put("name", toolCall.getFunction())
                                .put("arguments", toolCall.getArguments());
                    });
                }
            } else if (message.getRole() == ChatRole.SYSTEM) {
                SystemMessage systemMessage = (SystemMessage) message;
                messageJsonNode.put("content", systemMessage.getContent());
            } else if (message.getRole() == ChatRole.TOOL) {
                ToolMessage toolMessage = (ToolMessage) message;
                messageJsonNode.put("content", Optional.ofNullable(toolMessage.getContent()).orElse(""))
                        .put("tool_call_id", toolMessage.getToolCallId());
            }
            messageArrayNode.add(messageJsonNode);
        });
        if (getTools() != null && !getTools().isEmpty()) {
            ArrayNode toolArrayNode = requestJsonNode.putArray("tools");
            getTools().forEach(tool -> {
                ObjectNode toolNode = mapper.createObjectNode();
                toolNode.put("type", "function");
                ObjectNode functionNode = mapper.createObjectNode();
                functionNode.put("name", tool.getName());
                functionNode.put("description", tool.getDescription());
                functionNode.put("parameters", tool.getParameters());
                toolNode.put("function", functionNode);
                toolArrayNode.add(toolNode);
            });
        }
        return requestJsonNode.toString();
    }

    @Override
    public HttpRequest buildHttpRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + "/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getApiKey())
                .POST(HttpRequest.BodyPublishers.ofString(createRequestBody()))
                .build();
    }
}
