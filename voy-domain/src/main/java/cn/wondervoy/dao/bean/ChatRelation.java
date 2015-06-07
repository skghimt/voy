/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  ChatRelation.java 2014-12-17 09:45:17 $
 */



package cn.wondervoy.dao.bean;


import cn.wondervoy.base.BaseDomain;

public class ChatRelation extends BaseDomain {

    private Long relationId;
    private Long userAId;
    private Long userBId;

	public ChatRelation(){
	}

    public void setRelationId(Long value) {
        this.relationId = value;
    }

    public Long getRelationId() {
        return this.relationId;
    }
    public void setUserAId(Long value) {
        this.userAId = value;
    }

    public Long getUserAId() {
        return this.userAId;
    }
    public void setUserBId(Long value) {
        this.userBId = value;
    }

    public Long getUserBId() {
        return this.userBId;
    }


}

