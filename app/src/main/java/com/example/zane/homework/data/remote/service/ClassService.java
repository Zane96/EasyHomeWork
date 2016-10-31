package com.example.zane.homework.data.remote.service;

import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.SerClassInfo;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.bean.StuHaveCourse;
import com.example.zane.homework.data.bean.TeacherHavaClass;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface ClassService{

    String BASE_URL = "http://115.159.113.116/index.php/Clazz/";

    //-------------------------Class---------------------------------

    //学生创建班级
    @POST("creaClass")
    @FormUrlEncoded
    Observable<NoData> creatClass(@Field("name") String name,
                                  @Field("description") String description);

    //学生修改班级信息
    @POST("modiClass")
    @FormUrlEncoded
    Observable<NoData> modiClass(@Field("name") String name,
                                 @Field("description") String description,
                                 @Field("cid") String cid);

    //学生查看加入的班级
    @GET("inClass")
    Observable<StuHaveClass> stuClass();

    //老师申请加入班级
    @POST("appClass")
    @FormUrlEncoded
    Observable<NoData> teaAppClass(@Field("cid") String cid,
                                   @Field("sirsortu") String sirsortu,
                                   @Field("course") String course,
                                   @Field("addtion") String addtion);

    //学生申请加入班级
    @POST("appClass")
    @FormUrlEncoded
    Observable<NoData> stuAppClass(@Field("cid") String cid,
                                   @Field("sirsortu") String sirsortu);

    //同意老师加入班级
    @POST("okApply/1")
    @FormUrlEncoded
    Observable<NoData> applyTea(@Field("cid") String cid,
                                @Field("tid") String tid,
                                @Field("course") String course,
                                @Field("addtion") String addtion);

    //同意学生加入班级
    @POST("okApply/2")
    @FormUrlEncoded
    Observable<NoData> applyStu(@Field("cid") String cid,
                                @Field("sid") String sid);

    //老师查看已加入的班级
    @POST("haveClass")
    Observable<TeacherHavaClass> teaHaveClass();

    //学生查看已加入课程
    @POST("inCourse")
    Observable<StuHaveCourse> stuHaveCourse();

    //查看某个班的申请信息（管理员）
    @GET("showApply/{cid}")
    Observable<ShowApply> showApply(@Path("cid") String cid);

    //查看某个班的成员信息
    @GET("oneClass/{cid}")
    Observable<ClassMemeber> showClassMemeber(@Path("cid") String cid);

    //搜索某个班级的班级信息
    @GET("serClass/{cid}")
    Observable<SerClassInfo> serClassInfo(@Path("cid") String cid);
}
