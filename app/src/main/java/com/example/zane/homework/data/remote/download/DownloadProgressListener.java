package com.example.zane.homework.data.remote.download;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface DownloadProgressListener {
    /**
     * 文件下载的监听回调
     * @param bytesRead 已经下载了多少
     * @param contentLength 总共多少
     * @param done
     */
    void update(long bytesRead, long contentLength, boolean done);
}
