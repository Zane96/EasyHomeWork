package com.example.zane.homework.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;

/**
 * Created by Zane on 2016/12/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public abstract class BaseActivityView extends BaseViewImpl{

    @Override
    public void creatView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
        super.creatView(inflater, parent, bundle);
    }


    public void initToolbar(AppCompatActivity activity, Toolbar toolbar, String title){
        activity.setTitle(title);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RxToolbar.navigationClicks(toolbar).subscribe(aVoid -> {
            activity.finish();
        });
    }
}
