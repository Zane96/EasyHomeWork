package com.example.zane.homework.clazzdetail.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.entity.NoticeDetail;
import com.example.zane.homework.event.PostNoticeEvent;
import com.example.zane.homework.data.sp.MySharedPre;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class NoticeFragment extends BaseFragmentPresenter<ClazzDeatilFragmentView>{

    private List<NoticeDetail> datas;
    private ClazzDetailNoticeAdapter adapter;

    public static NoticeFragment newInstance(){
        NoticeFragment fragment = new NoticeFragment();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new ClazzDetailNoticeAdapter(getActivity());
        datas = new ArrayList<>();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 10; i++){
            datas.add(new NoticeDetail());
        }
        adapter.addAll(datas);
        v.initNoticeRecycle(adapter);
        if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
            adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
                @Override
                public void onClick(View view, int i) {
                }
                @Override
                public void onLongClick(View view, final int i) {
                    Snackbar.make(view, "您想要删除这个公告吗?~", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostNotice(PostNoticeEvent event){
        NoticeDetail data = new NoticeDetail();
        data.setContent(event.getContent());
        data.setPostTime(event.getPostTime());
        adapter.add(data, 0);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
