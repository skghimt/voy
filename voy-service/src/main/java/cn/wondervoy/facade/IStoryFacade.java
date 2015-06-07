package cn.wondervoy.facade;

import cn.wondervoy.dao.bean.Story;
import cn.wondervoy.dao.bean.StoryComment;
import cn.wondervoy.dao.bean.StoryReply;

import java.util.List;

/**
 * Created by ckzhang on 14-12-21.
 */
public interface IStoryFacade {

    long publish(long userId, String coverPic, String coverDes, String story, int type);

    List<Story> userStory(long userId, long storyId,int type, int size);

    boolean userHasStory(long userId, long storyId, int type);

    Story story(long storyId);

    long comment(long userId, long storyId, String comment, int stars);

    long reply(long userId, long commentId, String comment);

    List<StoryComment> getComments(long storyId, long commentId, int size);

    List<Long> getMainPopuler(int index, int size);

    boolean hasMore(int index);

    int userStoryCount(long userId);

    StoryReply getReplyByCommentId(long commentId);

    int getStoryCountByType(long userId, int type);

    int myCommentStars(long userId, long storyId);

}
