/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  ChatMessageDAO.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.dao.wondervoy;

import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.ChatMessage;
import cn.wondervoy.dao.bean.ChatSession;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IChatMessageDAO extends IBaseDAO<ChatMessage> {

    int messageCount(@Param("userId")long userId);

    List<ChatMessage> queryChats(@Param("relationId")long relationId, @Param("time")long time, @Param("size")int size);

    int readMessage(@Param("relationId")long relationId, @Param("userId")long userId, @Param("time")long time);

    List<ChatSession> getSessionByPage(@Param("userId")long userId, @Param("index")int index, @Param("size")int size);

    int getUnReadCount(@Param("userId")long userId, @Param("relationId")long relationId);

    ChatMessage getLatestMessage(@Param("relationId")long relationId);

}
