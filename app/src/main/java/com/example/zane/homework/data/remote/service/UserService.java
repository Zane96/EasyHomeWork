package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.PerInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/11/3.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface UserService {

    String BASE_URL = "http://115.159.113.116/index.php/User/";

    @GET("perInfo/{identity}")
    Observable<PerInfo.DataEntity> getPerInfo(@Path("identity") int identity);

    @POST("modiSelf")
    @FormUrlEncoded
    Observable<String> modiInfo(@Field("flag") String flag,
                                @Field("gender") String gender,
                                @Field("realname") String realname,
                                @Field("selfintro") String selfintro);

}
