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

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
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
    private ProgressHandler handler;

    @Override
    public Class<HomeWorkDetailView> getRootViewClass() {
        return HomeWorkDetailView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        handler = new ProgressHandler(this);
        v.initToolbar();
        v.showProgress();
        adapter = new HomeWorkDetailViewpagerAdapter(getSupportFragmentManager());
        adapter.addFinishedFragment(FinishedMemberFragment.newInstance(), "完成");
        adapter.addNoFinishedFragment(NoFinishedMemberFragment.newInstance(), "未完成");
        Message message = new Message();
        message.what = 1;
        handler.sendMessageDelayed(message, 1500);
    }

    @Override
    public void inDestory() {
        handler.removeMessages(1);
    }

    @Override
    public Activity getContext() {
        return this;
    }

    private final static class ProgressHandler extends Handler {
        private WeakReference<HomeWorkDetailActivity> reference;
        public ProgressHandler(HomeWorkDetailActivity activity){
            reference = new WeakReference<HomeWorkDetailActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null){
                switch (msg.what){
                    case 1:
                        reference.get().v.hideProgress();
                        reference.get().v.initTabLayout(reference.get().adapter);
                        break;
                }
            }
        }
    }
}
