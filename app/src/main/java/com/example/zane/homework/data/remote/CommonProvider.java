package com.example.zane.homework.data.remote;

import android.util.Log;

import com.example.zane.homework.app.App;
import com.example.zane.homework.utils.FileUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

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

    /**
     * 统一持久化管理Cookie
     */
    public static final ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache()
                                                                  , new SharedPrefsCookiePersistor(App.getInstance()));

    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private static final Cache cache = new Cache(FileUtils.getDiskCacheDir("homeworkcache"), 1024 * 1024 * 10);
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static final HeaderInterceptors headerInerceptors = new HeaderInterceptors();
    private static final StethoInterceptor stethoInterceptor = new StethoInterceptor();

    static {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient provideOkHttpClient(){
        //添加body日志打印，http，stetho调试的拦截器，管理cookie
        return builder
                .addInterceptor(interceptor)
                .addNetworkInterceptor(headerInerceptors)
                .addNetworkInterceptor(stethoInterceptor)
                .cookieJar(cookieJar)
                .cache(cache)
                .build();
    }

    public static Retrofit.Builder provideRetrofit(){
        Log.i("commonprovide", headerInerceptors+"");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideOkHttpClient());
    }

}
