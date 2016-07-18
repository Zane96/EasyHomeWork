package com.example.zane.homework.app;

import android.app.Application;
import android.webkit.CookieManager;

import com.jude.utils.JUtils;
import com.squareup.leakcanary.LeakCanary;

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
        LeakCanary.install(this);
    }

    public static App getInstance(){
        return instance;
    }
}
