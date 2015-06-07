/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryComment.java 2014-12-17 09:45:17 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StoryComment extends BaseDomain {
    private Long commentId;
    private Long storyId;
    private Long userId;
    private String content;
    private Long replayId;
    private Integer stars;

	public StoryComment(){
	}

    public void setCommentId(Long value) {
        this.commentId = value;
    }

    public Long getCommentId() {
        return this.commentId;
    }
    public void setStoryId(Long value) {
        this.storyId = value;
    }

    public Long getStoryId() {
        return this.storyId;
    }
    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setReplayId(Long value) {
        this.replayId = value;
    }

    public Long getReplayId() {
        return this.replayId;
    }
    public void setStars(Integer value) {
        this.stars = value;
    }

    public Integer getStars() {
        return this.stars;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("CommentId",getCommentId())
			.append("StoryId",getStoryId())
			.append("Userid", getUserId())
			.append("Content",getContent())
			.append("ReplayId",getReplayId())
			.append("Stars",getStars())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof StoryComment == false) return false;
		if(this == obj) return true;
		StoryComment other = (StoryComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

