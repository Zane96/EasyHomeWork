package com.example.zane.homework.data.remote.upload;

import com.example.zane.homework.data.remote.service.UpLoadService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OkhttpClient这里不能单例
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class UploadCommonProvider {

    private UploadCommonProvider(){}

    private static Retrofit.Builder retrofitBuilder;

    private static final class SingltonHolder{
        private static final UploadCommonProvider instance = new UploadCommonProvider();
    }

    public static final UploadCommonProvider getInstance(){
        return SingltonHolder.instance;
    }

    private static OkHttpClient.Builder provideOkhttpClintBuilder(){
        return new OkHttpClient.Builder()
                                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .connectTimeout(15, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true);

    }

    private static Retrofit.Builder providerRetrofitBuilder(){
        if (retrofitBuilder == null){
            retrofitBuilder = new Retrofit.Builder()
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .baseUrl(UpLoadService.BASE_URL);
        }
        return retrofitBuilder;
    }

    public static UpLoadService getServiceApi(UploadProgressListener listener){
        OkHttpClient client = provideOkhttpClintBuilder()
                                      .addInterceptor(new UpLoadProgressInterceptor(listener))
                                      .build();
        return providerRetrofitBuilder().client(client).build().create(UpLoadService.class);
    }
}
