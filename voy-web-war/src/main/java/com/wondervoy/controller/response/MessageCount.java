package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class MessageCount extends BaseRespones{

    private int count;

    private int reviewsCount;

    private int messageCount;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
