package com.example.zane.homework.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jude.utils.JUtils;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 */

public class App extends Application{

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        JUtils.initialize(this);
        Stetho.initializeWithDefaults(this);
    }

    public static App getInstance(){
        return instance;
    }
}
