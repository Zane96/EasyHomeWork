package com.example.zane.homework.base;

import android.widget.Toast;

import com.example.zane.easymvp.base.IView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;

/**
 * Created by Zane on 2016/12/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public abstract class BaseFragment<V extends IView> extends BaseFragmentPresenter<V>{

    public UnSubscriberListener listener;

    public void setUnSubscriberListener(UnSubscriberListener listener){
        this.listener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            listener.toSubscriber();
        }
    }
}
