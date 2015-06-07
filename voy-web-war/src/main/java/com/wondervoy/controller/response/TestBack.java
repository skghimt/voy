package com.wondervoy.controller.response;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckzhang on 14-12-28.
 */
public class TestBack {

    public static void main(String[] aa){
        System.out.println(getMyInfo());

        System.out.println(getUserStorys());

        System.out.println(getStoryDetail());

        System.out.println(getVoid());

    }

    private static String getVoid(){
        ResponseBean responseBean = new ResponseBean(0, "操作成功");
        return JSON.toJSONString(responseBean);
    }

    private static String getStoryDetail(){

        ResponseBean responseBean = new ResponseBean(0, "获取story成功");
        StoryResponseBean storyResponseBean = new StoryResponseBean();

        StoryResponse story = new StoryResponse();
        story.setCoverUrl("http://jxfile.qiniudn.com/1418958528173hKnefFDPvs.jpg");
        story.setCoverDes("这是一个测试");

        List<PicStory> picStories = new ArrayList<>();
        PicStory pic1 = new PicStory();
        pic1.setDesc("这是一幅美丽的图片");
        pic1.setPic("http://jxfile.qiniudn.com/14189588051584B1NQEb9SP.jpg");
        picStories.add(pic1);


        PicStory pic2 = new PicStory();
        pic2.setDesc("这是dier幅美丽的图片");
        pic2.setPic("http://jxfile.qiniudn.com/1418959047459mi0Y9gZfV5.jpg");
        picStories.add(pic2);

        story.setPicStories(picStories);

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setIcon("http://jxfile.qiniudn.com/1418784361735Hi8VQ0O3zK.jpg");
        comment.setLastName("jia");
        comment.setFirstName("aobo");
        comment.setStar(1);
        comment.setComment("这个邮寄太漂亮了");
        comment.setTime(System.currentTimeMillis());
        comments.add(comment);
        story.setComments(comments);

        story.setStoryId(1241l);

        storyResponseBean.setUserId(11l);
        storyResponseBean.setFirstName("chongkai");
        storyResponseBean.setLastName("zhang");
        storyResponseBean.setIcon("http://jxfile.qiniudn.com/1418784361735Hi8VQ0O3zK.jpg");

        storyResponseBean.setStory(story);

        storyResponseBean.setUserId(11l);
        storyResponseBean.setFirstName("chongkai");
        storyResponseBean.setLastName("zhang");
        storyResponseBean.setIcon("http://jxfile.qiniudn.com/1418784361735Hi8VQ0O3zK.jpg");

        responseBean.setData(storyResponseBean);


        return JSON.toJSONString(responseBean);
    }

    private static String getUserStorys(){

        ResponseBean responseBean = new ResponseBean(0, "获取story成功");

        StoryResponseBean storyResponseBean = new StoryResponseBean();

        List<StoryResponse> storys = new ArrayList<>();

        StoryResponse story = new StoryResponse();
        story.setCoverUrl("http://jxfile.qiniudn.com/1418958528173hKnefFDPvs.jpg");
        story.setCoverDes("这是一个测试");
        story.setStoryId(1241l);
        storys.add(story);

        StoryResponse story1 = new StoryResponse();
        story1.setCoverUrl("http://jxfile.qiniudn.com/1418958528173hKnefFDPvs.jpg");
        story1.setCoverDes("这是二个测试");
        story.setStoryId(1251l);
        storys.add(story1);


        storyResponseBean.setUserId(11l);
        storyResponseBean.setFirstName("chongkai");
        storyResponseBean.setLastName("zhang");
        storyResponseBean.setIcon("http://jxfile.qiniudn.com/1418784361735Hi8VQ0O3zK.jpg");

        storyResponseBean.setStorys(storys);

        responseBean.setData(storyResponseBean);



        return JSON.toJSONString(responseBean);
    }

    private static String getMyInfo(){

        ResponseBean responseBean = new ResponseBean(0, "获取个人信息成功");

        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(123l);

        UserInfo userInfo = new UserInfo();
        userInfo.setIcon("http://jxfile.qiniudn.com/1418784361735Hi8VQ0O3zK.jpg");
        userInfo.setAddress("xi'an");
        userInfo.setFirstName("chongkai");
        userInfo.setLastName("zhang");
        userInfo.setSex("F");
        userInfo.setIntro("这是我为什么使用这个的原因 不拉不拉不拉");

        userDetail.setInfo(userInfo);

        UserSkills skills = new UserSkills();
        skills.setAddress("Bengjing,Shanghai");
        skills.setCar("HAVAL 2");
        skills.setDriverAge(2);
        skills.setJob("Software En");
        skills.setLanguage("Chinese,English");


        userDetail.setSkills(skills);


        List<UserVerification> verificationList = new ArrayList<>();


        UserVerification verification = new UserVerification();
        verification.setAccount("skghimt@126.com");
        verification.setVerificationInfo("");
        verification.setVerificationTerrace("email");
        verification.setVerificationTime(System.currentTimeMillis());

        verificationList.add(verification);


        UserVerification verification1 = new UserVerification();
        verification1.setAccount("skghimt@126.com");
        verification1.setVerificationInfo("凯_SkgHimT");
        verification1.setVerificationTerrace("weibo");
        verification1.setVerificationTime(System.currentTimeMillis());

        verificationList.add(verification1);


        userDetail.setVerifications(verificationList);

        responseBean.setData(userDetail);

        return JSON.toJSONString(responseBean);
    }


}
