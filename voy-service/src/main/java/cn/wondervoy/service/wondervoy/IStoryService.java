/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  StoryService.java 2014-12-17 09:45:17 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.Story;
import cn.wondervoy.dao.bean.StoryPopuler;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

import java.util.List;

public interface IStoryService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

    List<Story> userStory(long userId, long storyId, int type, int size);

    int userHasStory(long userId, long storyId,int type);

    List<StoryPopuler> storyPopuler(int index, int size);

    int storyPopulerCount();

    int userStoryCount(long userId);

    Story getStoryByReplyId(long replyId);
}

