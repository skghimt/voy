package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-20.
 */
public class UserLoginResponseBean extends BaseRespones{

    private String session;

    private UserInfo userInfo;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
