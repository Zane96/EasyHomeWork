package com.example.zane.homework.data.model;


import android.content.Context;

import com.example.zane.homework.app.App;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 *
 * 登陆以及注册的数据接口
 */

public class RegisterLoginModel {

    private Context context;

    private RegisterLoginModel(){
        context = App.getInstance();
    }

    private static final class InstanceHolder{
        private final static RegisterLoginModel instance = new RegisterLoginModel();
    }

    public RegisterLoginModel getInstance(){
        return InstanceHolder.instance;
    }


}
