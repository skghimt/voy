/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserSkill.java 2014-12-17 09:45:18 $
 */



package cn.wondervoy.dao.bean;

import cn.wondervoy.base.BaseDomain;

public class FunnyV extends BaseDomain {
    private Long userId;
    private Integer number;
    private Integer stars;

	public FunnyV(){
	}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}

