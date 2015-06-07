package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class Review extends BaseRespones{

    private Comment comment;
    private StoryResponse story;
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public StoryResponse getStory() {
        return story;
    }

    public void setStory(StoryResponse story) {
        this.story = story;
    }
}
