package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-28.
 */
public class StoryList extends BaseRespones {

    private List<StoryResponse> storys;

    public List<StoryResponse> getStorys() {
        return storys;
    }

    public void setStorys(List<StoryResponse> storys) {
        this.storys = storys;
    }
}
