/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserComment.java 2014-12-17 09:45:18 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserComment extends BaseDomain {
    private Long commentId;
    private Long userId;
    private Long commentUserId;
    private String content;
    private Long replayId;
    private Integer stars;

	public UserComment(){
	}

    public void setCommentId(Long value) {
        this.commentId = value;
    }

    public Long getCommentId() {
        return this.commentId;
    }
    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setCommentUserId(Long value) {
        this.commentUserId = value;
    }

    public Long getCommentUserId() {
        return this.commentUserId;
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
			.append("UserId",getUserId())
			.append("CommentUserId",getCommentUserId())
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
		if(obj instanceof UserComment == false) return false;
		if(this == obj) return true;
		UserComment other = (UserComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

