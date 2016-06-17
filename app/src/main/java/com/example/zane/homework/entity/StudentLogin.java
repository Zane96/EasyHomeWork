package com.example.zane.homework.entity;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.SecurePreferences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class StudentLogin {

    private Context context;
    private SecurePreferences preferences;
    private SecurePreferences.Editor editor;

    private StudentLogin(){
        this.context = App.getInstance();
        preferences = new SecurePreferences(this.context);
        editor = preferences.edit();
    }

    private static class SingletonHolder{
        private static final StudentLogin instance = new StudentLogin();
    }

    public static StudentLogin getInstacne(){
        return StudentLogin.SingletonHolder.instance;
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

    public int getAvatar() {
        return preferences.getInt("avatar", 0);
    }

    public void setAvatar(int avatar) {
        editor.putInt("avatar", avatar);
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

    public String[] getCourse() {
        Set<String> courseNames = preferences.getStringSet("coursename", new HashSet<String>());
        String[] datas = new String[courseNames.size()];
        Iterator<String> iterator = courseNames.iterator();
        int i = 0;
        while (iterator.hasNext()){
            datas[i] = iterator.next();
            i++;
        }
        return datas;
    }

    public void setCourse(String[] datas) {
        HashSet<String> courseNames = new HashSet<>();
        for (int i = 0; i < datas.length; i++){
            courseNames.add(datas[i]);
        }
        editor.putStringSet("coursename", courseNames);
        editor.commit();
    }

    public String[] getIds() {
        Set<String> ids = preferences.getStringSet("ids", new HashSet<String>());
        String[] datas = new String[ids.size()];
        Iterator<String> iterator = ids.iterator();
        int i = 0;
        while (iterator.hasNext()){
            datas[i] = iterator.next();
            i++;
        }
        return datas;
    }

    public void setIds(String[] datas) {
        HashSet<String> ids = new HashSet<>();
        for (int i = 0; i < datas.length; i++){
            ids.add("ID: "+datas[i]);
        }
        editor.putStringSet("ids", ids);
        editor.commit();
    }

    public String getClazz(){
        return preferences.getString("clazzname", "");
    }

    public void setClazz(String clazzName){
        editor.putString("clazzname", clazzName);
        editor.commit();
    }

    public void setOwner(String data){
        editor.putString("owner", data);
        editor.commit();
    }

    public String getOwner(){
        return preferences.getString("owner", "");
    }

    public void setIsOwner(boolean data){
        editor.putBoolean("isowner", data);
        editor.commit();
    }

    public boolean getIsOwner(){
        return preferences.getBoolean("isowner", false);
    }
}
