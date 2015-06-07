package com.wondervoy.controller.response;

import java.io.Serializable;

/**
 * Created by ckzhang on 14-12-20.
 */
public class ResponseBean<T extends BaseRespones> implements Serializable{

    private int state;

    private String message;

    private T data;

    public ResponseBean (int state, String message){
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
