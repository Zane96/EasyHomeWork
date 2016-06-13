package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDetailPostNoticeView;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostNoticeActivity extends BaseActivityPresenter<ClazzDetailPostNoticeView>{
    @Override
    public Class<ClazzDetailPostNoticeView> getRootViewClass() {
        return ClazzDetailPostNoticeView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }
}
