package com.wondervoy.controller.business;

import cn.wondervoy.dao.bean.Story;
import cn.wondervoy.dao.bean.StoryComment;
import cn.wondervoy.dao.bean.StoryReply;
import cn.wondervoy.dao.bean.UserInfo;
import cn.wondervoy.facade.IStoryFacade;
import cn.wondervoy.facade.IUserFacade;
import com.alibaba.fastjson.JSON;
import com.wondervoy.controller.response.*;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/story")
public class StoryController {

//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserFacade userFacade;

    @Autowired
    private IStoryFacade storyFacade;


    /**
     * 发布
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean publish(@RequestHeader String voySession, @RequestParam String coverPic,
                               @RequestParam String coverDesc, @RequestParam String story, @RequestParam String type) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Story published!");

        try {
            long storyId = storyFacade.publish(userId, coverPic, coverDesc, story, Integer.parseInt(type));

            PublishStoryResponseBean publishStoryResponseBean = new PublishStoryResponseBean();
            publishStoryResponseBean.setStoryId(storyId);
            response.setData(publishStoryResponseBean);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "Story publish failed, Please retry!");
        }

        return response;
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean main(@RequestParam String page,
                             @RequestParam String size) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try {
            int pageVal = Integer.parseInt(page);

            int sizeVal = Integer.parseInt(size);
            int index = ( pageVal - 1 ) * sizeVal;

            List<Long> populers = storyFacade.getMainPopuler(index, sizeVal);

            List<StoryResponseBean> storyResponseBeans = new ArrayList<>();

            for (Long userId : populers){
                UserInfo userInfo = userFacade.getUserInfo(userId);

                List<Story> myStorys = storyFacade.userStory(userId, System.currentTimeMillis(), 0, 1);

                List<StoryResponse> storyResponses = new ArrayList<>();

                for (Story story : myStorys){

                    StoryResponse item = new StoryResponse();
                    item.setStoryId(story.getStoryId());
                    item.setCoverDes(story.getCoverDes());
                    item.setCoverUrl(story.getCoverUrl());
                    item.setTime(story.getCreateDate());
                    storyResponses.add(item);
                }

                StoryResponseBean storyList = new StoryResponseBean();
                storyList.setStorys(storyResponses);
                storyList.setIcon(userInfo.getIcon());
                storyList.setUserId(userInfo.getUserId());
                storyList.setLastName(userInfo.getLastName());
                storyList.setFirstName(userInfo.getFirstName());

                storyResponseBeans.add(storyList);
            }

            StoryMainResponseBean mainResponseBean = new StoryMainResponseBean();
            mainResponseBean.setPopulers(storyResponseBeans);
            mainResponseBean.setHasMore(storyFacade.hasMore(index + sizeVal));
            response.setData(mainResponseBean);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "/userStory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean userStory(@RequestParam String storyId,
                                  @RequestParam String size, @RequestParam String userId, @RequestParam String type) {


//        long userId = userFacade.getUserIdBySession(voySession);
        long userIdVal = Long.parseLong(userId);

        UserInfo userInfo = userFacade.getUserInfo(userIdVal);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");
        StoryResponseBean storyList = new StoryResponseBean();

        try {
            int getSize = Integer.parseInt(size);
            long storyIdValue = Long.parseLong(storyId);

            List<Story> myStorys = storyFacade.userStory(userIdVal, storyIdValue, Integer.parseInt(type), getSize);

            List<StoryResponse> storyResponses = new ArrayList<>();

            long queryStoryId = 0;

            for (Story story : myStorys){

                StoryResponse item = new StoryResponse();
                item.setStoryId(story.getStoryId());
                item.setCoverDes(story.getCoverDes());
                item.setCoverUrl(story.getCoverUrl());
                item.setTime(story.getCreateDate());
                storyResponses.add(item);

                queryStoryId = story.getStoryId();
            }


            storyList.setStorys(storyResponses);
            storyList.setIcon(userInfo.getIcon());
            storyList.setUserId(userInfo.getUserId());
            storyList.setLastName(userInfo.getLastName());
            storyList.setFirstName(userInfo.getFirstName());
            storyList.setHasMore(storyFacade.userHasStory(userIdVal, queryStoryId,Integer.parseInt(type)));



        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "");
        }

        storyList.setStoryCount(storyFacade.getStoryCountByType(userIdVal, 0));
        storyList.setDrafftCount(storyFacade.getStoryCountByType(userIdVal, 1));

        response.setData(storyList);

        return response;
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "/story", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean story(@RequestParam String storyId,  @RequestParam String commentSize) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try {

            int commentSizeVal = Integer.parseInt(commentSize);

            long stortIdValue = Long.parseLong(storyId);

            Story story = storyFacade.story(stortIdValue);

            UserInfo userInfo = userFacade.getUserInfo(story.getSenderId());

            StoryResponse backStory = new StoryResponse();
            backStory.setStoryId(story.getStoryId());
            backStory.setCoverDes(story.getCoverDes());
            backStory.setCoverUrl(story.getCoverUrl());
            backStory.setTime(story.getCreateDate());

            String content = story.getStory();
            List<PicStory> picStories = JSON.parseArray(content, PicStory.class);

            backStory.setPicStories(picStories);

            if (commentSizeVal == 0 ) {
                List<Comment> commentList = new ArrayList<>();

                Map<Long, UserInfo> userMap = new HashMap<>();
                List<StoryComment> comments = storyFacade.getComments(stortIdValue, System.currentTimeMillis(), commentSizeVal);
                for (StoryComment comment : comments) {

                    UserInfo user = userMap.get(comment.getUserId());
                    if (user == null) {
                        user = userFacade.getUserInfo(comment.getUserId());
                    }

                    Comment commentItem = new Comment();
                    commentItem.setTime(comment.getCreateDate());
                    commentItem.setStar(comment.getStars());
                    commentItem.setComment(comment.getContent());

                    commentItem.setFirstName(user.getFirstName());
                    commentItem.setLastName(user.getLastName());
                    commentItem.setUserId(user.getUserId());
                    commentItem.setIcon(user.getIcon());
                    commentList.add(commentItem);

                }
                backStory.setComments(commentList);
            }


            StoryResponseBean storyList = new StoryResponseBean();
            storyList.setStory(backStory);
            storyList.setIcon(userInfo.getIcon());
            storyList.setUserId(userInfo.getUserId());
            storyList.setLastName(userInfo.getLastName());
            storyList.setFirstName(userInfo.getFirstName());

            response.setData(storyList);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 评论
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean comment(@RequestHeader String voySession, @RequestParam String storyId,
                                  @RequestParam String comment, @RequestParam String stars) {


        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Review successfully!");

        try {

            long storyIdValue = Long.parseLong(storyId);

            int starsCount = 0;
            if (storyFacade.myCommentStars(userId, storyIdValue) == 0){
                starsCount = Integer.parseInt(stars);
            }


            long commentId = storyFacade.comment(userId, storyIdValue, comment, starsCount);

            StoryCommentResponseBean storyCommentResponseBean = new StoryCommentResponseBean();
            storyCommentResponseBean.setCommentId(commentId);
            response.setData(storyCommentResponseBean);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "Review failed, Please retry!");
        }

        return response;
    }

    /**
     * 回复
     * @return
     */
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean reply(@RequestHeader String voySession, @RequestParam String commentId,
                                @RequestParam String comment) {


        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        try {
            long commentIdVal = Long.parseLong(commentId);

            long replyId = storyFacade.reply(userId, commentIdVal, comment);

            StoryCommentResponseBean storyCommentResponseBean = new StoryCommentResponseBean();
            storyCommentResponseBean.setReplyId(replyId);
            response.setData(storyCommentResponseBean);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 评论
     * @return
     */
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean comments(@RequestParam String storyId, @RequestParam String userId,
                                @RequestParam String commentId, @RequestParam String size) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        long userIdVal = Long.parseLong(userId);

        try {

            long storyIdValue = Long.parseLong(storyId);

            long commentIdValue = Long.parseLong(commentId);

            int commentSizeVal = Integer.parseInt(size);

            if (commentIdValue == 0){
                commentIdValue = System.currentTimeMillis();
            }

            List<Comment> commentList = new ArrayList<>();

            Map<Long, UserInfo> userMap = new HashMap<>();

            List<StoryComment> comments =  storyFacade.getComments(storyIdValue, commentIdValue, commentSizeVal);

            long id = 0;

            for (StoryComment comment : comments){

                id = comment.getCommentId();

                UserInfo user = userMap.get(comment.getUserId());
                if (user == null) {
                    user = userFacade.getUserInfo(comment.getUserId());
                }

                Comment commentItem = new Comment();
                commentItem.setTime(comment.getCreateDate());
                commentItem.setStar(comment.getStars());
                commentItem.setComment(comment.getContent());
                commentItem.setCommentId(comment.getCommentId());

                commentItem.setFirstName(user.getFirstName());
                commentItem.setLastName(user.getLastName());
                commentItem.setUserId(user.getUserId());
                commentItem.setIcon(user.getIcon());

                Long commentItemCommentId = commentItem.getCommentId();

                StoryReply reply = storyFacade.getReplyByCommentId(commentItemCommentId);
                if (reply != null){
                    commentItem.setHasReply(true);
                    Reply r = new Reply();
                    r.setComment(reply.getContent());
                    r.setTime(reply.getCreateDate());
                    r.setReplyId(reply.getReplyId());
                    r.setUserId(reply.getUserId());

                    UserInfo userInfo = userFacade.getUserInfo(reply.getUserId());
                    r.setIcon(userInfo.getIcon());
                    r.setFirstName(userInfo.getFirstName());
                    r.setLastName(userInfo.getLastName());

                    commentItem.setReply(r);
                }


                commentList.add(commentItem);

            }

            CommentList backComment = new CommentList();
            backComment.setComments(commentList);

            if (userIdVal != 0) {
                backComment.setStars(storyFacade.myCommentStars(userIdVal, storyIdValue));
            }

            backComment.setHasMore(storyFacade.getComments(storyIdValue, id, 1).size() > 0);

            response.setData(backComment);

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "");
        }

        return response;
    }

    /**
     * 发布
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean delete(@RequestHeader String voySession, @RequestParam String storyId) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Story is invalid!");

        try {
            long storyIdVal = Long.parseLong(storyId);



        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.STORY_PUBLISH_ERROR.getCode(), "Story delete failed!");
        }

        return response;
    }

}

