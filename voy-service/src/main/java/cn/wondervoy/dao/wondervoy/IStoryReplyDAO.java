/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  StoryReplyDAO.java 2015-03-14 23:18:45 $
 */
package cn.wondervoy.dao.wondervoy;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.StoryReply;
import org.apache.ibatis.annotations.Param;

public interface IStoryReplyDAO extends IBaseDAO<StoryReply> {

    int reviewCount(@Param("userId")long userId);
}
