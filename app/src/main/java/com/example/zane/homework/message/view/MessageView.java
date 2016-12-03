package com.example.zane.homework.message.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.message.presenters.MessageRecyAdapter;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageView extends BaseViewImpl {
    @Bind(R.id.toolbar_message)
    Toolbar toolbarMessage;
    @Bind(R.id.recyleview_message)
    RecyclerView recyleviewMessage;

    private AppCompatActivity activity;
    private GridLayoutManager manager;

    @Override
    public int getRootViewId() {
        return R.layout.activity_message;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar(){
        toolbarMessage.setTitle("我的消息");
        activity.setSupportActionBar(toolbarMessage);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMessage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init(){
        initToolbar();
        manager = new GridLayoutManager(activity, 2);
    }

    public void initAdapter(final MessageRecyAdapter adapter){
        recyleviewMessage.setAdapter(adapter);
        recyleviewMessage.setLayoutManager(manager);
        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
            }
            @Override
            public void onLongClick(View view, final int i) {
                Snackbar.make(view, "您确定要删除这条消息吗?~", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
            }
        });
    }

}
