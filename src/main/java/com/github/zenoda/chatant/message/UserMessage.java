package com.github.zenoda.chatant.message;

import com.github.zenoda.chatant.ChatMessage;
import com.github.zenoda.chatant.ChatRole;
import com.github.zenoda.chatant.Media;

import java.util.List;

public class UserMessage extends ChatMessage {
    private List<Media> mediaList;

    public UserMessage() {
        this.setRole(ChatRole.USER);
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ChatMessage.Builder {
        private List<Media> mediaList;

        public Builder mediaList(List<Media> mediaList) {
            this.mediaList = mediaList;
            return this;
        }

        @Override

        public ChatMessage build() {
            UserMessage userMessage = new UserMessage();
            _build(userMessage);
            userMessage.setMediaList(mediaList);
            return userMessage;
        }
    }

}
