package com.example.zane.homework.base;

import android.os.Bundle;

import com.example.zane.easymvp.base.IView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 2016/12/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public abstract class BaseActivity<V extends IView> extends BaseActivityPresenter<V>{

    public UnSubscriberListener listener;

    public void setUnSubscriberListener(UnSubscriberListener listener){
        this.listener = listener;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            listener.toSubscriber();
        }
    }
}
