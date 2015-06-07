package cn.wondervoy.facade.impl;

import cn.wondervoy.dao.bean.*;
import cn.wondervoy.facade.IStoryFacade;
import cn.wondervoy.service.wondervoy.*;
import cn.wondervoy.service.wondervoy.impl.IdFactoryServiceImpl;
import cn.wondervoy.utils.IdBuildType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ckzhang on 14-12-22.
 */
@Service("StoryFacadeImpl")
public class StoryFacadeImpl implements IStoryFacade{

    @Autowired
    private IStoryService storyService;

    @Autowired
    private IdFactoryServiceImpl idFactoryService;

    @Autowired
    private IStoryCommentService storyCommentService;

    @Autowired
    private IStoryReplyService storyReplyService;

    @Override
    public long publish(long userId, String coverPic, String coverDes, String storyContext, int type) {


        long storyId = idFactoryService.getIdByBusinessCode(IdBuildType.STORYID, idFactoryService.getCountAsInt());

        Story story = new Story();
        story.setCoverDes(coverDes);
        story.setCoverUrl(coverPic);
        story.setSenderId(userId);
        story.setStoryId(storyId);
        story.setStory(storyContext);
        story.setType(type);

        storyService.insert(story);

        return storyId;
    }

    @Override
    public List<Story> userStory(long userId, long storyId, int type, int size) {

        if (storyId < 1){
            storyId = System.currentTimeMillis();
        }

        return storyService.userStory(userId, storyId, type, size);
    }

    @Override
    public boolean userHasStory(long userId, long storyId, int type) {

        int count = storyService.userHasStory(userId,storyId, type);

        return count > 0;
    }

    @Override
    public Story story(long storyId) {
        return (Story)storyService.findOne("storyId", storyId);
    }

    @Override
    public long comment(long userId, long storyId, String comment, int stars) {

        long commentId = idFactoryService.getIdByBusinessCode(IdBuildType.STORY_COMMENTID, idFactoryService.getCountAsInt());

        StoryComment storyComment = new StoryComment();
        storyComment.setStoryId(storyId);
        storyComment.setCommentId(commentId);
        storyComment.setContent(comment);
        storyComment.setReplayId(0l);
        storyComment.setStars(stars);
        storyComment.setUserId(userId);

        storyCommentService.insert(storyComment);

        return commentId;
    }

    @Override
    public long reply(long userId, long commentId, String comment) {

        long replyId = idFactoryService.getIdByBusinessCode(IdBuildType.STORY_REPLY, idFactoryService.getCountAsInt());

        StoryReply reply = new StoryReply();
        reply.setUserId(userId);
        reply.setCommentId(commentId);
        reply.setContent(comment);
        reply.setReplyId(replyId);

        storyReplyService.insert(reply);

        StoryComment storyComment = (StoryComment)storyCommentService.findOne("commentId", commentId);

        try {
            // 由评论产生的会话
            replyMessage(userId, storyComment.getUserId(), comment ,replyId);
        }catch (Exception e){
            e.printStackTrace();
        }


        return replyId;
    }

    @Override
    public List<StoryComment> getComments(long storyId, long commentId, int size){
        return storyCommentService.getComments(storyId, commentId, size);
    }

    @Override
    public List<Long> getMainPopuler(int index, int size) {

        List<StoryPopuler> storyPopulers = storyService.storyPopuler(index, size);
        List<Long> populers = new ArrayList<>();
        for (StoryPopuler populer : storyPopulers){
            populers.add(populer.getUserId());
        }

        return populers;
    }

    @Override
    public boolean hasMore(int index) {

        int maxCount = storyService.storyPopulerCount();

        return index < maxCount;
    }

    @Override
    public int userStoryCount(long userId){
        return storyService.userStoryCount(userId);
    }


    @Autowired
    private IChatMessageService messageService;

    @Autowired
    private IChatRelationService chatRelationService;

    /**
     * 评论产生的聊天会话
     *
     * @param userId
     * @param receiverId
     * @param content
     * @param replyId
     */
    private void replyMessage(long userId, long receiverId, String content, long replyId) {

        ChatMessage chatMessage = new ChatMessage();

        Map<String,Object> params = new HashMap<>();
        params.put("userAId", userId);
        params.put("userBId", receiverId);
        ChatRelation chatRelation = (ChatRelation)chatRelationService.queryOne(params);

        long relationId = 0;
        if (chatRelation == null){
            relationId = idFactoryService.getIdByBusinessCode(IdBuildType.USER_RELATIONID,idFactoryService.getCountAsInt());

            chatRelation = new ChatRelation();
            chatRelation.setUserAId(userId);
            chatRelation.setRelationId(relationId);
            chatRelation.setUserBId(receiverId);
            chatRelationService.insert(chatRelation);

            chatRelation.setUserBId(userId);
            chatRelation.setUserAId(receiverId);
            chatRelationService.insert(chatRelation);

        } else {
            relationId = chatRelation.getRelationId();
        }

        chatMessage.setSenderId(userId);
        chatMessage.setRelationId(relationId);
        chatMessage.setContent(content);
        chatMessage.setIsRead(0);
        chatMessage.setReplyId(replyId);


        messageService.insert(chatMessage);
    }

    @Override
    public StoryReply getReplyByCommentId(long commentId) {
        return (StoryReply)storyReplyService.findOne("commentId",commentId);
    }

    @Override
    public int getStoryCountByType(long userId, int type) {

        Map<String, Object> params = new HashMap<>();
        params.put("type",type);
        params.put("senderId", userId);
        return storyService.count(params);
    }

    @Override
    public int myCommentStars(long userId, long storyId) {
        return storyCommentService.myCommentStars(userId, storyId);
    }
}
