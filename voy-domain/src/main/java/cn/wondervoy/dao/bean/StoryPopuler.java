package cn.wondervoy.dao.bean;

/**
 * Created by ckzhang on 15-1-22.
 */
public class StoryPopuler {

    private long userId;

    private long maxTime;

    private int num;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
