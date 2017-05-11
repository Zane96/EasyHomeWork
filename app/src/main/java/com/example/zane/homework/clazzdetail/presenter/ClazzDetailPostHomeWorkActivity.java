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
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDetailActivityView;
import com.example.zane.homework.clazzdetail.view.ClazzDetailPostHomeWorkView;
import com.example.zane.homework.event.PostWorkFinishEvent;
import com.example.zane.homework.event.UpLoadFileEvent;
import com.example.zane.homework.service.PostHomeWorkService;
import com.example.zane.homework.service.UploadFileService;
import com.example.zane.homework.utils.Uri2File;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import static com.example.zane.homework.utils.FileUtils.OPEN_FFILE;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostHomeWorkActivity extends BaseActivityPresenter<ClazzDetailPostHomeWorkView>{

    private PostHomeWorkService postService;
    public static final String TYPE = "postHomeWork";
    public static final String PERCENTAGE = "percentage";
    public static final String DEADLINE = "deadline";
    public static final String ADDTION = "addtion";
    public static final String JID = "jid";
    public static final String FILE  = "file";

    private File uploadFile = null;
    private String jid;

    @Override
    public Class<ClazzDetailPostHomeWorkView> getRootViewClass() {
        return ClazzDetailPostHomeWorkView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        EventBus.getDefault().register(this);
        v.init();
        jid = getIntent().getStringExtra(ClazzDetailActivityView.JID);
    }

    @Override
    public void inDestory() {
        EventBus.getDefault().unregister(this);
    }

    //开启服务进行作业的发布
    public void startPostWork(String percentage, String deadline, String addtion){
        Intent intent = new Intent(ClazzDetailPostHomeWorkActivity.this, UploadFileService.class);
        intent.putExtra(TYPE, "posthomework");
        intent.putExtra(PERCENTAGE, percentage);
        intent.putExtra(DEADLINE, deadline);
        intent.putExtra(ADDTION, addtion);
        intent.putExtra(JID, jid);
        intent.putExtra(FILE, uploadFile);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case OPEN_FFILE:
                    String filePath = Uri2File.getFileAbsolutePath(ClazzDetailPostHomeWorkActivity.this, Uri.parse(Uri.decode(data.getDataString())));
                    uploadFile = new File(filePath);
                    Log.i("ClazzDetailPost", filePath+"");
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void uploadFinish(PostWorkFinishEvent event){
        v.loaded();
    }

    @Override
    public Activity getContext() {
        return this;
    }


}
