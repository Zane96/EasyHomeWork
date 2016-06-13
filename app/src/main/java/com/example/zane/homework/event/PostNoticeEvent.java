package com.example.zane.homework.event;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class PostNoticeEvent {
    private String postTime;
    private String content;

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
