package com.wondervoy.controller.utils;

/**
 * Created by ckzhang on 14-12-20.
 */
public enum StateEnum {

    // 正常的返回值
    OK(0),

    //
    ERROR(1),

    SESSION_TIMEOUT(100),

    // 账号未注册返回值
    ACCOUNT_NOT_REGISTER(1000),

    // 密码错误返回值
    ACCOUNT_ERROR_PASSWORD(1001),

    // 账号未认证返回值
    ACCOUNT_NOT_VERIFICATION(1002),

    // 账号已存在返回值
    ACCOUNT_EXIST(1003),

    // 账号激活码错误返回值
    ACCOUNT_ACTIVE_ERROR(1004),

    // 账号激活码失效返回值
    ACCOUNT_ACTIVE_INVALID(1005),

    // 账号已激活
    ACCOUNT_ACTIVEED(1006),

    // 邀请码失效
    ACCOUNT_INVITE_INVALID(1100),

    // 邀请码错误
    ACCOUNT_INVITE_ERROR(1101),

    // story 发布错误
    STORY_PUBLISH_ERROR(2000),

    FILE_UPLOAD_ERROR(3000),

    MESSAGE_SEND_ERROR(4000),

    USER_UPDATE_ERROR(5000),

    StateEnum(5000);


    private int code;
    public int getCode() {
        return code;
    }

    /**
     *
     * Creates a new instance of SchoolTypeEnum.
     *
     * @param code
     */
    private StateEnum(int code) {
        this.code = code;
    }
}
