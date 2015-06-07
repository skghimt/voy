/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryCommentService.java 2014-12-17 09:45:17 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.StoryComment;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

import java.util.List;

public interface IStoryCommentService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

    List<StoryComment> getComments(long storyId, long commentId, int size);

    List<StoryComment> findMyComment(long userId, int index, int size);

    void readReviews(long userId);

    int myCommentStars(long userId, long storyId);
}
