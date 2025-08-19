package org.zenoda.chatant;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.zenoda.chatant.message.ErrorPartialMessage;
import org.zenoda.chatant.message.ResultPartialMessage;
import org.zenoda.chatant.message.ThinkingPartialMessage;
import org.zenoda.chatant.message.ToolCallPartialMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ThinkingPartialMessage.class, name = ThinkingPartialMessage.TYPE),
        @JsonSubTypes.Type(value = ResultPartialMessage.class, name = ResultPartialMessage.TYPE),
        @JsonSubTypes.Type(value = ErrorPartialMessage.class, name = ErrorPartialMessage.TYPE),
        @JsonSubTypes.Type(value = ToolCallPartialMessage.class, name = ToolCallPartialMessage.TYPE)
})
public abstract class PartialMessage {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
