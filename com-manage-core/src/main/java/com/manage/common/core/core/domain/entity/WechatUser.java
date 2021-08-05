package com.manage.common.core.core.domain.entity;

import java.io.Serializable;

public class WechatUser implements Serializable {

    private Long id;

    private String userName;

    private String openId;

    private String phoneNum;

    /** 性别： 0：女，1：男，2：性别不详细 */
    private Short sex;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }
}
