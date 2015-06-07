package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class ChatSession extends BaseRespones{

    private int unReadCount;

    private Chat latest;

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public Chat getLatest() {
        return latest;
    }

    public void setLatest(Chat latest) {
        this.latest = latest;
    }
}
