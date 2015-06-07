package com.wondervoy.controller.response;

/**
 * Created by ckzhang on 14-12-22.
 */
public class UserVerification extends BaseRespones{

    private String verificationTerrace;

    private String account;

    private String verificationInfo;

    private long verificationTime;

    public String getVerificationTerrace() {
        return verificationTerrace;
    }

    public void setVerificationTerrace(String verificationTerrace) {
        this.verificationTerrace = verificationTerrace;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVerificationInfo() {
        return verificationInfo;
    }

    public void setVerificationInfo(String verificationInfo) {
        this.verificationInfo = verificationInfo;
    }

    public long getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(long verificationTime) {
        this.verificationTime = verificationTime;
    }
}
