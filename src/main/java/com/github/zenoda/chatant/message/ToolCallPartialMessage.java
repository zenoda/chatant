package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.ChatResponse;
import com.github.zenoda.chatant.PartialMessage;

public class ToolCallPartialMessage extends PartialMessage {
    public static final String TYPE = "tool_call";
    private int index;
    private String id;
    private String name;
    private String partialArguments;

    public ToolCallPartialMessage() {
        this.setType(TYPE);
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartialArguments() {
        return partialArguments;
    }

    public void setPartialArguments(String partialArguments) {
        this.partialArguments = partialArguments;
    }

    public static class Builder {
        private int index;
        private String id;
        private String name;
        private String partialArguments;

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder partialArguments(String partialArguments) {
            this.partialArguments = partialArguments;
            return this;
        }

        public ToolCallPartialMessage build() {
            ToolCallPartialMessage message = new ToolCallPartialMessage();
            message.setIndex(index);
            message.setId(id);
            message.setName(name);
            message.setPartialArguments(partialArguments);
            return message;
        }
    }

    @Override
    public String toString() {
        return "ToolCallPartialMessage [type=" + getType() +
                ", index=" +
                index +
                ", id=" +
                id +
                ", name=" +
                name +
                ", partialArguments=" +
                partialArguments +
                "]";
    }
}
