package com.example.zane.homework.data.remote.upload;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 上传文件的请求头拦截
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class UpLoadProgressInterceptor implements Interceptor {

    private UploadProgressListener listener;

    public UpLoadProgressInterceptor(UploadProgressListener listener) {
        this.listener = listener;
    }

    //修改原始的request
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request origin = chain.request();

        Request request = origin.newBuilder()
                .method(origin.method(), new UpLoadRequestBody(listener, origin.body()))
                .build();

        return chain.proceed(request);
    }
}
