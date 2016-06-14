package com.example.zane.homework.event;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.example.zane.homework.entity.Clazz;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class ActivityExitingEvent {
    private FragmentActivity activity;
    private Clazz clazz;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
