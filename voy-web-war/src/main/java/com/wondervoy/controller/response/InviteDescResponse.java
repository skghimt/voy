package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class InviteDescResponse extends BaseRespones{

    private String code;

    private String email;

    private UserInfo inviteUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(UserInfo inviteUser) {
        this.inviteUser = inviteUser;
    }
}
