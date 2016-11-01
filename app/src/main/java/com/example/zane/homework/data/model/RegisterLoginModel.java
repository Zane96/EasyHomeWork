package com.example.zane.homework.data.model;


import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.Login;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.QuitLogin;
import com.example.zane.homework.data.bean.Registe;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.service.LoginRegisteService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 *
 * 登陆以及注册的数据接口model
 */

public class RegisterLoginModel {

    private Context context;
    private LoginRegisteService serviceApi;

    private RegisterLoginModel(){
        context = App.getInstance();
        serviceApi = CommonProvider.provideRetrofit()
                .baseUrl(LoginRegisteService.BASE_URL)
                .build()
                .create(LoginRegisteService.class);
    }

    private static final class InstanceHolder{
        private final static RegisterLoginModel instance = new RegisterLoginModel();
    }

    public RegisterLoginModel getInstance(){
        return InstanceHolder.instance;
    }

    //注册
    public Observable<Registe> register(String sirstu, String username, String realname, String password,
                                       String gender, String introduce){
        return serviceApi.registe(sirstu, username, realname, password, gender, introduce)
                .compose(new SchedulerTransform<Registe>())
                .compose(new ErrorTransForm<Registe>());

    }

    //登陆
    public Observable<Login> login(String username, String password, String sirstu){
        return serviceApi.login(username, password, sirstu)
                .compose(new SchedulerTransform<>())
                .compose(new ErrorTransForm<>());
    }

    //退出登陆
    public Observable<QuitLogin> quitLogin(){
        return serviceApi.quitLogin()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

}
