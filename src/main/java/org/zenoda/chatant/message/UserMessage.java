package org.zenoda.chatant.message;

import org.zenoda.chatant.ChatMessage;
import org.zenoda.chatant.ChatRole;
import org.zenoda.chatant.Media;

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

        @Override
        public Builder content(String content) {
            super.content(content);
            return this;
        }

        public Builder mediaList(List<Media> mediaList) {
            this.mediaList = mediaList;
            return this;
        }

        @Override
        public UserMessage build() {
            UserMessage userMessage = new UserMessage();
            _build(userMessage);
            userMessage.setMediaList(mediaList);
            return userMessage;
        }
    }

}
