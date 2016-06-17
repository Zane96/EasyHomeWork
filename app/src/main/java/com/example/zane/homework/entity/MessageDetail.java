package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageDetail implements IListModel{

    private String action = "申请加入班级";
    private String state = "审核中";//0审核中1审核通过2审核未通过
    private String className = "信管实验班";
    private String owner = "徐志";
    private String avatar;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
