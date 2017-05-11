package com.example.zane.homework.homeworkdetail.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailMemberAdapter;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.utils.RandomBackImage;

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
    public IPersenter getPersenter() {
        return this;
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

        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(getActivity(), OtherInfoActivity.class);
               // intent.putExtra(MemberFragment.MEMBER_DETAIL, datas.get(i));
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int i) {
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
