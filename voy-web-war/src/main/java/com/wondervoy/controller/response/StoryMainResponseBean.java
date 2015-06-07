package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-20.
 */
public class StoryMainResponseBean extends BaseRespones{

    private List<StoryResponseBean> populers;

    private Boolean hasMore;

    public List<StoryResponseBean> getPopulers() {
        return populers;
    }

    public void setPopulers(List<StoryResponseBean> populers) {
        this.populers = populers;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
