package org.zenoda.chatant;

public enum ChatRole {
    SYSTEM,
    USER,
    ASSISTANT,
    TOOL;

    String value() {
        return name().toLowerCase();
    }
}
