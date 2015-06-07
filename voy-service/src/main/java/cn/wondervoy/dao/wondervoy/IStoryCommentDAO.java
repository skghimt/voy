/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryCommentDAO.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.dao.wondervoy;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.StoryComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStoryCommentDAO extends IBaseDAO<StoryComment> {

    List<StoryComment> getComments(@Param("storyId") long storyId, @Param("commentId") long commentId,
                                 @Param("size") int size);


    List<StoryComment> findMyComment(@Param("userId")long userId, @Param("index")int index, @Param("size")int size);

    int readReviews(@Param("userId")long userId, @Param("time") long time);

    int myCommentStars(@Param("userId")long userId , @Param("storyId") long storyId);

}
