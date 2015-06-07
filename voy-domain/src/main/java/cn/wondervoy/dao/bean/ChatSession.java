/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  ChatMessage.java 2015-03-14 23:03:32 $
 */



package cn.wondervoy.dao.bean;

import java.io.Serializable;

public class ChatSession implements Serializable{
    private Long relationId;
    private Long time;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}

