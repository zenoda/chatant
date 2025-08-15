package org.zenoda.chatant;

public class ToolCallSpecification {
    private String id;
    private int index;
    private String function;
    private String arguments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "ToolCallSpecification[id=" + id + ", index=" + index + ", function=" + function + ", arguments=" + arguments + "]";
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String id;
        private int index;
        private String function;
        private String arguments;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder function(String function) {
            this.function = function;
            return this;
        }

        public Builder arguments(String arguments) {
            this.arguments = arguments;
            return this;
        }

        public ToolCallSpecification build() {
            ToolCallSpecification toolCallSpecification = new ToolCallSpecification();
            toolCallSpecification.setId(id);
            toolCallSpecification.setIndex(index);
            toolCallSpecification.setFunction(function);
            toolCallSpecification.setArguments(arguments);
            return toolCallSpecification;
        }
    }
}
