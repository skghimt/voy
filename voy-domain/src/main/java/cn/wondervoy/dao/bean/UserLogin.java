/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserLogin.java 2014-12-17 09:45:18 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class UserLogin extends BaseDomain{
    private Long invalidDate;
    private Long userId;
    private String token;
    private String source;

	public UserLogin(){
	}
    public void setInvalidDate(Long value) {
        this.invalidDate = value;
    }

    public Long getInvalidDate() {
        return this.invalidDate;
    }
    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setToken(String value) {
        this.token = value;
    }

    public String getToken() {
        return this.token;
    }
    public void setSource(String value) {
        this.source = value;
    }

    public String getSource() {
        return this.source;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id", getId())
			.append("State",getState())
			.append("InvalidDate",getInvalidDate())
			.append("UserId",getUserId())
			.append("Token",getToken())
			.append("Source",getSource())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserLogin == false) return false;
		if(this == obj) return true;
		UserLogin other = (UserLogin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

