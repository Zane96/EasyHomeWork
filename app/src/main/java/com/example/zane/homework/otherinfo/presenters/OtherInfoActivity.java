package com.example.zane.homework.otherinfo.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.clazzdetail.view.ClazzDetailActivityView;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.otherinfo.view.OtherInfoView;
import com.example.zane.homework.data.sp.MySharedPre;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class OtherInfoActivity extends BaseActivityPresenter<OtherInfoView>{

    private MemberDetail memberDetail;
    private ProgressHandler handler;
    private String courseName;

    @Override
    public Class<OtherInfoView> getRootViewClass() {
        return OtherInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            memberDetail = (MemberDetail) getIntent().getSerializableExtra(MemberFragment.MEMBER_DETAIL);
            v.init(memberDetail.getName(), memberDetail.getSelfIntro(), memberDetail.getAvatar());
        } else if (MySharedPre.getInstance().getIdentity().equals("student")){
            if (getIntent().getSerializableExtra(MemberFragment.MEMBER_DETAIL) != null){
                memberDetail = (MemberDetail) getIntent().getSerializableExtra(MemberFragment.MEMBER_DETAIL);
                JUtils.Toast(memberDetail.getName());
                v.init(memberDetail.getName(), memberDetail.getSelfIntro(), memberDetail.getAvatar());
            } else {
                courseName = getIntent().getStringExtra(ClazzDetailActivityView.COURSENAME);
                v.init(StudentLogin.getInstacne().getUserName(), StudentLogin.getInstacne().getSelfIntro(), StudentLogin.getInstacne().getAvatar());
            }
        }

        v.showProgress();
        handler = new ProgressHandler(this);
        Message message = new Message();
        message.what = 1;
        handler.sendMessageDelayed(message, 1500);
    }

    @Override
    public void inDestory() {
        handler.removeMessages(1);
    }

    @Override
    public Activity getContext() {
        return this;
    }

    private final static class ProgressHandler extends Handler {
        private WeakReference<OtherInfoActivity> reference;
        public ProgressHandler(OtherInfoActivity activity){
            reference = new WeakReference<OtherInfoActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null){
                MemberDetail data = reference.get().memberDetail;
                switch (msg.what){
                    case 1:
                        reference.get().v.hideProgress();
                        if (MySharedPre.getInstance().getIdentity().equals("teacher") || reference.get().getIntent().getSerializableExtra(MemberFragment.MEMBER_DETAIL) != null){
                            reference.get().v.setData(data.getNumber(), data.getScore(), data.getWorks(), data.getNoWorks());
                        } else {
                            reference.get().v.setData(reference.get().courseName, "30分", "15分", "2次");
                        }
                        break;
                }
            }
        }
    }
}
