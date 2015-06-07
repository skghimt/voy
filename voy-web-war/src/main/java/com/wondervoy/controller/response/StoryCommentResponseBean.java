package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-20.
 */
public class StoryCommentResponseBean extends BaseRespones{

    private Long commentId;

    private Long replyId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }
}
