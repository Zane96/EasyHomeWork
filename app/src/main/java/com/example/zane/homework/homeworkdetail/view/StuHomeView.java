package com.example.zane.homework.homeworkdetail.view;

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

    private void initView() {
        RxView.clicks(buttonSubmit)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {presenter.downloadWork();});
    }

    @Override
    public void onPresenterDestory() {

    }
}
