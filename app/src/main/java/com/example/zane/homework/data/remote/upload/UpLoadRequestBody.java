package com.example.zane.homework.data.remote.upload;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * 类似包装
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class UpLoadRequestBody extends RequestBody{

    private RequestBody requestBody;
    private UploadProgressListener listener;
    private BufferedSink bufferedSink;

    public UpLoadRequestBody(UploadProgressListener listener, RequestBody requestBody) {
        this.listener = listener;
        this.requestBody = requestBody;
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null){
            //buffer包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        requestBody.writeTo(bufferedSink);
        //清除缓存
        bufferedSink.flush();
    }

    private Sink sink(Sink sink){
        return new ForwardingSink(sink) {
            //当前写入字节
            long byteWritten = 0L;
            //总的长度字节
            long contentLength = 0L;
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                byteWritten += byteCount;
                if (listener != null) {
                    listener.update(byteWritten, contentLength, byteWritten == contentLength);
                }
            }
        };
    }
}
