package com.example.zane.homework.data.remote.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class DownloadProgressInterceptor implements Interceptor{

    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener){
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        //传入原始的response body以及回调接口构建一个新的response body，并替换原来的body
        return originalResponse.newBuilder()
                       .body(new DownLoadResponseBody(originalResponse.body(), listener))
                       .build();
    }
}
