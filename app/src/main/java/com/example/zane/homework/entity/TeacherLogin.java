package com.example.zane.homework.entity;

import android.content.Context;
import android.net.Uri;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.SecurePreferences;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 * 单列保持登录类
 */

public class TeacherLogin {

    private Context context;
    private SecurePreferences preferences;
    private SecurePreferences.Editor editor;

    private TeacherLogin(){
        this.context = App.getInstance();
        preferences = new SecurePreferences(this.context);
        editor = preferences.edit();
    }

    private static class SingletonHolder{
        private static final TeacherLogin instance = new TeacherLogin();
    }

    public static TeacherLogin getInstacne(){
        return SingletonHolder.instance;
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public String getPsd() {
        return preferences.getString("psd", "");
    }

    public void setPsd(String psd) {
        editor.putString("psd", psd);
        editor.commit();
    }

    public String getUserName() {
        return preferences.getString("username", "");
    }

    public void setUserName(String userName) {
        editor.putString("username", userName);
        editor.commit();
    }

    public String getSelfIntro() {
        return preferences.getString("selfintro", "");
    }

    public void setSelfIntro(String selfIntro) {
        editor.putString("selfintro", selfIntro);
        editor.commit();
    }

    public String getAvatar() {
        return preferences.getString("avatar", "");
    }

    public void setAvatar(String avatar) {
        editor.putString("avatar", avatar);
        editor.commit();
    }

    public String getGender() {
        return preferences.getString("gender", "");
    }

    public void setGender(String gender) {
        editor.putString("gender", gender);
        editor.commit();
    }

    public String getSessionid() {
        return preferences.getString("sessionid", "");
    }

    public void setSessionid(String sessionid) {
        editor.putString("sessionid", sessionid);
        editor.commit();
    }

    public boolean isLogin() {
        return preferences.getBoolean("isLogin", false);
    }

    public void setLogin(boolean login) {
        editor.putBoolean("isLogin", login);
        editor.commit();
    }

    public String getCourse() {
        return preferences.getString("course", "");
    }

    public void setCourse(String course) {
        editor.putString("course", course);
        editor.commit();
    }
}
