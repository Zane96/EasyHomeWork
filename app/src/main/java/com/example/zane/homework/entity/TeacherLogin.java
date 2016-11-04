package com.example.zane.homework.entity;

import android.content.Context;
import android.net.Uri;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.SecurePreferences;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    public String getTid() {
        return preferences.getString("tid", "");
    }

    public void setTid(String tid) {
        editor.putString("tid", tid);
        editor.commit();
    }

    public String getCourse() {
        return preferences.getString("course", "");
    }

    public void setCourse(String course) {
        editor.putString("course", course);
        editor.commit();
    }

    public void setClazz(String[] datas){
        Set<String> clazzNames = new HashSet<>();
        for (int i = 0; i < datas.length; i++){
            clazzNames.add(datas[i]);
        }
        editor.putStringSet("clazzname", clazzNames);
        editor.commit();
    }

    public String[] getClazz(){
        Set<String> clazzNames = preferences.getStringSet("clazzname", new HashSet<String>());
        String[] datas = new String[clazzNames.size()];
        Iterator<String> iterator = clazzNames.iterator();
        int i = 0;
        while (iterator.hasNext()){
            datas[i] = iterator.next();
            i++;
        }
        return datas;
    }

    public void setOwners(String[] datas){
        Set<String> owners = new HashSet<>();
        for (int i = 0; i < datas.length; i++){
            owners.add(datas[i]);
        }
        editor.putStringSet("owners", owners);
        editor.commit();
    }

    public String[] getOwners(){
        Set<String> owners = preferences.getStringSet("owners", new HashSet<String>());
        String[] datas = new String[owners.size()];
        Iterator<String> iterator = owners.iterator();
        int i = 0;
        while (iterator.hasNext()){
            datas[i] = iterator.next();
            i++;
        }
        return datas;
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
            ids.add("ID: " + datas[i]);
        }
        editor.putStringSet("ids", ids);
        editor.commit();
    }
}
