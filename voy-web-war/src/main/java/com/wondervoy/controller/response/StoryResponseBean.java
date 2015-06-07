package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-20.
 */
public class StoryResponseBean extends BaseRespones{

    private long userId;

    private String firstName;

    private String lastName;

    private String icon;

    private StoryResponse story;

    private Boolean hasMore;

    private int storyCount;

    private int drafftCount;

    private List<StoryResponse> storys;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public StoryResponse getStory() {
        return story;
    }

    public void setStory(StoryResponse story) {
        this.story = story;
    }

    public List<StoryResponse> getStorys() {
        return storys;
    }

    public void setStorys(List<StoryResponse> storys) {
        this.storys = storys;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getStoryCount() {
        return storyCount;
    }

    public void setStoryCount(int storyCount) {
        this.storyCount = storyCount;
    }

    public int getDrafftCount() {
        return drafftCount;
    }

    public void setDrafftCount(int drafftCount) {
        this.drafftCount = drafftCount;
    }
}
