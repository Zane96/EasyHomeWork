package com.example.zane.homework.data.remote.service;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 上传文件
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface UpLoadService {

    String BASE_URL = "http://115.159.113.116/index.php/Homework/";

    //---------------------------------------------------------------------------------

    //老师发布作业
    @POST("sirUpload")
    @Multipart
    Observable<String> declareWork(@Part("percentage") RequestBody percentage,
                                   @Part("deadline") RequestBody deadLine,
                                   @Part("addtion") RequestBody addtion,
                                   @Part("jid") RequestBody jid,
                                   @Part MultipartBody.Part uploadFile);

    //提交作业
    @POST("stuUpload/1")
    @FormUrlEncoded
    @Multipart
    Observable<String> stuUpload(@Field("asid") String asid,
                                 @Part MultipartBody.Part file);

    //再次提交作业
    @POST("stuUpload/2")
    @FormUrlEncoded
    @Multipart
    Observable<String> stuUploadAgain(@Field("asid") String asid,
                                      @Part MultipartBody.Part file);
}
