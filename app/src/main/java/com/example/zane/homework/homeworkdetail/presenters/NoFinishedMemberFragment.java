package com.example.zane.homework.homeworkdetail.presenters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailMemberAdapter;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.entity.MemberDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class NoFinishedMemberFragment extends BaseFragmentPresenter<ClazzDeatilFragmentView>{

    private List<MemberDetail> datas;
    private ClazzDetailMemberAdapter adapter;

    public static NoFinishedMemberFragment newInstance(){
        NoFinishedMemberFragment fragment = new NoFinishedMemberFragment();
        return fragment;
    }

    @Override
    public Class<ClazzDeatilFragmentView> getRootViewClass() {
        return ClazzDeatilFragmentView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        adapter = new ClazzDetailMemberAdapter(App.getInstance());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 10; i++){
            datas.add(new MemberDetail());
        }
        adapter.addAll(datas);
        v.initMemberRecycle(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
