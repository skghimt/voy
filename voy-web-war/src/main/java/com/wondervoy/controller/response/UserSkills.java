package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class UserSkills extends BaseRespones{

    private String job;

    private String language;

    private String address;

    private String car;

    private Integer driverAge;

    private String interest;

    private SystemPropertiesResponse.SystemProperty addressDetail;

    private SystemPropertiesResponse.SystemProperty jobDetail;

    private List<SystemPropertiesResponse.SystemProperty> languageList;

    private List<SystemPropertiesResponse.SystemProperty> interestList;


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public List<SystemPropertiesResponse.SystemProperty> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<SystemPropertiesResponse.SystemProperty> languageList) {
        this.languageList = languageList;
    }

    public List<SystemPropertiesResponse.SystemProperty> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<SystemPropertiesResponse.SystemProperty> interestList) {
        this.interestList = interestList;
    }

    public SystemPropertiesResponse.SystemProperty getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(SystemPropertiesResponse.SystemProperty addressDetail) {
        this.addressDetail = addressDetail;
    }

    public SystemPropertiesResponse.SystemProperty getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(SystemPropertiesResponse.SystemProperty jobDetail) {
        this.jobDetail = jobDetail;
    }
}
