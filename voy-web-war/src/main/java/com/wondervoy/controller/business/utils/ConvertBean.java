package com.wondervoy.controller.business.utils;

import cn.wondervoy.dao.bean.UserSkill;
import com.wondervoy.controller.response.UserInfo;
import com.wondervoy.controller.response.UserSkills;
import com.wondervoy.controller.response.UserVerification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckzhang on 14-12-28.
 */
public class ConvertBean {

    public static UserInfo convertUserInfo(cn.wondervoy.dao.bean.UserInfo info){

        if (info == null){
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(info.getUserId());
        userInfo.setIntro(info.getIntro());
        userInfo.setSex(info.getSex());
        userInfo.setLastName(info.getLastName());
        userInfo.setFirstName(info.getFirstName());
        userInfo.setIcon(info.getIcon());
        userInfo.setAddress(info.getAddress());
        userInfo.setBirthday(info.getBirthday());
        userInfo.setConstellation(info.getConstellation() != null ?info.getConstellation(): 0 );

        return userInfo;
    }

    public static UserSkills convertUserSkill(UserSkill skill){

        if (skill == null){
            return null;
        }

        UserSkills back = new UserSkills();

        back.setAddress(skill.getAddress());
        back.setLanguage(skill.getLanguage());
        back.setJob(skill.getJob());
        back.setDriverAge(skill.getDriverAge());
        back.setCar(skill.getCar());
        back.setInterest(skill.getInterest());

        return back;
    }

    public static List<UserVerification> convertUserVerificationList(List<cn.wondervoy.dao.bean.UserVerification> verifications){

        List<UserVerification> verificationList = new ArrayList<>();

        if (verifications != null){
            for (cn.wondervoy.dao.bean.UserVerification item : verifications) {
                verificationList.add(convertUserVerification(item));
            }
        }

        return verificationList;

    }

    public static UserVerification convertUserVerification(cn.wondervoy.dao.bean.UserVerification verification){

        UserVerification userVerification = new UserVerification();

        userVerification.setVerificationTime(verification.getVerificationTime());
        userVerification.setVerificationTerrace(verification.getVerificationTerrace());
        userVerification.setVerificationInfo(verification.getVerificationInfo());
        userVerification.setAccount(verification.getAccount());

        return userVerification;
    }
}
