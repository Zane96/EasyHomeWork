package com.example.zane.homework.homeworkdetail.view;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.homeworkdetail.presenters.StuHomePresenter;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuHomeView extends BaseActivityView {

    @Bind(R.id.toolbar_submit)
    Toolbar toolbarSubmit;
    @Bind(R.id.button_submit_download)
    Button buttonSubmitDownload;
    @Bind(R.id.button_submit)
    Button buttonSubmit;
    @Bind(R.id.button_submit_addtion)
    TextView buttonSubmitAddtion;
    @Bind(R.id.button_submit_deadline)
    TextView buttonSubmitDeadline;

    private StuHomePresenter presenter;

    @Override
    public int getRootViewId() {
        return R.layout.activity_submit;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        presenter = (StuHomePresenter) iPersenter;
    }

    public void init() {
        initToolbar(presenter, toolbarSubmit, "我的作业");
    }

    public void initData(String homework, String addtion, String deadline, String attachmenntDB) {
        if (attachmenntDB != null) {
            buttonSubmitDownload.setText("打开");
        }

        if (!"".equals(homework)) {
            buttonSubmit.setText("未提交");
        }

        buttonSubmitAddtion.setText(addtion);
        buttonSubmitDeadline.setText(deadline);
    }

    public void openFile(){
        buttonSubmitDownload.setText("打开");
        buttonSubmitDownload.setEnabled(true);
        RxView.clicks(buttonSubmitDownload)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {presenter.openFile();});

        RxView.clicks(buttonSubmit)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {presenter})
    }

    public void judgeSuccess(){
        Snackbar.make(buttonSubmitDownload, "批改成功", Snackbar.LENGTH_SHORT).show();
    }

    public void downloading(){
        buttonSubmitDownload.setText("下载中");
        buttonSubmitDownload.setEnabled(false);
    }

    @Override
    public void onPresenterDestory() {

    }
}
