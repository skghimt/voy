/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  UserInvite.java 2015-03-14 23:03:31 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserInvite extends BaseDomain {
    private Long invalidDate;
    private Long userId;
    private String email;
    private Long inviteId;
    private String code;

    public UserInvite() {
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

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setInviteId(Long value) {
        this.inviteId = value;
    }

    public Long getInviteId() {
        return this.inviteId;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("CreateDate", getCreateDate())
                .append("InvalidDate", getInvalidDate())
                .append("UserId", getUserId())
                .append("Email", getEmail())
                .append("InviteId", getInviteId())
                .append("Code", getCode())
                .append("UpdateDate", getUpdateDate())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof UserInvite == false) return false;
        if (this == obj) return true;
        UserInvite other = (UserInvite) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

