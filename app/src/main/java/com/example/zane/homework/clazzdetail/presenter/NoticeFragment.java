package com.example.zane.homework.clazzdetail.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.clazzdetail.NoticeDetailFrgament;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.data.model.MessageModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.event.LoadMoreNoticeEvent;
import com.example.zane.homework.event.NoticeRefreshEvent;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class NoticeFragment extends BaseFragment<ClazzDeatilFragmentView> {

    private ClazzDetailNoticeAdapter adapter;

    private MessageModel model = MessageModel.getInstance();
    private List<String> mids;
    private LinearLayoutManager manager;

    private boolean isLoading = false;

    //分页加载
    private int page = 1;

    //用于进行比较，判断是否有下一页
    private int originPage = 1;

    public static NoticeFragment newInstance(){
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public Class<ClazzDeatilFragmentView> getRootViewClass() {
        return ClazzDeatilFragmentView.class;
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        adapter = new ClazzDetailNoticeAdapter(getActivity());
        mids = new ArrayList<>();

        getData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.initNoticeRecycle(adapter);

        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                //显示消息公告的具体内容
                NoticeDetailFrgament frgament = new NoticeDetailFrgament();
                frgament.setMid(mids.get(i));
                frgament.show(getChildFragmentManager(), "NoticeDetailFragment");
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

    /**
     * 获取数据和上拉加载更多
     */
    public void getData(){

        isLoading = true;

        Observable<List<GetMessage.DataEntity>> observable;
        //如果第N页数据为空，后台会返回null
        if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
            observable = model.getTeaMessage(page);
        } else {
            observable = model.getStuMessage(page);
        }

        observable.subscribe(new FinalSubscriber<List<GetMessage.DataEntity>>(this, dataEntity -> {
            if (dataEntity != null){
                List<GetMessage.DataEntity> messages = (List<GetMessage.DataEntity>) dataEntity;
                adapter.addAll(messages);

                for (GetMessage.DataEntity data : messages) {
                    mids.add(data.getMid());
                }

                //页数自增,一页最多填充七条数据(后台的规定)
                page++;
                adapter.notifyDataSetChanged();
                isLoading = false;
            } else {
                JUtils.Toast("没有更多消息了～");
                //停止再加载更多
                isLoading = true;
            }
        }));
    }

    /**
     * 下拉刷新
     */
    private void reFreshData(){
        //暴力刷新，后期用Diffutils重构
        adapter.clear();
        mids.clear();
        //final Semaphore mSemaphore = new Semaphore(1);

        Observable<List<GetMessage.DataEntity>> observable;
        for (int i = 1; i <= page - 1; i++) {
            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
                observable = model.getTeaMessage(i);
            } else {
                observable = model.getStuMessage(i);
            }

            observable.subscribe(new FinalSubscriber<List<GetMessage.DataEntity>>(this, dataEntity -> {
                List<GetMessage.DataEntity> messages = (List<GetMessage.DataEntity>) dataEntity;
                adapter.addAll(messages);
                adapter.notifyDataSetChanged();
                for (GetMessage.DataEntity data : messages) {
                    mids.add(data.getMid());
                }
            }));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(NoticeRefreshEvent event){
        v.finishRefresh();
        reFreshData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadMore(LoadMoreNoticeEvent event){
        getData();
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onPostNotice(PostNoticeEvent event){
//        NoticeDetail data = new NoticeDetail();
//        data.setContent(event.getContent());
//        data.setPostTime(event.getPostTime());
//        adapter.add(data, 0);
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
