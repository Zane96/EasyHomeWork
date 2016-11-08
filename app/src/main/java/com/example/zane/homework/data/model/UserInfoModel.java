package com.example.zane.homework.data.model;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.PerInfo;
import com.example.zane.homework.data.bean.Registe;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.service.LoginRegisteService;
import com.example.zane.homework.data.remote.service.UserService;

import rx.Observable;

/**
 * Created by Zane on 16/11/3.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class UserInfoModel {

    private Context context;
    private UserService serviceApi;

    private UserInfoModel(){
        context = App.getInstance();
        serviceApi = CommonProvider.getRetrofitBuilder()
                             .baseUrl(UserService.BASE_URL)
                             .build()
                             .create(UserService.class);
    }

    private static final class InstanceHolder{
        private final static UserInfoModel instance = new UserInfoModel();
    }

    public static UserInfoModel getInstance(){
        return UserInfoModel.InstanceHolder.instance;
    }

    //获得个人信息
    public Observable<PerInfo.DataEntity> getPerInfo(int identity){
        return serviceApi.getPerInfo(identity)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //修改个人信息
    public Observable<String> modiPerInfo(String flag, String gender, String realname,
                                          String name, String username, String selfintro){
        return serviceApi.modiInfo(flag, gender, realname, selfintro)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }
}
