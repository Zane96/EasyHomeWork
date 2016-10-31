package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.NoDueHomeWork;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface HomeWorkService {

    String BASE_URL = "http://115.159.113.116/index.php/Homework/";

    //---------------------------HomeWork------------------------------

    //老师查看自己布置的未过期的作业列表
    @GET("noDueHo")
    Observable<NoDueHomeWork> showNoDueWork();

    //查看学生的完成作业情况
    @GET("hoPerson/{asid}/{sid}")
    Observable<HoPerson> showHoPerson(@Path("asid") String asid,
                                      @Path("sid") String sid);

    //老师批改作业
    @POST("hoJudge")
    @FormUrlEncoded
    Observable<NoData> judgeHomeWork(@Field("grade") String grade,
                                     @Field("addtion") String addtion,
                                     @Field("asid") String asid,
                                     @Field("sid") String sid);

    //老师发布作业
    @POST("sirUpload")
    @FormUrlEncoded
    @Multipart
    Observable<NoData> declareWork(@Field("percentage") String percentage,
                                   @Field("addtion") String addtion,
                                   @Field("jid") String jid,
                                   @Part MultipartBody.Part uploadFile);

}
