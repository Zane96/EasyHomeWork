package com.example.zane.homework.clazzdetail.presenter;

import android.content.Intent;
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
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberFragment extends BaseFragmentPresenter<ClazzDeatilFragmentView>{

    public static final String MEMBER_DETAIL = "memberDetail";

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
            MemberDetail memberDetail = new MemberDetail();
            memberDetail.setAvatar(RandomBackImage.getRandomAvatar());
            datas.add(memberDetail);
        }
        adapter.addAll(datas);
        v.initMemberRecycle(adapter);
        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(getActivity(), OtherInfoActivity.class);
                intent.putExtra(MEMBER_DETAIL, datas.get(i));
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, final int i) {
                if (MySharedPre.getInstance().getIdentity().equals("student") && StudentLogin.getInstacne().getIsOwner()){
                    Snackbar.make(view, "您想要删除这位成员嘛?~", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.remove(i);
                            adapter.notifyDataSetChanged();
                        }
                    }).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
