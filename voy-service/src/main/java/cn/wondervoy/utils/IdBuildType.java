package cn.wondervoy.utils;

/**
 * Created by ckzhang on 14-11-3.
 */
public enum IdBuildType {

    /**
     * 用户ID生成编号
     */
    USERID(1000),
    /**
     * 孩子ID生成编号
     */
    STORYID(2000),
    /**
     * 省Id生成编号
     */
    STORY_COMMENTID (3000),

    STORY_REPLY (3001),
    /**
     * 市Id生成编号
     */
    USER_COMMENTID (3001),

    USER_RELATIONID (4001);


    private Integer code;
    public Integer getCode() {
        return code;
    }

    /**
     *
     * Creates a new instance of SchoolTypeEnum.
     *
     * @param code
     */
    private IdBuildType(Integer code) {
        this.code = code;
    }
}
