package com.example.zane.homework.data.remote.upload;

/**
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface UploadProgressListener {
    /**
     * 文件上传的progress回调接口方法
     * @param byteWritten
     * @param contentLength
     * @param done
     */
    void update(long byteWritten, long contentLength, boolean done);
}
