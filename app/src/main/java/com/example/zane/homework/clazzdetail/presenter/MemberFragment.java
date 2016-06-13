package com.example.zane.homework.clazzdetail.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.entity.MemberDetail;
import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberFragment extends BaseFragmentPresenter<ClazzDeatilFragmentView>{

    private List<MemberDetail> datas;
    private ClazzDetailMemberAdapter adapter;

    public static MemberFragment newInstance(){
        MemberFragment fragment = new MemberFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        adapter = new ClazzDetailMemberAdapter(getActivity());
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
