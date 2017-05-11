package com.example.zane.homework.homeworkdetail.presenters;

import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.base.UnSubscriberListener;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.homeworkdetail.view.StuHomeView;
import com.example.zane.homework.service.DownloadFileService;

import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_NUM;
import static com.example.zane.homework.service.DownloadFileService.DOWNLOAD_URL;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuHomePresenter extends BaseActivity<StuHomeView>{

    private HomeWorkModel homeWorkModel = HomeWorkModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public Class<StuHomeView> getRootViewClass() {
        return StuHomeView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

    }

    public void downloadWork() {
        Intent intent = new Intent(this, DownloadFileService.class);
        intent.putExtra(DOWNLOAD_URL, attachment);
        intent.putExtra(DOWNLOAD_NUM, "1");
        startService(intent);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
