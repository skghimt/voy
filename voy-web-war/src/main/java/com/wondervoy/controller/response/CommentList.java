package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-28.
 */
public class CommentList extends BaseRespones {

    private List<Comment> comments;

    private boolean hasMore;

    private int stars;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
