package com.wondervoy.controller.business;

import cn.wondervoy.dao.bean.*;
import cn.wondervoy.facade.IMessageFacade;
import cn.wondervoy.facade.IUserFacade;
import com.wondervoy.controller.response.*;
import com.wondervoy.controller.response.ChatSession;
import com.wondervoy.controller.response.UserInfo;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/message")
public class MessageController {

//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserFacade userFacade;

    @Autowired
    private IMessageFacade messageFacade;

    /**
     * 获取未读消息个数
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean count(@RequestHeader String voySession) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try{
            MessageCount count = new MessageCount();

            count.setReviewsCount(messageFacade.reviewCount(userId));
            count.setMessageCount(messageFacade.messageCount(userId));
            count.setCount(count.getReviewsCount() + count.getMessageCount());
            response.setData(count);
        }catch (Exception e){
            response = new ResponseBean(StateEnum.MESSAGE_SEND_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 获取未读消息数详情（带消息列表）
     * type = 1 表示获取 获取聊天消息列表
     * type = 2 表示获取reviews列表
     * @return
     */
    @RequestMapping(value = "/countDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean countDetail(@RequestHeader String voySession,@RequestParam String type, @RequestParam String page, @RequestParam String size) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        int pageVal = Integer.parseInt(page);
        int sizeVal = Integer.parseInt(size);

        cn.wondervoy.dao.bean.UserInfo selfInfo = userFacade.getUserInfo(userId);

        try{

            if("1".equals(type)){
                ChatSessions sessions = new ChatSessions();
                List<ChatSession> chatSessions = new ArrayList<>();

                List<cn.wondervoy.dao.bean.ChatSession> msessions = messageFacade.getSessionByPage(userId, (pageVal - 1) * sizeVal, sizeVal);

                for (cn.wondervoy.dao.bean.ChatSession sItem : msessions){
                    ChatSession item = new ChatSession();
                    item.setUnReadCount(messageFacade.getUnReadCount(userId, sItem.getRelationId()));

                    Chat latestChat = new Chat();

                    ChatMessage latestMessage = messageFacade.getLatestMessage(sItem.getRelationId());

                    if (latestMessage == null ){
                        continue;
                    }

                    latestChat.setChatTime(latestMessage.getCreateDate());

                    latestChat.setIsReplay(latestMessage.getReplyId() == 0 ? 0 : 1 );
                    latestChat.setContent(latestMessage.getContent());

                    if (latestMessage.getReplyId() != null && latestMessage.getReplyId() > 0){
                        StoryResponse story = new StoryResponse();

                        Story s = messageFacade.getStoryByReplyId(latestMessage.getReplyId());

                        story.setTime(s.getCreateDate());
                        story.setStoryId(s.getStoryId());
                        story.setCoverDes(s.getCoverDes());
                        latestChat.setStory(story);
                    }


                    ChatRelation relation = messageFacade.getChatRelatiobById(sItem.getRelationId(), userId);
                    latestChat.setSessionId(relation.getUserAId());

                    cn.wondervoy.dao.bean.UserInfo senderInfo = userFacade.getUserInfo(latestMessage.getSenderId());
                    UserInfo sender = new UserInfo();
                    sender.setLastName(senderInfo.getLastName());
                    sender.setFirstName(senderInfo.getFirstName());
                    sender.setIcon(senderInfo.getIcon());

                    latestChat.setSender(sender);
                    item.setLatest(latestChat);

                    chatSessions.add(item);
                }
                sessions.setSessions(chatSessions);
                sessions.setReviewsCount(messageFacade.reviewCount(userId));
                sessions.setMessageCount(messageFacade.messageCount(userId));
                response.setData(sessions);
            } else if ("2".equals(type)){
                ReviewsResponse reviews = new ReviewsResponse();
                List<Review> list = new ArrayList<>();

                List<StoryComment> comments = messageFacade.findMyComment(userId, (pageVal - 1) * sizeVal, sizeVal);

                Map<Long, StoryResponse> storyMap = new HashMap<>();

                for (StoryComment item : comments){
                    Review review = new Review();

                    if (storyMap.get(item.getStoryId()) == null) {
                        Story daoStory = messageFacade.getStoryById(item.getStoryId());
                        StoryResponse story = new StoryResponse();

                        story.setTime(daoStory.getCreateDate());
                        story.setStoryId(daoStory.getStoryId());
                        story.setCoverDes(daoStory.getCoverDes());
                        review.setStory(story);

                        storyMap.put(item.getStoryId(), story);
                    } else {
                        review.setStory(storyMap.get(item.getStoryId()));
                    }

                    Comment comment = new Comment();
                    comment.setUserId(item.getUserId());

                    cn.wondervoy.dao.bean.UserInfo userInfo = userFacade.getUserInfo(item.getUserId());

                    comment.setLastName(userInfo.getLastName());
                    comment.setFirstName(userInfo.getFirstName());
                    comment.setIcon(userInfo.getIcon());

                    comment.setComment(item.getContent());
                    comment.setTime(item.getCreateDate());
                    comment.setStar(item.getStars());
                    comment.setCommentId(item.getCommentId());

                    StoryReply daoReply = messageFacade.getReplyByCommentId(item.getCommentId());

                    if (daoReply != null ){
                        Reply reply = new Reply();
                        reply.setTime(daoReply.getCreateDate());
                        reply.setCommentId(daoReply.getCommentId());
                        reply.setComment(daoReply.getContent());
                        reply.setIcon(selfInfo.getIcon());
                        comment.setReply(reply);
                        comment.setHasReply(true);
                    }

                    review.setComment(comment);
                    list.add(review);

                }
                reviews.setReviews(list);
                reviews.setMessageCount(messageFacade.messageCount(userId));
                reviews.setReviewsCount(0);
                response.setData(reviews);

                // 将对应的所有评论置为已读
                messageFacade.readReviews(userId);
            }


        }catch (Exception e){
            response = new ResponseBean(StateEnum.MESSAGE_SEND_ERROR.getCode(), "");
            e.printStackTrace();
        }

        return response;
    }

    /**
     * 获取聊天消息
     *
     * @return
     */
    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean chats(@RequestHeader String voySession,@RequestParam String sessionId,@RequestParam String time,@RequestParam String size) {

        long userId = userFacade.getUserIdBySession(voySession);

        ChatRelation relation = messageFacade.getRelationId(userId, Long.parseLong(sessionId));

        long relationId = relation.getRelationId();

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try{
            ChatsResponse chatsResponse = new ChatsResponse();

            long timeVal = Long.parseLong(time);

            timeVal = timeVal== 0 ? System.currentTimeMillis() : Long.parseLong(time);

            List<ChatMessage> messages = messageFacade.queryChats(relationId, timeVal, Integer.parseInt(size));

            List<Chat> chats = new ArrayList<>();

            Map<Long, UserInfo> userTemp = new HashMap<>();
                for (ChatMessage item : messages){
                    Chat newChat = new Chat();

                    newChat.setChatTime(item.getCreateDate());

                    newChat.setIsReplay(item.getReplyId() > 0 ? 1 : 0);

                    newChat.setContent(item.getContent());

                    if (newChat.getIsReplay() > 0) {

                        StoryResponse story = new StoryResponse();
                        Story daoStory = messageFacade.getStoryByReplyId(item.getReplyId());

                        story.setTime(daoStory.getCreateDate());
                        story.setStoryId(daoStory.getStoryId());
                        story.setCoverDes(daoStory.getCoverDes());
                        newChat.setStory(story);
                    }

                    if (userTemp.get(item.getSenderId()) == null) {
                        cn.wondervoy.dao.bean.UserInfo userInfo = userFacade.getUserInfo(item.getSenderId());
                        UserInfo sender = new UserInfo();
                        sender.setLastName(userInfo.getLastName());
                        sender.setFirstName(userInfo.getFirstName());
                        sender.setUserId(item.getSenderId());
                        sender.setIcon(userInfo.getIcon());
                        newChat.setSender(sender);

                        userTemp.put(item.getSenderId(), sender);
                    } else {
                        newChat.setSender(userTemp.get(item.getSenderId()));
                    }

                    chats.add(newChat);

                }
            chatsResponse.setChats(chats);
            chatsResponse.setFriendId(Long.parseLong(sessionId));
            response.setData(chatsResponse);

            // 设置为已读
            messageFacade.readMessage(relationId, userId);

        }catch (Exception e){
            response = new ResponseBean(StateEnum.MESSAGE_SEND_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 聊天
     * @return
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean sendMessage(@RequestHeader String voySession,
                                    @RequestParam String receiverId,
                                    @RequestParam String content) {

        long userId = userFacade.getUserIdBySession(voySession);
        long receiverIdVal = Long.parseLong(receiverId);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Message sent!");

        try{
            messageFacade.sendMessage(userId,receiverIdVal,content);
        }catch (Exception e){
            response = new ResponseBean(StateEnum.MESSAGE_SEND_ERROR.getCode(), "Message sent failed , Please retry!");
        }

        return response;
    }

    /**
     * query
     * @return
     */
    @RequestMapping(value = "/conversation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean conversation(@RequestHeader String voySession) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try{
//            messageFacade.sendMessage(userId,receiverIdVal,content);
        }catch (Exception e){
            response = new ResponseBean(StateEnum.MESSAGE_SEND_ERROR.getCode(), "");
        }

        return response;
    }


}

