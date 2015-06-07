package com.wondervoy.controller.request;

import java.io.Serializable;

/**
 * Created by ckzhang on 14-12-20.
 */
public class UserLoginRequestBean implements Serializable {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
