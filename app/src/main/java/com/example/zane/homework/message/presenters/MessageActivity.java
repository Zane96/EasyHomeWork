package com.example.zane.homework.message.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.message.view.MessageView;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageActivity extends BaseActivityPresenter<MessageView>{

    private MessageRecyAdapter adapter;
    private List<MessageDetail> datas;
    private ProgressHandler handler;

    @Override
    public Class<MessageView> getRootViewClass() {
        return MessageView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        adapter = new MessageRecyAdapter(App.getInstance());
        datas = new ArrayList<>();
        handler = new ProgressHandler(this);
        v.init();
        v.showProgress();
        Message message = new Message();
        message.what = 1;
        handler.sendMessageDelayed(message, 1000);
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
        private WeakReference<MessageActivity> reference;

        public ProgressHandler(MessageActivity activity) {
            reference = new WeakReference<MessageActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                switch (msg.what) {
                    case 1:

                        for (int i = 0; i < 10; i++) {
                            MessageDetail data = new MessageDetail();
                            //data.setAvatar(RandomBackImage.getRandomAvatar());
                            reference.get().datas.add(data);
                        }
                        reference.get().adapter.addAll(reference.get().datas);
                        reference.get().v.initAdapter(reference.get().adapter);
                        JUtils.Toast(reference.get().getResources().getString(R.string.finish_load));
                        reference.get().v.dismissProgress();
                        break;
                }
            }
        }
    }
}
