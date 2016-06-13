package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetail implements IListModel{
    //作业id
    private int asId;
    //百分比
    private int percentage;
    //上传的文件名
    private String attachement = "null";
    //截止日期
    private String deadLine = "截至: 16/05/28 23:59";
    //发布日期
    private String pubTimel = "发布: 16/05/25 21:48";
    //说明
    private String addtion = "数据库第三次课堂作业";
    //班级号
    private int jid;

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public String getPubTimel() {
        return pubTimel;
    }

    public void setPubTimel(String pubTimel) {
        this.pubTimel = pubTimel;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getAddtion() {
        return addtion;
    }

    public void setAddtion(String addtion) {
        this.addtion = addtion;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
