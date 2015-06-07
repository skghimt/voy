/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserVerification.java 2014-12-17 09:45:18 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserVerification extends BaseDomain {
    private Long userId;
    private String verificationTerrace;
    private String account;
    private String passwd;
    private String verificationInfo;
    private long verificationTime;

    public UserVerification(){
	}
    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setVerificationTerrace(String value) {
        this.verificationTerrace = value;
    }

    public String getVerificationTerrace() {
        return this.verificationTerrace;
    }
    public void setAccount(String value) {
        this.account = value;
    }

    public String getAccount() {
        return this.account;
    }
    public void setPasswd(String value) {
        this.passwd = value;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getVerificationInfo() {
        return verificationInfo;
    }

    public void setVerificationInfo(String verificationInfo) {
        this.verificationInfo = verificationInfo;
    }

    public long getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(long verificationTime) {
        this.verificationTime = verificationTime;
    }

    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("UserId",getUserId())
			.append("VerificationTerrace",getVerificationTerrace())
			.append("Account",getAccount())
			.append("Passwd",getPasswd())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserVerification == false) return false;
		if(this == obj) return true;
		UserVerification other = (UserVerification)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

