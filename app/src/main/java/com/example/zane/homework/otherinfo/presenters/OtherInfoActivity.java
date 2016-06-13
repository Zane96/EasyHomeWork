package com.example.zane.homework.otherinfo.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.otherinfo.view.OtherInfoView;

import java.lang.ref.WeakReference;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class OtherInfoActivity extends BaseActivityPresenter<OtherInfoView>{

    private MemberDetail memberDetail;
    private ProgressHandler handler;

    @Override
    public Class<OtherInfoView> getRootViewClass() {
        return OtherInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        memberDetail = (MemberDetail) getIntent().getSerializableExtra(MemberFragment.MEMBER_DETAIL);
        v.init(memberDetail.getName(), memberDetail.getSelfIntro(), memberDetail.getAvatar());
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
                        reference.get().v.setData(data.getNumber(), data.getScore(), data.getWorks(), data.getNoWorks());
                        break;
                }
            }
        }
    }
}
