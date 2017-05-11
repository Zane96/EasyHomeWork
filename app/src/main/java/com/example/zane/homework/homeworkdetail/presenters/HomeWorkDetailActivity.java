package com.example.zane.homework.homeworkdetail.presenters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.SharedElementCallback;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.HomeWorkFragment;
import com.example.zane.homework.event.ActivityReenterEvent;
import com.example.zane.homework.homeworkdetail.HomeWorkDetailViewpagerAdapter;
import com.example.zane.homework.homeworkdetail.view.HomeWorkDetailView;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetailActivity extends BaseActivityPresenter<HomeWorkDetailView>{

    private HomeWorkDetailViewpagerAdapter adapter;

    @Override
    public Class<HomeWorkDetailView> getRootViewClass() {
        return HomeWorkDetailView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.initToolbar();
        adapter = new HomeWorkDetailViewpagerAdapter(getSupportFragmentManager());
        adapter.addFinishedFragment(FinishedMemberFragment.newInstance(getIntent().getStringExtra(HomeWorkFragment.CID), getIntent().getStringExtra(HomeWorkFragment.ASID)), "完成");
        adapter.addNoFinishedFragment(NoFinishedMemberFragment.newInstance(), "未完成");
        v.initTabLayout(adapter);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
