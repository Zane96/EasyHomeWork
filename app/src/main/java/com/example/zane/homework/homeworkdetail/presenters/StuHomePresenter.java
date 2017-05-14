package com.example.zane.homework.homeworkdetail.presenters;

import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.base.UnSubscriberListener;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.data.bean.GetHoWork;
import com.example.zane.homework.data.db.DownloadWorkDBManager;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.event.DownloadFinishEvent;
import com.example.zane.homework.event.DownloadingEvent;
import com.example.zane.homework.homeworkdetail.view.StuHomeView;
import com.example.zane.homework.service.DownloadFileService;
import com.example.zane.homework.utils.FileUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_NUM;
import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_URL;

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
        asid = getIntent().getStringExtra(ASID);
        getHomeWorkData(asid);
    }

    /**
     * 获得作业的信息
     * @param asid
     */
    private void getHomeWorkData(String asid) {
        homeWorkModel.getHoWork(asid).subscribe(new FinalSubscriber<List<GetHoWork.DataEntity>>(this, data -> {
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

    /**
     * 接收作业下载完成的事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void DownloadFinish(DownloadFinishEvent event){
        DownloadWorkDBManager.getInstance().insert("", asid, attachment, event.getFilePath(), "2");
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

    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
