package com.wondervoy.controller.response;

import cn.wondervoy.dao.bean.FunnyVEx;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class FunnyVResponse extends BaseRespones{

    private List<FunnyVEx> funny;

    public List<FunnyVEx> getFunny() {
        return funny;
    }

    public void setFunny(List<FunnyVEx> funny) {
        this.funny = funny;
    }
}
