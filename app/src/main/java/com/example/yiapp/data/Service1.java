package com.example.yiapp.data;

import org.litepal.crud.LitePalSupport;

public class Service1 extends LitePalSupport {
    int id;
    int userId;
    int needId;
    int serviceId;
    String state;
    String grade;
    String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNeedId() {
        return needId;
    }

    public void setNeedId(int needId) {
        this.needId = needId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
