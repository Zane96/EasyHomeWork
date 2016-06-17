package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.info.view.ChangeOtherInfoView;
import com.example.zane.homework.info.view.InfoView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangeOtherInfoActivity extends BaseActivityPresenter<ChangeOtherInfoView>{
    @Override
    public Class<ChangeOtherInfoView> getRootViewClass() {
        return ChangeOtherInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        String oldData = getIntent().getStringExtra(InfoView.CHANGE_CONTENT);
        v.init(oldData);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }
}
