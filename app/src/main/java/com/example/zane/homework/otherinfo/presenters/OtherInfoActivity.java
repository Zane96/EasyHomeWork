package com.example.zane.homework.otherinfo.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.clazzdetail.view.ClazzDetailActivityView;
import com.example.zane.homework.data.bean.GetStatistc;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.otherinfo.view.OtherInfoView;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class OtherInfoActivity extends BaseActivity<OtherInfoView> {

    private MemberDetail memberDetail;
    private String courseName;
    private final HomeWorkModel model = HomeWorkModel.getInstance();

    @Override
    public Class<OtherInfoView> getRootViewClass() {
        return OtherInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        String name = getIntent().getStringExtra(MemberFragment.NAME);
        String intro = getIntent().getStringExtra(MemberFragment.INTRO);
        v.init(name, intro, RandomBackImage.getRandomAvatar());
        getData(getIntent().getStringExtra(MemberFragment.JID), getIntent().getStringExtra(MemberFragment.SID));
    }

    public void getData(String jid, String sid){
        model.getStatistc(jid, sid)
                .subscribe(new FinalSubscriber<GetStatistc.DataEntity>(this, dataEntity -> {
                    GetStatistc.DataEntity data = (GetStatistc.DataEntity) dataEntity;
                    v.setData(data.getCoursename(), data.getTotal(), data.getScore(), String.valueOf(data.getAbsent()));
                }));
    }

    @Override
    public void inDestory() {
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
