package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.GetHoWork;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.GetStatistc;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.NoDueHomeWork;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
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
    Observable<List<NoDueHomeWork.DataEntity>> showNoDueWork();

    //查看学生的完成作业情况
    @GET("hoPerson/{asid}/{sid}")
    Observable<List<HoPerson.DataEntity>> showHoPerson(@Path("asid") String asid,
                                                       @Path("sid") String sid);

    //老师批改作业
    @POST("hoJudge")
    @FormUrlEncoded
    Observable<String> judgeHomeWork(@Field("grade") String grade,
                                     @Field("addtion") String addtion,
                                     @Field("asid") String asid,
                                     @Field("sid") String sid);

    //老师发布作业
    @POST("sirUpload")
    @FormUrlEncoded
    @Multipart
    Observable<String> declareWork(@Field("percentage") String percentage,
                                   @Field("deadline") String deadLine,
                                   @Field("addtion") String addtion,
                                   @Field("jid") String jid,
                                   @Part MultipartBody.Part uploadFile);

    //某个学生的作业得分情况
    @GET("getStatistc/{jid}/{sid}")
    Observable<GetStatistc.DataEntity> getStatistc(@Path("jid") String jid,
                                        @Path("sid") String sid);

    //学生查看未过期作业列表
    @GET("getNodue")
    Observable<List<GetNoDueWork.DataEntity>> getNoDueWork();

    //学生查看自己提交的某份作业详情
    @GET("getHo/{asid}")
    Observable<List<GetHoWork.DataEntity>> getHoWork(@Path("asid") String asid);

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
