package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.clazzdetail.view.WorkJudgeActivityView;
import com.example.zane.homework.data.db.DownloadWorkDBManager;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.remote.service.DownLoadService;
import com.example.zane.homework.event.DownloadFinishEvent;
import com.example.zane.homework.event.DownloadingEvent;
import com.example.zane.homework.homeworkdetail.presenters.FinishedMemberFragment;
import com.example.zane.homework.service.DownloadFileService;
import com.example.zane.homework.utils.FileUtils;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.functions.Action1;

import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_NUM;
import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_URL;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class WorkJudgePresenter extends BaseActivity<WorkJudgeActivityView> {

    private HomeWorkModel workModel = HomeWorkModel.getInstance();

    private String sid;
    private String asid;
    private String attachment;
    private String name;

    @Override
    public Class<WorkJudgeActivityView> getRootViewClass() {
        return WorkJudgeActivityView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        EventBus.getDefault().register(this);

        attachment = getIntent().getStringExtra(FinishedMemberFragment.ATTACHMENT);
        name = getIntent().getStringExtra(FinishedMemberFragment.STU_NAME);
        sid = getIntent().getStringExtra(FinishedMemberFragment.SID);
        asid = getIntent().getStringExtra(FinishedMemberFragment.ASID);

        String attachDB = DownloadWorkDBManager.getInstance().queryAttachment(asid, "1");

        v.init(name, getIntent().getStringExtra(FinishedMemberFragment.ADDTION),
                attachment, attachDB);
    }

    public void judgeWork(String degree, String addtion){
        workModel.judgeHomeWork(degree, addtion, asid, sid).subscribe(new FinalSubscriber<String>(this, string -> {
            v.judgeSuccess();
        }));
    }

    public void downloadWork(){
        Intent intent = new Intent(WorkJudgePresenter.this, DownloadFileService.class);
        intent.putExtra(DOWNLOAD_URL, attachment);
        intent.putExtra(DOWNLOAD_NUM, "2");
        startService(intent);
    }

    public void openFile() {
        Intent intent = FileUtils.openFile(DownloadWorkDBManager.getInstance().queryFilePath(asid, "1"));
        startActivity(intent);
    }

    /**
     * 接收作业下载完成的事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DownloadFinish(DownloadFinishEvent event){
        DownloadWorkDBManager.getInstance().insert(name, asid, attachment, event.getFilePath(), "1");
        v.openFile();
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
