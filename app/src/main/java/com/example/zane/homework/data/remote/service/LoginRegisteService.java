package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.Login;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.QuitLogin;
import com.example.zane.homework.data.bean.Registe;
import com.example.zane.homework.data.bean.StuHaveClass;

import javax.crypto.interfaces.PBEKey;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface LoginRegisteService {

    String BASE_URL = "http://115.159.113.116/index.php/Login";

    //-----------------------Login/Registe-------------------------

    //学生/老师账号注册
    @POST("insert")
    @FormUrlEncoded
    Observable<Registe> registe(@Field("sirstu") String sirstu,
                                @Field("username") String userName,
                                @Field("realname") String realName,
                                @Field("password") String password,
                                @Field("gender") String gender,
                                @Field("introduce") String introduce);

    //学生／老师帐号登陆
    @POST("isvalid")
    @FormUrlEncoded
    Observable<Login> login(@Field("username") String userName,
                            @Field("password") String password,
                            @Field("sirstu") String sirstu);

    //注销登陆
    @POST("quit")
    Observable<QuitLogin> quitLogin();
}
