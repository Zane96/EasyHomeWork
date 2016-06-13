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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class UpLoadFileService extends Service{

    private int percent = 0;
    private Map<Integer, Integer> maps;

    @Override
    public void onCreate() {
        super.onCreate();
        maps = new HashMap<>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int position = intent.getIntExtra(ClazzDetailActivityPresenter.POSITION, 0);
        Log.i("UpLoadService", "start " + position);
        maps.put(position, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int percent = 0;
                FinishUpLoadEvent event = new FinishUpLoadEvent();
                event.setPosition(position);
                while (percent < 100){
                    event.setFinished(false);
                    percent += 10;
                    EventBus.getDefault().post(event);
                    setPercent(position, percent);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                event.setFinished(true);
                event.setPosition(position);
                EventBus.getDefault().post(event);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }


    public class MyBinder extends Binder{
        public UpLoadFileService getServiceContext(){
            return UpLoadFileService.this;
        }
    }

    public int getPercent(int position) {
        return maps.get(position);
    }

    private void setPercent(int position, int percent) {
        maps.put(position, percent);
    }
}
