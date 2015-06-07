/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  StoryReply.java 2015-03-14 23:03:33 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StoryReply extends BaseDomain {
    private Long commentId;
    private Long replyId;
    private Long userId;
    private String content;

	public StoryReply(){
	}

    public void setCommentId(Long value) {
        this.commentId = value;
    }

    public Long getCommentId() {
        return this.commentId;
    }
    public void setReplyId(Long value) {
        this.replyId = value;
    }

    public Long getReplyId() {
        return this.replyId;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("CommentId",getCommentId())
			.append("ReplyId",getReplyId())
			.append("Userid", getUserId())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof StoryReply == false) return false;
		if(this == obj) return true;
		StoryReply other = (StoryReply)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

