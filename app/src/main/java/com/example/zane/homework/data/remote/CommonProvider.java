package com.example.zane.homework.data.remote;

import com.example.zane.homework.app.App;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

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

    /**
     * 统一持久化管理Cookie
     */
    private static final ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache()
                                                                  , new SharedPrefsCookiePersistor(App.getInstance()));

    private static OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(cookieJar);

    public static OkHttpClient.Builder provideOkHttpClientBuilder(){
        //添加body日志打印，http，stetho调试的拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return builder
                .addInterceptor(interceptor)
                .addInterceptor(new HeaderInterceptors());
    }

    public static Retrofit.Builder provideRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

}
