package com.example.zane.homework.entity;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchClazz {

    private String clazzName;
    private String creatime;
    private String creator;
    private String courseName;

    public String getName() {
        return clazzName;
    }

    public void setName(String name) {
        this.clazzName = name;
    }

    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCourseName() {
        return  courseName;
    }

    public void setCourseName(String description) {
        this.courseName  = description;
    }
}
