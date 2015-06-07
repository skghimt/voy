package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class Chat extends BaseRespones{

    private UserInfo sender;

    private UserInfo receiver;

    private long chatTime;

    private long sessionId;

    private String content;

    private StoryResponse story;

    private int isReplay;

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public UserInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfo receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StoryResponse getStory() {
        return story;
    }

    public void setStory(StoryResponse story) {
        this.story = story;
    }

    public int getIsReplay() {
        return isReplay;
    }

    public void setIsReplay(int isReplay) {
        this.isReplay = isReplay;
    }

    public long getChatTime() {
        return chatTime;
    }

    public void setChatTime(long chatTime) {
        this.chatTime = chatTime;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
