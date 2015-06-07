package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class ChatSessions extends BaseRespones{

    private List<ChatSession> sessions;

    private int reviewsCount;

    private int messageCount;

    public List<ChatSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<ChatSession> sessions) {
        this.sessions = sessions;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
}
