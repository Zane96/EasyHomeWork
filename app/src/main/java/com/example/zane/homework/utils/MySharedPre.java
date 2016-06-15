package com.example.zane.homework.utils;

import android.content.Context;

import com.example.zane.homework.app.App;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class MySharedPre {
    private static final String TAG = MySharedPre.class.getSimpleName();
    private Context context;
    private SecurePreferences preferences;
    private SecurePreferences.Editor editor;

    private MySharedPre() {
        this.context = App.getInstance();
        preferences = new SecurePreferences(this.context);
        editor = preferences.edit();
    }

    private static class SingletonHolder{
        private static final MySharedPre instance = new MySharedPre();
    }

    public static MySharedPre getInstance(){
        return SingletonHolder.instance;
    }

    public void setIdentity(String identity){
        editor.putString("identity", identity);
        editor.commit();
    }

    public String getIdentity(){
        return preferences.getString("identity", "");
    }
}
