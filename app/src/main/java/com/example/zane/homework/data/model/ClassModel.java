package com.example.zane.homework.data.model;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.SerClassInfo;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.bean.StuHaveCourse;
import com.example.zane.homework.data.bean.TeacherHavaClass;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.service.ClassService;

import rx.Observable;

/**
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class ClassModel {
    private Context context;
    private ClassService serviceApi;

    private ClassModel(){
        context = App.getInstance();
        serviceApi = CommonProvider.provideRetrofit()
                             .baseUrl(ClassService.BASE_URL)
                             .build()
                             .create(ClassService.class);
    }

    private static final class InstanceHolder{
        private final static ClassModel instance = new ClassModel();
    }

    public ClassModel getInstance(){
        return ClassModel.InstanceHolder.instance;
    }

    //创建班级
    public Observable<NoData> creatClass(String name, String description){
        return serviceApi.creatClass(name, description)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //修改班级信息
    public Observable<NoData> modiClass(String name, String description, String cid){
        return serviceApi.modiClass(name, description, cid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //学生查看加入的班级
    public Observable<StuHaveClass> stuClass(){
        return serviceApi.stuClass()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //老师申请进驻班级
    public Observable<NoData> teaAppClass(String cid, String sirstu, String course, String addtion){
        return serviceApi.teaAppClass(cid, sirstu, course, addtion)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //学生申请进驻班级
    public Observable<NoData> stuAppClass(String cid, String sirsourt){
        return serviceApi.stuAppClass(cid, sirsourt)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<NoData> okTeaApply(String cid, String tid, String course, String addtion){
        return serviceApi.applyTea(cid, tid, course, addtion)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<NoData> okStuApply(String cid, String sid){
        return serviceApi.applyStu(cid, sid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<TeacherHavaClass> teaHaveClass(){
        return serviceApi.teaHaveClass()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<StuHaveCourse> stuHaveCourse(){
        return serviceApi.stuHaveCourse()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<ShowApply> showApply(String cid){
        return serviceApi.showApply(cid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<ClassMemeber> classMemeber(String cid){
        return serviceApi.showClassMemeber(cid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<SerClassInfo> serClassInfo(String cid){
        return serviceApi.serClassInfo(cid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }
}
