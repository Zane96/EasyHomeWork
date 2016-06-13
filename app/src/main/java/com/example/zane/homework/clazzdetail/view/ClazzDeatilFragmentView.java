package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailHomeWorkAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailMemberAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailNoticeAdapter;
import com.example.zane.homework.event.ActivityReenterEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDeatilFragmentView extends BaseViewImpl {

    @Bind(R.id.recycleview_homework)
    RecyclerView recycleviewHomework;
    private Activity activity;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog progressDialog;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_homework;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = activity;
    }

    public void initHomeWorkRecycle(ClazzDetailHomeWorkAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);
    }

    public void initNoticeRecycle(ClazzDetailNoticeAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);
    }

    public void initMemberRecycle(ClazzDetailMemberAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
    }

    public void hideProgress() {
        progressDialog.hide();
    }
}
