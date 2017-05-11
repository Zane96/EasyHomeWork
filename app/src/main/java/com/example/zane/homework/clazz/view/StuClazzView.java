package com.example.zane.homework.clazz.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.clazz.presenter.StuClazzAdapter;

import butterknife.Bind;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuClazzView extends BaseActivityView {

    @Bind(R.id.recycleview_stu_clazz)
    RecyclerView recycleviewStuClazz;
    @Bind(R.id.toolbar_stuclazz)
    Toolbar toolbarStuclazz;

    private AppCompatActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_stuclazz;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (AppCompatActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init() {
        initToolbar(activity, toolbarStuclazz, "我的班级");
    }

    public void initRecy(StuClazzAdapter adapter) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(activity);
        recycleviewStuClazz.setAdapter(adapter);
        recycleviewStuClazz.setLayoutManager(manager);
    }
}
