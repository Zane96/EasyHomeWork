package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.info.view.InfoFragmentView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class InfoActivity extends BaseActivityPresenter<InfoFragmentView> {

    private static final String TAG = InfoActivity.class.getSimpleName();

    @Override
    public Class<InfoFragmentView> getRootViewClass() {
        return InfoFragmentView.class;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, requestCode+"");
        v.OnActivityResult(requestCode, resultCode, data);
    }
}
