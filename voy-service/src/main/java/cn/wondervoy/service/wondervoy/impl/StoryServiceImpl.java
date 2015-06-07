/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryServiceImpl.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.service.wondervoy.impl;

import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.Story;
import cn.wondervoy.dao.bean.StoryPopuler;
import cn.wondervoy.dao.wondervoy.IStoryDAO;
import cn.wondervoy.service.AbstractPageService;
import cn.wondervoy.service.wondervoy.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("StoryServiceImpl")
public class StoryServiceImpl extends AbstractPageService<IBaseDAO<Story>, Story> implements IStoryService<IBaseDAO<Story>,Story> {
    @Autowired
    private IStoryDAO storyDAO;

    @Override
    public IBaseDAO<Story> getDao() {
        return storyDAO;
    }

    @Override
    public List<Story> userStory(long userId, long storyId, int type, int size) {
        return storyDAO.userStory(userId, storyId, type, size);
    }

    @Override
    public int userHasStory(long userId, long storyId, int type) {
        return storyDAO.userHasStory(userId, storyId,type);
    }

    @Override
    public List<StoryPopuler> storyPopuler(int index, int size) {
        return storyDAO.storyPopuler(index, size);
    }

    @Override
    public int storyPopulerCount() {
        return storyDAO.storyPopulerCount();
    }

    public int userStoryCount(long userId){
        return storyDAO.userStoryCount(userId);
    }

    @Override
    public Story getStoryByReplyId(long replyId) {
        return storyDAO.getStoryByReplyId(replyId);
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
//    public List<Story> findAll() {
//        return storyDAO.findAll();
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
//    protected StoryDAO getDao() {
//        return storyDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
