package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.data.bean.NoData;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface MessageService {

    String BASE_URL = "http://115.159.113.116/index.php/Message/";

    //------------------------------Message-------------------------

    //发布消息
    @POST("postMessage")
    @FormUrlEncoded
    Observable<String> postMessage(@Field("type") String type,
                                   @Field("id") String id,
                                   @Field("content") String content);

    //学生查看消息列表(分页加载)
    @GET("getMess/{num}")
    @Headers("Cache-Control: no-cache")
    Observable<List<GetMessage.DataEntity>> getStuMessage(@Path("num") int num);

    //老师查看消息列表(分页加载)
    @GET("getMesste/{num}")
    @Headers("Cache-Control: no-cache")
    Observable<List<GetMessage.DataEntity>> getTeaMessage(@Path("num") int num);

    //查看消息的具体内容
    @GET("getDetail/{num}")
    @Headers("Cache-Control:max-age=86400")
    Observable<GetDetailMessage.DataEntity> getDetailMessage(@Path("num") int num);
}
