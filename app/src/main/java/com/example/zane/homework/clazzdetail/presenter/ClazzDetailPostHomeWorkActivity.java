package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDetailPostHomeWorkView;
import com.example.zane.homework.service.PostHomeWorkService;

import java.lang.ref.WeakReference;

import static com.example.zane.homework.utils.FileUtils.OPEN_FFILE;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostHomeWorkActivity extends BaseActivityPresenter<ClazzDetailPostHomeWorkView>{

    private PostHomeWorkService postService;
    private ProgressHandler handler;

    @Override
    public Class<ClazzDetailPostHomeWorkView> getRootViewClass() {
        return ClazzDetailPostHomeWorkView.class;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, final IBinder service) {
            PostHomeWorkService.MyBinder binder = (PostHomeWorkService.MyBinder) service;
            ClazzDetailPostHomeWorkActivity.this.postService = binder.getServiceContext();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                    while (postService.getPercent() < 100){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Message message1 = new Message();
                    message1.what = 1;
                    handler.sendMessage(message1);
                    ClazzDetailPostHomeWorkActivity.this.postService.stopSelf();
                }
            }).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        handler = new ProgressHandler(this);
    }

    @Override
    public void inDestory() {
        handler.removeMessages(1);
        handler.removeMessages(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case OPEN_FFILE:
                    String filePath = Uri.decode(data.getDataString());
                    filePath = filePath.substring(7, filePath.length());
                    //开启服务上传文件
                    Intent intent = new Intent(ClazzDetailPostHomeWorkActivity.this, PostHomeWorkService.class);
                    startService(intent);
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                    break;
            }
        }
    }

    @Override
    public Activity getContext() {
        return this;
    }

    private final static class ProgressHandler extends Handler {
        private WeakReference<ClazzDetailPostHomeWorkActivity> reference;
        public ProgressHandler(ClazzDetailPostHomeWorkActivity activity){
            reference = new WeakReference<ClazzDetailPostHomeWorkActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null){
                switch (msg.what){
                    case 0:
                        reference.get().v.loading();
                        break;
                    case 1:
                        reference.get().v.loaded();
                        break;
                }
            }
        }
    }
}
