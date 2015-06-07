package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class UserDetail extends BaseRespones{

    private long userId;

    private int storyCount;

    private float stars = 4.0f;

    private UserInfo info;

    private UserSkills skills;

    private List<UserVerification> verifications;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public UserSkills getSkills() {
        return skills;
    }

    public void setSkills(UserSkills skills) {
        this.skills = skills;
    }

    public List<UserVerification> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<UserVerification> verifications) {
        this.verifications = verifications;
    }

    public int getStoryCount() {
        return storyCount;
    }

    public void setStoryCount(int storyCount) {
        this.storyCount = storyCount;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
