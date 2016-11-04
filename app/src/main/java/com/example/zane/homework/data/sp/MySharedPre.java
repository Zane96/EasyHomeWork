package com.example.zane.homework.data.sp;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.SecurePreferences;

/**加密本地序列化存储
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

    public void setLogin(boolean data){
        editor.putBoolean("login", data);
        editor.commit();
    }

    public boolean getLogin(){
        return preferences.getBoolean("login", false);
    }

    public void setCookie(String cookie){
        editor.putString("cookie", cookie);
        editor.commit();
    }

    public String getCookie(){
        return preferences.getString("cookie", "");
    }
}
