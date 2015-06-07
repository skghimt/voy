package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class StoryResponse extends BaseRespones{

    private long time = System.currentTimeMillis();
    private Long storyId;
    private String coverUrl;
    private String coverDes;
    private List<PicStory> picStories;
    private List<Comment> comments;

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverDes() {
        return coverDes;
    }

    public void setCoverDes(String coverDes) {
        this.coverDes = coverDes;
    }

    public List<PicStory> getPicStories() {
        return picStories;
    }

    public void setPicStories(List<PicStory> picStories) {
        this.picStories = picStories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
