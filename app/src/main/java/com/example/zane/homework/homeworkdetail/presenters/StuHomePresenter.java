package com.example.zane.homework.homeworkdetail.presenters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.data.bean.GetHoWork;
import com.example.zane.homework.data.db.DownloadWorkDBManager;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.event.DownloadFinishEvent;
import com.example.zane.homework.event.DownloadingEvent;
import com.example.zane.homework.event.PostWorkFinishEvent;
import com.example.zane.homework.event.PostingEvent;
import com.example.zane.homework.homeworkdetail.view.StuHomeView;
import com.example.zane.homework.service.DownloadFileService;
import com.example.zane.homework.service.PostHomeWorkService;
import com.example.zane.homework.utils.FileUtils;
import com.example.zane.homework.utils.Uri2File;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_NUM;
import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_URL;
import static com.example.zane.homework.utils.FileUtils.OPEN_FFILE;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuHomePresenter extends BaseActivity<StuHomeView>{

    private String attachment;
    private String homework;
    private String addtion;
    private String deadline;
    private String asid;

    public static final String ASID = "asid";
    private HomeWorkModel homeWorkModel = HomeWorkModel.getInstance();

    @Override
    public Class<StuHomeView> getRootViewClass() {
        return StuHomeView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        EventBus.getDefault().register(this);
        asid = getIntent().getStringExtra(ASID);
        getHomeWorkData(asid);
    }

    /**
     * 获得作业的信息
     * @param asid
     */
    private void getHomeWorkData(String asid) {
        homeWorkModel.getHoWork(asid).subscribe(new FinalSubscriber<GetHoWork.DataEntity>(this, data -> {
            GetHoWork.DataEntity stuData = (GetHoWork.DataEntity) data;
            attachment = stuData.getAsinattachment();
            homework = stuData.getHoattachment();
            addtion = stuData.getAddtion();
            deadline = stuData.getDeadline();

            String attchmentDB = DownloadWorkDBManager.getInstance().queryAttachment(asid, "2");
            v.initData(homework, addtion, deadline, attchmentDB);
        }));
    }

    /**
     * 下载作业附件
     */
    public void downloadWork() {
        Intent intent = new Intent(this, DownloadFileService.class);
        intent.putExtra(DOWNLOAD_URL, attachment);
        intent.putExtra(DOWNLOAD_NUM, "2");
        startService(intent);
    }

    public void openFile() {
        Intent intent = FileUtils.openFile(DownloadWorkDBManager.getInstance().queryFilePath(asid, "2"));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case OPEN_FFILE:
                    String filePath = Uri2File.getFileAbsolutePath(StuHomePresenter.this, Uri.parse(Uri.decode(data.getDataString())));
                    Log.i("testUpload", filePath);
                    File uploadFile = new File(filePath);
                    Intent intent = new Intent(StuHomePresenter.this, PostHomeWorkService.class);
                    intent.putExtra(PostHomeWorkService.POST_TIME, v.getPostTime());
                    intent.putExtra(PostHomeWorkService.WORK_FILE, uploadFile);
                    intent.putExtra(PostHomeWorkService.ASID, asid);
                    startService(intent);
                    break;
            }
        }
    }

    /**
     * 接收作业下载完成的事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DownloadFinish(DownloadFinishEvent event){
        Log.i("testDB", "insert");
        DownloadWorkDBManager.getInstance().insert("", asid, attachment, event.getFilePath(), "2");
        v.openFile();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UploadFinish(PostWorkFinishEvent event) {
        if (event.isSuccess()) {
            v.uploadSuccess();
        } else {
            v.uploadFailed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Uploading(PostingEvent event) {
        v.uploading();
    }

    /**
     * 下载中的事件接收
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Downloading(DownloadingEvent event){
        v.downloading();
    }

    @Override
    public void inDestory() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
