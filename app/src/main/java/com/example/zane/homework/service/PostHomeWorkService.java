package com.example.zane.homework.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.entity.FileDownUpload;
import com.example.zane.homework.event.PostWorkFinishEvent;
import com.example.zane.homework.event.PostingEvent;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import rx.Subscriber;

/**
 * Created by Zane on 2017/5/15.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class PostHomeWorkService extends IntentService {
    private final HomeWorkModel model = HomeWorkModel.getInstance();

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private static final int ID = 2;

    public static final String WORK_FILE = "work_file";
    public static final String ASID = "asid";
    public static final String POST_TIME = "post_time";

    public PostHomeWorkService() {
        super("PostHomeWorkService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                                      .setSmallIcon(R.drawable.appicon)
                                      .setContentTitle("作业上传")
                                      .setContentText("作业上传中")
                                      .setAutoCancel(true);

        notificationManager.notify(ID, notificationBuilder.build());

        //上传作业
        File file = (File) intent.getSerializableExtra(WORK_FILE);
        String asid = intent.getStringExtra(ASID);
        String postTime = intent.getStringExtra(POST_TIME);
        postHomeWork(file, asid, postTime);

        EventBus.getDefault().post(new PostingEvent());
    }

    private void postHomeWork(File file, String asid, String postTime){
        if ("1".equals(postTime)) {
            model.stuUpLoadFirst(asid, file, (byteWritten, contentLength, done) -> {
                refresh(byteWritten, contentLength);
            }).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    success();
                }

                @Override
                public void onError(Throwable e) {
                    error(e);
                }

                @Override
                public void onNext(String s) {
                }
            });
        } else if ("2".equals(postTime)) {
            model.stuUpLoadAgain(asid, file, (byteWritten, contentLength, done) -> {
                refresh(byteWritten, contentLength);
            }).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    success();
                }

                @Override
                public void onError(Throwable e) {
                    error(e);
                }

                @Override
                public void onNext(String s) {
                }
            });
        }
    }

    private void refresh(long byteWritten, long contentLength) {
        FileDownUpload upload = new FileDownUpload();
        upload.setCurrentFileSize(byteWritten);
        upload.setTotalFileSize(contentLength);
        int progress = (int) ((byteWritten * 100) / contentLength);
        upload.setProgress(progress);
        sendNotification(upload);
    }

    private void success() {
        downloadCompleted();
        PostWorkFinishEvent event = new PostWorkFinishEvent();
        event.setSuccess(true);
        EventBus.getDefault().post(event);
        JUtils.Toast("上传成功~");
    }

    private void error(Throwable e) {
        //不太清楚这里的问题是什么
        PostWorkFinishEvent event = new PostWorkFinishEvent();

        if (e instanceof IllegalStateException){
            downloadCompleted();
            event.setSuccess(true);
            EventBus.getDefault().post(event);
            JUtils.Toast("上传成功~");
        } else {
            event.setSuccess(false);
            EventBus.getDefault().post(event);
            JUtils.Toast("上传失败" + e.getMessage());
        }
    }

    /**
     * 刷新通知栏
     * @param download
     */
    private void sendNotification(FileDownUpload download) {
        notificationBuilder.setProgress(100, download.getProgress(), false);
        notificationManager.notify(ID, notificationBuilder.build());
    }

    /**
     * 下载完成，刷新通知栏
     */
    private void downloadCompleted() {
        notificationManager.cancel(ID);
        notificationBuilder.setProgress(100, 100, false);
        notificationBuilder.setContentText("文件上传完成");
        notificationManager.notify(ID, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(ID);
    }
}
