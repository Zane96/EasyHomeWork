package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberDetail implements IListModel{
    //个人id
    private int studentId;
    //姓名
    private String name = "徐志";
    //性别
    private String gender;
    //是否管理员
    private String isAdmin;
    //个人介绍
    private String selfIntro;
    //学号
    private String number = "2014210876";

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
