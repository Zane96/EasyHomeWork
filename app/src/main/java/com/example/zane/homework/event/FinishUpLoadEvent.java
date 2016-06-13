package com.example.zane.homework.event;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class FinishUpLoadEvent {
    private boolean isFinished;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
