/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: wondervoy
 * $Id:  UserFindPassword.java 2015-05-24 11:35:38 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserFindPassword extends BaseDomain {
    /** 有效截止时间 */
    private Long invalidDate;
    /** 被邀请人的email */
    private String email;
    /** 邀请码 */
    private String code;

	public UserFindPassword(){
	}
    public void setInvalidDate(Long value) {
        this.invalidDate = value;
    }

    public Long getInvalidDate() {
        return this.invalidDate;
    }
    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }
    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateDate",getCreateDate())
			.append("InvalidDate",getInvalidDate())
			.append("Email",getEmail())
			.append("Code",getCode())
			.append("UpdateDate",getUpdateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserFindPassword == false) return false;
		if(this == obj) return true;
		UserFindPassword other = (UserFindPassword)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

