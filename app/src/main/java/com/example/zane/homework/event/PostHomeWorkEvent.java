package com.example.zane.homework.event;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class PostHomeWorkEvent {

    private String postTime;
    private String endTime;
    private String addtion;
    private String attachement;

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddtion() {
        return addtion;
    }

    public void setAddtion(String addtion) {
        this.addtion = addtion;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }
}
