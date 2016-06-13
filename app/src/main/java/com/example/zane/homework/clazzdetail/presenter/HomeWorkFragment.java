package com.example.zane.homework.clazzdetail.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.entity.HomeWorkDetail;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.event.FinishUpLoadEvent;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.homeworkdetail.presenters.HomeWorkDetailActivity;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkFragment extends BaseFragmentPresenter<ClazzDeatilFragmentView>{

    private List<HomeWorkDetail> datas;
    private ClazzDetailHomeWorkAdapter adapter;
    private boolean isAlive;

    public static HomeWorkFragment newInstance(){
        HomeWorkFragment fragment = new HomeWorkFragment();
        return fragment;
    }

    @Override
    public Class<ClazzDeatilFragmentView> getRootViewClass() {
        return ClazzDeatilFragmentView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return getActivity();
    }

    //必须在这里注册!
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 10; i++) {
            datas.add(new HomeWorkDetail());
        }
        adapter.addAll(datas);
        v.initHomeWorkRecycle(adapter);
        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                startActivity(new Intent(getActivity(), HomeWorkDetailActivity.class));
            }
            @Override
            public void onLongClick(View view, final int i) {
                Snackbar.make(view, "您想要删除这个作业吗?~", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }).show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinish(FinishUpLoadEvent event){
        if (event.isFinished()){
            datas.get(event.getPosition()).setAttachement("loaded");
        } else {
            datas.get(event.getPosition()).setAttachement("loading");
        }
        adapter.clear();
        adapter.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostHomeWork(PostHomeWorkEvent event){
        HomeWorkDetail data = new HomeWorkDetail();
        data.setDeadLine("截至: " + event.getEndTime());
        data.setPubTimel("发布: " + event.getPostTime());
        data.setAddtion(event.getAddtion());
        data.setAttachement(event.getAttachement());
        adapter.add(data, 0);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        adapter = new ClazzDetailHomeWorkAdapter(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //    private final static class ProgressHandler extends Handler{
//        private WeakReference<HomeWorkFragment> reference;
//        public ProgressHandler(HomeWorkFragment fragment){
//            reference = new WeakReference<HomeWorkFragment>(fragment);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (reference.get() != null){
//                HomeWorkFragment fragment = reference.get();
//                switch (msg.what){
//                    case 1:
//                        //fragment.v.initHomeWorkRecycle(reference.get().adapter);
//                        for (int i = 0; i < 10; i++) {
//                            fragment.datas.add(new HomeWorkDetail());
//                        }
//                        fragment.adapter.addAll(fragment.datas);
//                        fragment.v.initHomeWorkRecycle(fragment.adapter);
//                        break;
//                }
//            }
//        }
//    }

}
