package com.example.zane.homework.data.remote.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface DownLoadService {

    String BASE_URL = "http://115.159.113.116/index.php/Homework/downLoad/";

    //下载学生作业
    @GET("{attach}/{num}")
    @Streaming
    Observable<ResponseBody> downloadWork(@Path("attach") String attach, @Path("num") String num);

}
