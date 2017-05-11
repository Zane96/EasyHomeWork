package com.example.zane.homework.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.entity.FileDownUpload;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.event.DownloadFinishEvent;
import com.example.zane.homework.event.DownloadingEvent;
import com.example.zane.homework.utils.FileUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * 下载学生作业的服务
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class DownloadFileService extends IntentService{

    private final HomeWorkModel model = HomeWorkModel.getInstance();

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;

    public static final String DOWNLOAD_URL = "download_url";
    public static final String DOWNLOAD_NUM = "download_num";

    private static final int ID = 0;

    public DownloadFileService() {
        super("DownloadFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.appicon)
                .setContentTitle("下载")
                .setContentText("下载中")
                .setAutoCancel(true);

        notificationManager.notify(ID, notificationBuilder.build());

        download(intent.getStringExtra(DOWNLOAD_URL), intent.getStringExtra(DOWNLOAD_NUM));
    }

    private void download(String attachment, String num){

        EventBus.getDefault().post(new DownloadingEvent());

        //外存的公有存储空间(doc)
        File outputFile = getFile(attachment);

        model.downloadWork(attachment, num, ((bytesRead, contentLength, done) -> {
            FileDownUpload download = new FileDownUpload();
            download.setTotalFileSize(contentLength);
            download.setCurrentFileSize(bytesRead);
            int progress = (int) ((bytesRead * 100) / contentLength);
            download.setProgress(progress);
            sendNotification(download);
        })).subscribe(responseBody -> {
            downloadCompleted();
            FileUtils.writeFile(responseBody.byteStream(), outputFile);

            //发布下载作业成功的事件
            DownloadFinishEvent event = new DownloadFinishEvent();
            event.setFilePath(outputFile.getAbsolutePath());
            EventBus.getDefault().post(event);
        });


    }

    @NonNull
    private File getFile(String attachment) {
        String end = attachment.substring(attachment.lastIndexOf(".") + 1, attachment.length()).toLowerCase();
        String fileAttachment = attachment;
        if (end.equals("docx")){
            fileAttachment = attachment.substring(0, attachment.length() - 1);
        }
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileAttachment);
    }

    /**
     * 下载完成，刷新通知栏
     */
    private void downloadCompleted() {
        notificationManager.cancel(ID);
        notificationBuilder.setProgress(100, 100, false);
        notificationBuilder.setContentText("下载完成");
        notificationManager.notify(ID, notificationBuilder.build());
    }

    /**
     * 刷新通知栏
     * @param download
     */
    private void sendNotification(FileDownUpload download){
        notificationBuilder.setProgress(100, download.getProgress(), false);
        notificationManager.notify(ID, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(ID);
    }
}
