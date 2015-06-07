/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  ChatMessageService.java 2014-12-17 09:45:17 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.ChatMessage;
import cn.wondervoy.dao.bean.ChatSession;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

import java.util.List;

public interface IChatMessageService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

    int messageCount(long userId);

    List<ChatMessage> queryChats(long relationId, long time, int size);

    void readMessage(long relationId, long userId);

    List<ChatSession> getSessionByPage(long userId, int index, int size);

    int getUnReadCount(long userId, long relationId);

    ChatMessage getLatestMessage(long relationId);

}
