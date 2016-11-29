package com.example.zane.homework.data.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.GetHoWork;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.GetStatistc;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.bean.NoDueHomeWork;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.TransForm;
import com.example.zane.homework.data.remote.download.DownloadProgressInterceptor;
import com.example.zane.homework.data.remote.download.DownloadProgressListener;
import com.example.zane.homework.data.remote.service.DownLoadService;
import com.example.zane.homework.data.remote.service.HomeWorkService;
import com.example.zane.homework.data.remote.upload.UploadCommonProvider;
import com.example.zane.homework.data.remote.upload.UploadProgressListener;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
        serviceApi = CommonProvider.getRetrofitBuilder()
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
        return TransForm.transform(serviceApi.showNoDueWork());
    }

    public Observable<List<HoPerson.DataEntity>> showHoPerson(String asid, String sid){
        return TransForm.transform(serviceApi.showHoPerson(asid, sid));
    }

    public Observable<String> judgeHomeWork(String grade, String addtion, String asid, String sid){
        return TransForm.transform(serviceApi.judgeHomeWork(grade, addtion, asid, sid));
    }

    public Observable<String> declareWork(String percentage, String deadline, String addtion, String jid, File file, UploadProgressListener listener){

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody percentageBody = RequestBody.create(MediaType.parse("multipart/form-data"), percentage);
        RequestBody deadlineBody = RequestBody.create(MediaType.parse("multipart/form-data"), deadline);
        RequestBody addtionBody = RequestBody.create(MediaType.parse("multipart/form-data"), addtion);
        RequestBody jidBody = RequestBody.create(MediaType.parse("multipart/form-data"), jid);

        return UploadCommonProvider.getServiceApi(listener).declareWork(percentageBody, deadlineBody, addtionBody, jidBody, body)
                       .compose(new SchedulerTransform<>());
    }

    public Observable<GetStatistc.DataEntity> getStatistc(String jid, String sid){
        return TransForm.transform(serviceApi.getStatistc(jid, sid));
    }

    public Observable<List<GetNoDueWork.DataEntity>> getNoDueWork(){
        return TransForm.transform(serviceApi.getNoDueWork());
    }

    public Observable<List<GetHoWork.DataEntity>> getHoWork(String asid){
        return TransForm.transform(serviceApi.getHoWork(asid));
    }

    public Observable<String> stuUpLoadFirst(String asid, File file, UploadProgressListener listener){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return TransForm.transform(UploadCommonProvider.getServiceApi(listener).stuUpload(asid, body));
    }

    public Observable<String> stuUpLoadAgain(String asid, File file, UploadProgressListener listener){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return TransForm.transform(UploadCommonProvider.getServiceApi(listener).stuUploadAgain(asid, body));
    }

    //老师下载学生的作业
    public Observable<ResponseBody> downloadWork(String attachment, DownloadProgressListener listener){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new DownloadProgressInterceptor(listener))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(DownLoadService.BASE_URL)
                .build();

        return retrofit.create(DownLoadService.class)
                       .downloadWork(attachment, "2")
                       .compose(new ErrorTransForm<>())
                       .compose(new SchedulerTransform<>());

    }
}
