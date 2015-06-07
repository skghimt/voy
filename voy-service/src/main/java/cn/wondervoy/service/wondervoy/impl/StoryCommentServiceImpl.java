/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryCommentServiceImpl.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.service.wondervoy.impl;

import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.StoryComment;
import cn.wondervoy.dao.wondervoy.IStoryCommentDAO;
import cn.wondervoy.service.AbstractPageService;
import cn.wondervoy.service.wondervoy.IStoryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("StoryCommentServiceImpl")
public class StoryCommentServiceImpl extends AbstractPageService<IBaseDAO<StoryComment>, StoryComment> implements IStoryCommentService<IBaseDAO<StoryComment>,StoryComment> {
    @Autowired
    private IStoryCommentDAO storyCommentDAO;

    @Override
    public IBaseDAO<StoryComment> getDao() {
        return storyCommentDAO;
    }

    @Override
    public List<StoryComment> getComments(long storyId, long commentId, int size) {
        return storyCommentDAO.getComments(storyId, commentId, size);
    }

    @Override
    public List<StoryComment> findMyComment(long userId, int index, int size) {
        return storyCommentDAO.findMyComment(userId, index, size);
    }

    @Override
    public void readReviews(long userId) {
        storyCommentDAO.readReviews(userId, System.currentTimeMillis());
    }

    @Override
    public int myCommentStars(long userId, long storyId) {

        Integer count = storyCommentDAO.myCommentStars(userId, storyId);
        return count == null ? 0 : count;
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
//    public List<StoryComment> findAll() {
//        return storyCommentDAO.findAll();
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
//    protected StoryCommentDAO getDao() {
//        return storyCommentDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
