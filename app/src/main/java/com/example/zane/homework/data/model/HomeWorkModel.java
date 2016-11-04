package com.example.zane.homework.data.model;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.GetHoWork;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.GetStatistc;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.bean.NoDueHomeWork;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.service.HomeWorkService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class HomeWorkModel {
    private Context context;
    private HomeWorkService serviceApi;

    private HomeWorkModel(){
        context = App.getInstance();
        serviceApi = CommonProvider.provideRetrofit()
                             .baseUrl(HomeWorkService.BASE_URL)
                             .build()
                             .create(HomeWorkService.class);
    }

    private static final class InstanceHolder{
        private final static HomeWorkModel instance = new HomeWorkModel();
    }

    public static HomeWorkModel getInstance(){
        return HomeWorkModel.InstanceHolder.instance;
    }

    public Observable<List<NoDueHomeWork.DataEntity>> showNoDueWork(){
        return serviceApi.showNoDueWork()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<List<HoPerson.DataEntity>> showHoPerson(String asid, String sid){
        return serviceApi.showHoPerson(asid, sid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<String> judgeHomeWork(String grade, String addtion, String asid, String sid){
        return serviceApi.judgeHomeWork(grade, addtion, asid, sid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<String> declareWork(String percentage, String deadline, String addtion, String jid, File file){

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return serviceApi.declareWork(percentage, deadline, addtion, jid, body)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<GetStatistc.DataEntity> getStatistc(String jid, String sid){
        return serviceApi.getStatistc(jid, sid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<List<GetNoDueWork.DataEntity>> getNoDueWork(){
        return serviceApi.getNoDueWork()
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<List<GetHoWork.DataEntity>> getHoWork(String asid){
        return serviceApi.getHoWork(asid)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<String> stuUpLoadFirst(String asid, File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return serviceApi.stuUpload(asid, body)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    public Observable<String> stuUpLoadAgain(String asid, File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return serviceApi.stuUploadAgain(asid, body)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }
}
