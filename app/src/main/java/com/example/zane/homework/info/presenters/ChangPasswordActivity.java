package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.info.view.ChangePasswordView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangPasswordActivity extends BaseActivityPresenter<ChangePasswordView>{
    @Override
    public Class<ChangePasswordView> getRootViewClass() {
        return ChangePasswordView.class;
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
