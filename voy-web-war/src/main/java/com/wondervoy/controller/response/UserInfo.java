package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class UserInfo extends BaseRespones{

    private String firstName;

    private String lastName;

    private String sex;

    private String intro;

    private String icon;

    private String address;

    private long userId;

    private String birthday;

    private long constellation;

    private String constellationStr;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getConstellation() {
        return constellation;
    }

    public void setConstellation(long constellation) {
        this.constellation = constellation;
    }

    public String getConstellationStr() {
        return constellationStr;
    }

    public void setConstellationStr(String constellationStr) {
        this.constellationStr = constellationStr;
    }
}
