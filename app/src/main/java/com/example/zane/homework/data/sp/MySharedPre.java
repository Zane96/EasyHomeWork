package com.example.zane.homework.data.sp;

import android.content.Context;

import com.example.zane.homework.app.App;

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

    public void setName(String name){
        editor.putString("name", name);
        editor.commit();
    }

    public String getName(){
        return preferences.getString("name", "");
    }

    public void setRealName(String realName){
        editor.putString("realname", realName);
        editor.commit();
    }

    public String getRealName(){
        return preferences.getString("realname", "");
    }

    public void setIntro(String intro){
        editor.putString("intro", intro);
        editor.commit();
    }

    public String getIntro(){
        return preferences.getString("intro", "");
    }

    public void setGender(String gender){
        editor.putString("gender", gender);
        editor.commit();
    }

    public String getGender(){
        return preferences.getString("gender", "");
    }

    public void setSid(String sid) {
        editor.putString("sid", sid);
        editor.commit();
    }

    public String getSid() {
        return preferences.getString("sid", "");
    }

}
