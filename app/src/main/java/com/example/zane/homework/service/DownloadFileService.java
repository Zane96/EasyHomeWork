package com.example.zane.homework.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;


import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.data.bean.FileDownload;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.download.DownloadProgressListener;
import com.example.zane.homework.utils.FileUtils;

import java.io.File;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * 下载学生作业的服务
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class DownloadFileService extends IntentService{

    private HomeWorkModel model = HomeWorkModel.getInstance();

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;

    private static final int ID = 0;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadFileService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.appicon)
                .setContentTitle("作业下载")
                .setContentText("作业下载中")
                .setAutoCancel(true);

        notificationManager.notify(ID, notificationBuilder.build());

        download(intent.getStringExtra(WorkJudgePresenter.DOWNLOAD_URL));
    }

    private void download(String fileUrl){

        File outputFile = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        model.downloadWork(fileUrl, ((bytesRead, contentLength, done) -> {
            FileDownload download = new FileDownload();
            download.setTotalFileSize(contentLength);
            download.setCurrentFileSize(bytesRead);
            int progress = (int) ((bytesRead * 100) / contentLength);
            download.setProgress(progress);
            sendNotification(download);
        })).subscribe(responseBody -> {
            downloadCompleted();
            FileUtils.writeFile(responseBody.byteStream(), outputFile);
        });
    }

    /**
     * 下载完成，刷新通知栏
     */
    private void downloadCompleted() {
        notificationManager.cancel(ID);
        notificationBuilder.setProgress(100, 100, false);
        notificationBuilder.setContentText("作业下载完成");
        notificationManager.notify(ID, notificationBuilder.build());
    }

    /**
     * 刷新通知栏
     * @param download
     */
    private void sendNotification(FileDownload download){
        notificationBuilder.setProgress(100, download.getProgress(), false);
        notificationManager.notify(ID, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(ID);
    }
}
