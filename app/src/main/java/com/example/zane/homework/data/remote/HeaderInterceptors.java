package com.example.zane.homework.data.remote;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

import android.util.Log;

import com.example.zane.homework.data.sp.MySharedPre;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ServiceConfigurationError;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Zane on 16/3/14.
 * 拦截,http缓存
 */
public class HeaderInterceptors implements Interceptor {

    private static final String TAG = "HeaderInterceptors";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Response originalResponse;

        originalResponse = chain.proceed(request);

        MediaType contentType = originalResponse.body().contentType();
        //先解析一遍json数据，根据status和message去手动改状态码和描述
        String originalContent = originalResponse.body().string();
        Log.i("testBody", originalContent);

        int code ;
        String message ;
        String body ;

        JSONObject wrapper = null;
        try {
            wrapper = new JSONObject(originalContent);
            message = wrapper.getString("message");
            code = wrapper.getInt("status");
            //剥离外层数据
            body = wrapper.getString("data");

        } catch (JSONException e) {
            throw new ServiceConfigurationError("解析错误："+e.getLocalizedMessage());
        }

        //更改响应头,根据Request的要求来强制添加不同的缓存策略
        String cacheControl = request.cacheControl().toString();
        return originalResponse.newBuilder()
                                           .code(code)
                                           .message(message)
                                           .body(ResponseBody.create(contentType, body))
                                           .header("Cache-Control", cacheControl)
                                           .removeHeader("Pragma")
                                           .build();
    }
}
