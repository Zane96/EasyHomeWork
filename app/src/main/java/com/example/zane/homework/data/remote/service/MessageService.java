package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.data.bean.NoData;

import retrofit2.http.Field;
import retrofit2.http.GET;
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
    Observable<NoData> postMessage(@Field("type") String type,
                                   @Field("id") String id,
                                   @Field("content") String content);

    //查看消息列表(分页加载)
    @GET("getMess/{num}")
    Observable<GetMessage> getMessage(@Path("num") int num);


    //查看消息的具体内容
    @GET("getDetail/{num}")
    Observable<GetDetailMessage> getDetailMessage(@Path("num") int num);
}
