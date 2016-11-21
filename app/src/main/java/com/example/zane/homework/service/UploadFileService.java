package com.example.zane.homework.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.entity.FileDownUpload;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.event.PostWorkFinishEvent;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import rx.Subscriber;

/**
 * 老师发布作业，学生上传作业，再次上传作业
 * Created by Zane on 2016/11/21.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class UploadFileService extends IntentService {

    private final HomeWorkModel model = HomeWorkModel.getInstance();

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private static final int ID = 1;

    public UploadFileService() {
        super("UploadFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                                      .setSmallIcon(R.drawable.appicon)
                                      .setContentTitle("文件上传")
                                      .setContentText("文件上传中")
                                      .setAutoCancel(true);

        notificationManager.notify(ID, notificationBuilder.build());

        //发布作业
        if (intent.getStringExtra(ClazzDetailPostHomeWorkActivity.TYPE) != null) {
            String percentage = intent.getStringExtra(ClazzDetailPostHomeWorkActivity.PERCENTAGE);
            String deadline = intent.getStringExtra(ClazzDetailPostHomeWorkActivity.DEADLINE);
            String addtion = intent.getStringExtra(ClazzDetailPostHomeWorkActivity.ADDTION);
            String jid = intent.getStringExtra(ClazzDetailPostHomeWorkActivity.JID);
            File file = (File) intent.getSerializableExtra(ClazzDetailPostHomeWorkActivity.FILE);
            postHomeWork(percentage, deadline, addtion, jid, file);
        }
    }

    private void postHomeWork(String percentage, String deadline, String addtion, String jid, File file){
        model.declareWork(percentage, deadline, addtion, jid, file, (byteWritten, contentLength, done) -> {
            FileDownUpload upload = new FileDownUpload();
            upload.setCurrentFileSize(byteWritten);
            upload.setTotalFileSize(contentLength);
            int progress = (int) ((byteWritten * 100) / contentLength);
            upload.setProgress(progress);
            sendNotification(upload);
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                //不太清楚这里的问题是什么
                if (e instanceof IllegalStateException){
                    downloadCompleted();
                    EventBus.getDefault().post(new PostWorkFinishEvent());
                    JUtils.Toast("发布成功~");
                } else {
                    JUtils.Toast("发布失败" + e.getMessage());
                }
            }
            @Override
            public void onNext(String s) {
                downloadCompleted();
                EventBus.getDefault().post(new PostWorkFinishEvent());
                JUtils.Toast("发布成功~");
            }
        });
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
