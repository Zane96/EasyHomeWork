package com.example.zane.homework.event;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class UpLoadFileEvent {

    private int position;
    private int asId;

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
