package com.example.zane.homework.clazzdetail.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.NoDueHomeWork;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.HomeWorkDetail;
import com.example.zane.homework.event.FinishUpLoadEvent;
import com.example.zane.homework.event.HomeWorkRefreshEvent;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.homeworkdetail.presenters.HomeWorkDetailActivity;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.homeworkdetail.presenters.StuHomePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkFragment extends BaseFragment<ClazzDeatilFragmentView> {

    private ArrayList<HomeWorkDetail> datas;
    private List<NoDueHomeWork.DataEntity> teaData;
    private List<GetNoDueWork.DataEntity> stuData;
    private ClazzDetailHomeWorkAdapter adapter;
    private HomeWorkModel model = HomeWorkModel.getInstance();

    public static final String CID = "cid";
    public static final String ASID = "asid";

    private static final String DATAS = "datas";

    public static HomeWorkFragment newInstance(){
        HomeWorkFragment fragment = new HomeWorkFragment();
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

    //必须在这里注册!
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        datas = new ArrayList<>();
        adapter = new ClazzDetailHomeWorkAdapter(getActivity());
        getData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DATAS, datas);
    }

    /**
     * 获取数据
     */
    private void getData(){
        if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
            model.showNoDueWork().subscribe(new FinalSubscriber<List<NoDueHomeWork.DataEntity>>(this, data -> {
                datas = (ArrayList<HomeWorkDetail>) data;
                teaData = (List<NoDueHomeWork.DataEntity>) data;
                refreshData();
            }));
        } else {
            model.getNoDueWork().subscribe(new FinalSubscriber<List<GetNoDueWork.DataEntity>>(this, data -> {
                datas = (ArrayList<HomeWorkDetail>) data;
                stuData = (List<GetNoDueWork.DataEntity>) data;
                refreshData();
            }));
        }
    }

    private void refreshData() {
        adapter.clear();
        adapter.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null){
            datas = savedInstanceState.getParcelableArrayList(DATAS);
        }

        v.initHomeWorkRecycle(adapter);

        if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
            adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
                @Override
                public void onClick(View view, int i) {
                    Intent intent = new Intent(getActivity(), HomeWorkDetailActivity.class);
                    intent.putExtra(CID, teaData.get(i).getCid());
                    intent.putExtra(ASID, teaData.get(i).getAsid());
                    startActivity(intent);
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
        } else {
            adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
                @Override
                public void onClick(View view, int i) {
                    Intent intent = new Intent(getActivity(), StuHomePresenter.class);
                    intent.putExtra(StuHomePresenter.ASID, stuData.get(i).getAsid());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int i) {
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishRefresh(HomeWorkRefreshEvent event){
        v.finishRefresh();
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinish(FinishUpLoadEvent event){

//        if (event.isFinished()){
//            datas.get(event.getPosition()).setAttachement("loaded");
//        } else {
//            datas.get(event.getPosition()).setAttachement("loading");
//        }
//        adapter.clear();
//        adapter.addAll(datas);
//        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostHomeWork(PostHomeWorkEvent event){
        // TODO: 2016/11/11 发布作业 
//        HomeWorkDetail data = new HomeWorkDetail();
//        data.setDeadLine("截至: " + event.getEndTime());
//        data.setPubTimel("发布: " + event.getPostTime());
//        data.setAddtion(event.getAddtion());
//        data.setAttachement(event.getAttachement());
//        adapter.add(data, 0);
//        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
