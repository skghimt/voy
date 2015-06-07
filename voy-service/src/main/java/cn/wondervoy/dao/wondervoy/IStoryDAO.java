/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryDAO.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.dao.wondervoy;

import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.Story;
import cn.wondervoy.dao.bean.StoryPopuler;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStoryDAO extends IBaseDAO<Story> {
	

    List<Story> userStory(@Param("userId") long userId, @Param("storyId") long storyId,
                          @Param("type")  int type, @Param("size") int size);

    int userHasStory(@Param("userId") long userId, @Param("storyId") long storyId,@Param("type") int type);

    List<StoryPopuler> storyPopuler(@Param("index")int index, @Param("size")int size);

    int storyPopulerCount();

    int userStoryCount(@Param("userId")long userId);

    Story getStoryByReplyId(@Param("replyId")long replyId);

}
