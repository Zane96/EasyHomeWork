package com.example.zane.homework.data.remote;

import android.util.Log;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.FileUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 *
 * 提供基础的OkHttpClient.Builder和Retrofit.Builder
 */

public class CommonProvider {

    private CommonProvider(){}
    public static PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache()
                                                                                 , new SharedPrefsCookiePersistor(App.getInstance()));
    private static final Retrofit.Builder RetrofitBuilderInstance = provideRetrofit();

    private static OkHttpClient provideOkHttpClient(){
        int s = 1;
        double ss = s;
        //添加body日志打印，http，stetho调试的拦截器，管理cookie
        return new OkHttpClient.Builder()
                       .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                       .addNetworkInterceptor(new HeaderInterceptors())
                       .addNetworkInterceptor(new StethoInterceptor())
                       .cookieJar(cookieJar)
                       .cache(new Cache(FileUtils.getDiskCacheDir("homeworkcache"), 1024 * 1024 * 10))
                       .build();
    }

    private static Retrofit.Builder provideRetrofit(){
        return new Retrofit.Builder()
                       .addConverterFactory(GsonConverterFactory.create())
                       .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                       .client(provideOkHttpClient());
    }

    public static Retrofit.Builder getRetrofitBuilder(){
        return RetrofitBuilderInstance;
    }

}
