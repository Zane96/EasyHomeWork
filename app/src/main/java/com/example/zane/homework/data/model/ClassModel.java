package com.example.zane.homework.data.model;

import android.content.Context;
import android.util.Log;

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
import com.example.zane.homework.data.remote.TransForm;
import com.example.zane.homework.data.remote.service.ClassService;

import java.util.List;

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
        serviceApi = CommonProvider.getRetrofitBuilder()
                             .baseUrl(ClassService.BASE_URL)
                             .build()
                             .create(ClassService.class);
    }

    private static final class InstanceHolder{
        private final static ClassModel instance = new ClassModel();
    }

    public static ClassModel getInstance(){
        return ClassModel.InstanceHolder.instance;
    }

    //创建班级
    public Observable<String> creatClass(String name, String description){
        return TransForm.transform(serviceApi.creatClass(name, description));
    }

    //修改班级信息
    public Observable<String> modiClass(String name, String description, String cid){
        return TransForm.transform(serviceApi.modiClass(name, description, cid));
    }

    //学生查看加入的班级
    public Observable<StuHaveClass.DataEntity> stuClass(){
        return TransForm.transform(serviceApi.stuClass());
    }

    //老师申请进驻班级
    public Observable<String> teaAppClass(String cid, String sirstu, String course, String addtion){
        return TransForm.transform(serviceApi.teaAppClass(cid, sirstu, course, addtion));
    }

    //学生申请进驻班级
    public Observable<String> stuAppClass(String cid, String sirsourt){
        return TransForm.transform(serviceApi.stuAppClass(cid, sirsourt));
    }

    public Observable<String> okTeaApply(String cid, String tid, String course, String addtion){
        return TransForm.transform(serviceApi.applyTea(cid, tid, course, addtion));
    }

    public Observable<String> okStuApply(String cid, String sid){
        return TransForm.transform(serviceApi.applyStu(cid, sid));
    }

    public Observable<List<TeacherHavaClass.DataEntity>> teaHaveClass(){
        return TransForm.transform(serviceApi.teaHaveClass());
    }

    public Observable<List<StuHaveCourse.DataEntity>> stuHaveCourse(){
        return TransForm.transform(serviceApi.stuHaveCourse());
    }

    public Observable<ShowApply.DataEntity> showApply(String cid){
        return TransForm.transform(serviceApi.showApply(cid));
    }

    public Observable<List<ClassMemeber.DataEntity>> classMemeber(String cid){
        return TransForm.transform(serviceApi.showClassMemeber(cid));
    }

    public Observable<SerClassInfo.DataEntity> serClassInfo(String cid){
        return TransForm.transform(serviceApi.serClassInfo(cid));
    }
}
