/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  Story.java 2014-12-17 09:45:17 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Story extends BaseDomain {
    private Long storyId;
    private Long senderId;
    private String coverUrl;
    private String coverDes;
    private String story;
    private Integer checkStatus = 0;
    private int type;

	public Story(){
	}

    public void setStoryId(Long value) {
        this.storyId = value;
    }

    public Long getStoryId() {
        return this.storyId;
    }
    public void setSenderId(Long value) {
        this.senderId = value;
    }

    public Long getSenderId() {
        return this.senderId;
    }
    public void setCoverUrl(String value) {
        this.coverUrl = value;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }
    public void setCoverDes(String value) {
        this.coverDes = value;
    }

    public String getCoverDes() {
        return this.coverDes;
    }
    public void setStory(String value) {
        this.story = value;
    }

    public String getStory() {
        return this.story;
    }
    public void setCheckStatus(Integer value) {
        this.checkStatus = value;
    }

    public Integer getCheckStatus() {
        return this.checkStatus;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("StoryId",getStoryId())
			.append("SenderId",getSenderId())
			.append("CoverUrl",getCoverUrl())
			.append("CoverDes",getCoverDes())
			.append("Story",getStory())
			.append("CheckStatus",getCheckStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Story == false) return false;
		if(this == obj) return true;
		Story other = (Story)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

