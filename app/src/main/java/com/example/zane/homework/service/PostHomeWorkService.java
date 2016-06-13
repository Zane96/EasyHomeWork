package com.example.zane.homework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.event.FinishUpLoadEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class PostHomeWorkService extends Service{

    private int percent = 0;

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int percent = 0;
                while (percent < 100){
                    percent += 10;
                    setPercent(percent);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PostHomeWorkService.MyBinder();
    }


    public class MyBinder extends Binder {
        public PostHomeWorkService getServiceContext(){
            return PostHomeWorkService.this;
        }
    }

    public int getPercent() {
        return percent;
    }

    private void setPercent(int percent) {
        this.percent = percent;
    }
}
