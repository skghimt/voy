/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserSkill.java 2014-12-17 09:45:18 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserSkill extends BaseDomain {
    private Long userId;
    private String job;
    private String language;
    private String address;
    private String car;
    private Integer driverAge;
    private String interest;

	public UserSkill(){
	}

    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
    }
    public void setJob(String value) {
        this.job = value;
    }

    public String getJob() {
        return this.job;
    }
    public void setLanguage(String value) {
        this.language = value;
    }

    public String getLanguage() {
        return this.language;
    }
    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }
    public void setCar(String value) {
        this.car = value;
    }

    public String getCar() {
        return this.car;
    }
    public void setDriverAge(Integer value) {
        this.driverAge = value;
    }

    public Integer getDriverAge() {
        return this.driverAge;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("State",getState())
			.append("CreateDate",getCreateDate())
			.append("UpdateDate",getUpdateDate())
			.append("UserId",getUserId())
			.append("Job",getJob())
			.append("Language",getLanguage())
			.append("Address",getAddress())
			.append("Car",getCar())
			.append("DriverAge",getDriverAge())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserSkill == false) return false;
		if(this == obj) return true;
		UserSkill other = (UserSkill)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}

