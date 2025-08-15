package org.zenoda.chatant;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ToolSpecification {
    private String name;
    private String description;
    private ObjectNode parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectNode getParameters() {
        return parameters;
    }

    public void setParameters(ObjectNode parameters) {
        this.parameters = parameters;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private ObjectNode parameters;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder parameters(ObjectNode parameters) {
            this.parameters = parameters;
            return this;
        }

        public ToolSpecification build() {
            ToolSpecification toolSpecification = new ToolSpecification();
            toolSpecification.setName(name);
            toolSpecification.setDescription(description);
            toolSpecification.setParameters(parameters);
            return toolSpecification;
        }
    }
}
