package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class NoticeDetail implements IListModel{
    //发送者id
    private int poster;
    //收件人id
    private int receiver;
    //发送内容
    private String content = "明天上课记得带书!";
    //发送的类型(公告or私信)
    private int type;
    //发送时间
    private String postTime = "05月31日 16:56";

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
