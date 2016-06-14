package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

import java.io.Serializable;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 * 班级的实体类
 */

public class Clazz implements Serializable, IListModel{
    //列表班级号
    private int jxclassId;
    //课程名
    private String courseName = "统计学";
    //班级名
    private String className = "信息管理实验班";
    //创建者
    private String owner = "徐志";
    //班级头像
    private int image;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public int getJxclassId() {
        return jxclassId;
    }

    public void setJxclassId(int jxclassId) {
        this.jxclassId = jxclassId;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
