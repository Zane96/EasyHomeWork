package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

import java.io.Serializable;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberDetail implements IListModel, Serializable{
    //个人id
    private int studentId;
    //姓名
    private String name = "徐志";
    //性别
    private String gender;
    //是否管理员
    private String isAdmin;
    //个人介绍
    private String selfIntro = "爱生活爱编程";
    //学号
    private String number = "2014210876";
    //得分
    private String score = "50分";
    //提交的作业次数
    private String works = "15次";
    //没有提交的昨夜次数
    private String noWorks = "2次";
    //头像id
    private int avatar;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getNoWorks() {
        return noWorks;
    }

    public void setNoWorks(String noWorks) {
        this.noWorks = noWorks;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
