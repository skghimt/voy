/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  ChatMessage.java 2015-03-14 23:03:32 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ChatMessage extends BaseDomain {
    private Long relationId;
    private String content;
    private Integer isRead;
    private Long senderId;
    private Long replyId = 0l;

	public ChatMessage(){
	}

    public void setRelationId(Long value) {
        this.relationId = value;
    }

    public Long getRelationId() {
        return this.relationId;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setIsRead(Integer value) {
        this.isRead = value;
    }

    public Integer getIsRead() {
        return this.isRead;
    }
    public void setSenderId(Long value) {
        this.senderId = value;
    }

    public Long getSenderId() {
        return this.senderId;
    }
    public void setReplyId(Long value) {
        this.replyId = value;
    }

    public Long getReplyId() {
        return this.replyId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("RelationId",getRelationId())
			.append("Content",getContent())
			.append("IsRead",getIsRead())
			.append("SenderId",getSenderId())
			.append("ReplyId",getReplyId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ChatMessage == false) return false;
		if(this == obj) return true;
		ChatMessage other = (ChatMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

