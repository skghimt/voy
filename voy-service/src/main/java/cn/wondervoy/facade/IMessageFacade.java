package cn.wondervoy.facade;

import cn.wondervoy.dao.bean.*;

import java.util.List;

/**
 * Created by ckzhang on 15-1-15.
 */
public interface IMessageFacade {

    void sendMessage(long userId, long receiverId, String content);

    int reviewCount(long userId);

    int messageCount(long userId);

    List<ChatMessage> queryChats(long relationId, long time, int size);

    void readMessage(long relationId, long userId);

    Story getStoryByReplyId(long replyId);

    Story getStoryById(long storyId);

    List<StoryComment> findMyComment(long userId, int index, int size);

    StoryReply getReplyByCommentId(long commentId);

    void readReviews(long userId);

    List<ChatSession> getSessionByPage(long userId, int index, int size);

    int getUnReadCount(long userId, long relationId);

    ChatMessage getLatestMessage(long relationId);

    ChatRelation getChatRelatiobById(long relationId, long userId);

    ChatRelation getRelationId(long userAId, long userBId);

}
