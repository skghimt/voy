package cn.wondervoy.facade.impl;

import cn.wondervoy.dao.bean.*;
import cn.wondervoy.facade.IMessageFacade;
import cn.wondervoy.service.wondervoy.*;
import cn.wondervoy.service.wondervoy.impl.IdFactoryServiceImpl;
import cn.wondervoy.utils.IdBuildType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ckzhang
 *
 */
@Service("MessageFacadeImpl")
public class MessageFacadeImpl implements IMessageFacade {

    @Autowired
    private IChatMessageService messageService;

    @Autowired
    private IChatRelationService chatRelationService;

    @Autowired
    private IdFactoryServiceImpl idFactoryService;

    @Autowired
    private IStoryReplyService storyReplyService;

    @Autowired
    private IStoryService storyService;

    @Autowired
    private IStoryCommentService storyCommentService;

    @Override
    public void sendMessage(long userId, long receiverId, String content) {

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

            chatRelation = new ChatRelation();
            chatRelation.setRelationId(relationId);
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


        messageService.insert(chatMessage);
    }

    @Override
    public int reviewCount(long userId) {
        return storyReplyService.reviewCount(userId);
    }

    @Override
    public int messageCount(long userId) {

        return messageService.messageCount(userId);
    }

    @Override
    public List<ChatMessage> queryChats(long relationId, long time, int size) {
        return messageService.queryChats(relationId,time,size);
    }

    @Override
    public void readMessage(long relationId, long userId) {
        messageService.readMessage(relationId, userId);
    }

    @Override
    public Story getStoryByReplyId(long replyId) {
        return storyService.getStoryByReplyId(replyId);
    }

    @Override
    public Story getStoryById(long storyId) {
        return (Story)storyService.findOne("storyId", storyId);
    }

    @Override
    public List<StoryComment> findMyComment(long userId, int index, int size) {

        return storyCommentService.findMyComment(userId, index, size);

    }

    @Override
    public StoryReply getReplyByCommentId(long commentId) {
        return (StoryReply)storyReplyService.findOne("commentId",commentId);
    }

    @Override
    public void readReviews(long userId) {
        storyCommentService.readReviews(userId);
    }

    @Override
    public List<ChatSession> getSessionByPage(long userId, int index, int size) {
        return messageService.getSessionByPage(userId, index, size);
    }

    @Override
    public int getUnReadCount(long userId, long relationId) {
        return messageService.getUnReadCount(userId, relationId);
    }

    @Override
    public ChatMessage getLatestMessage(long relationId) {
        return messageService.getLatestMessage(relationId);
    }

    @Override
    public ChatRelation getChatRelatiobById(long relationId, long userId) {

        Map<String,Object> params = new HashMap<>();
        params.put("userBId", userId);
        params.put("relationId", relationId);

        return (ChatRelation)chatRelationService.queryOne(params);
    }

    @Override
    public ChatRelation getRelationId(long userAId, long userBId) {

        Map<String,Object> params = new HashMap<>();
        params.put("userAId", userAId);
        params.put("userBId", userBId);

        return (ChatRelation)chatRelationService.queryOne(params);
    }
}
