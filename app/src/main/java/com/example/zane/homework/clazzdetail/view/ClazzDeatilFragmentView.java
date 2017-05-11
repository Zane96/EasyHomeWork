package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailHomeWorkAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailMemberAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailNoticeAdapter;
import com.example.zane.homework.event.HomeWorkRefreshEvent;
import com.example.zane.homework.event.MemberRefreshEvent;
import com.example.zane.homework.event.NoticeRefreshEvent;
import com.example.zane.homework.event.WorkDetailRefreshEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * View复用严重破坏了单一原则。不靠谱
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDeatilFragmentView extends BaseViewImpl {

    @Bind(R.id.recycleview_homework)
    RecyclerView recycleviewHomework;
    @Bind(R.id.swip_homework_frag)
    SwipeRefreshLayout swipHomeworkFrag;

    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_homework;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
    }

    @Override
    public void onPresenterDestory() {
    }

    public void initHomeWorkRecycle(ClazzDetailHomeWorkAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);

        swipHomeworkFrag.setOnRefreshListener(() -> {
            EventBus.getDefault().post(new HomeWorkRefreshEvent());
        });
    }

    public void initNoticeRecycle(ClazzDetailNoticeAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);

        swipHomeworkFrag.setOnRefreshListener(() -> {
            EventBus.getDefault().post(new NoticeRefreshEvent());
        });
    }

    /**
     * 作业和成员两个模块都重用了的
     * @param adapter
     * @param type work表示作业模块的重用, member表示成员模块的重用
     */
    public void initMemberRecycle(ClazzDetailMemberAdapter adapter, String type) {
        linearLayoutManager = new LinearLayoutManager(App.getInstance());
        recycleviewHomework.setAdapter(adapter);
        recycleviewHomework.setLayoutManager(linearLayoutManager);

        if (type.equals("work")){
            swipHomeworkFrag.setOnRefreshListener(() -> {
                EventBus.getDefault().post(new WorkDetailRefreshEvent());
            });
        } else {
            swipHomeworkFrag.setOnRefreshListener(() -> {
                EventBus.getDefault().post(new MemberRefreshEvent());
            });
        }
    }

    public void finishRefresh(){
        swipHomeworkFrag.setRefreshing(false);
    }

    //-----------------------------------Notice-------------------------------------


}
