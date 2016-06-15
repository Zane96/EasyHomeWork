package com.example.zane.homework.authorinfo.presenters;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.authorinfo.AuthorInfoFragment;
import com.example.zane.homework.authorinfo.view.AuthorInfoView;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class AuthorInfoActivity extends BaseActivityPresenter<AuthorInfoView>{
    @Override
    public Class<AuthorInfoView> getRootViewClass() {
        return AuthorInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.initToolbar();
        v.transToAboutFragment(new AuthorInfoFragment());
    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }
}
