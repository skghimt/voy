/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  ChatMessageServiceImpl.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.service.wondervoy.impl;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.ChatMessage;
import cn.wondervoy.dao.bean.ChatSession;
import cn.wondervoy.dao.wondervoy.IChatMessageDAO;
import cn.wondervoy.service.AbstractPageService;
import cn.wondervoy.service.wondervoy.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ChatMessageServiceImpl")
public class ChatMessageServiceImpl extends AbstractPageService<IBaseDAO<ChatMessage>, ChatMessage> implements IChatMessageService<IBaseDAO<ChatMessage>,ChatMessage> {
    @Autowired
    private IChatMessageDAO chatMessageDAO;

    @Override
    public IBaseDAO<ChatMessage> getDao() {
        return chatMessageDAO;
    }

    public int messageCount(long userId){
        return chatMessageDAO.messageCount(userId);
    }

    public List<ChatMessage> queryChats(long relationId, long time, int size){
        return chatMessageDAO.queryChats(relationId,time,size);
    }

    @Override
    public void readMessage(long relationId, long userId) {
        chatMessageDAO.readMessage(relationId, userId, System.currentTimeMillis());
    }

    @Override
    public List<ChatSession> getSessionByPage(long userId, int index, int size) {
        return chatMessageDAO.getSessionByPage(userId,index,size);
    }

    @Override
    public int getUnReadCount(long userId, long relationId) {
        return chatMessageDAO.getUnReadCount(userId,relationId);
    }

    @Override
    public ChatMessage getLatestMessage(long relationId) {
        return chatMessageDAO.getLatestMessage(relationId);
    }

    //    @Override
//    public void insert(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void update(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void updateNull(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void deleteByProperty(String property, Object value) {
//
//    }
//
//    @Override
//    public BaseDomain fetch(Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain findOne(@Param("property") String property, @Param("value") Object value) {
//        return null;
//    }
//
//    @Override
//    public List findList(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public void deleteByCondition(Map condition) {
//
//    }
//
//    @Override
//    public void updateMap(@Param("map") Map entityMap) {
//
//    }
//
//    @Override
//    public List<ChatMessage> findAll() {
//        return chatMessageDAO.findAll();
//    }
//
//    @Override
//    public List like(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public Long selectMaxId() {
//        return null;
//    }
//
//    @Override
//    public BaseDomain updateOrSave(BaseDomain baseDomain, Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain selectOne(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public List selectList(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return null;
//    }
//
//    @Override
//    public int count(Map condition) {
//        return 0;
//    }
//
//    @Override
//    public BaseDomain queryOne(Map condition) {
//        return null;
//    }
//
//    @Override
//    public List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
//        return null;
//    }
//
//    @Override
//    public List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
//        return null;
//    }
//
//    @Override
//    protected ChatMessageDAO getDao() {
//        return chatMessageDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
