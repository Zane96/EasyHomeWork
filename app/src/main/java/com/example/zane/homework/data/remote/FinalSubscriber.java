package com.example.zane.homework.data.remote;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

import android.content.Context;
import android.util.Log;

import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.base.UnSubscriberListener;
import com.example.zane.homework.data.remote.progress.ProgressCancelListener;
import com.example.zane.homework.data.remote.progress.ProgressDialogHandler;

import rx.Subscriber;


public class FinalSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private static final String TAG  = "FinalSubscriber";
    private ProgressDialogHandler progressDialogHandler;
    private SubscriberOnNextListener subscriberOnNextListener;

    /**
     * activity调用
     * @param context
     * @param listener
     */
    public FinalSubscriber(BaseActivity context, SubscriberOnNextListener listener){
        context.setUnSubscriberListener(() -> {if (!isUnsubscribed()){unsubscribe();}});
        progressDialogHandler = new ProgressDialogHandler(context, this, true);
        subscriberOnNextListener = listener;
    }

    /**
     * fragment调用
     * @param context
     * @param listener
     */
    public FinalSubscriber(BaseFragment context, SubscriberOnNextListener listener) {
        context.setUnSubscriberListener(() -> {if (!isUnsubscribed()){unsubscribe();}});
        progressDialogHandler = new ProgressDialogHandler(context.getActivity(), this, true);
        subscriberOnNextListener = listener;
    }

    private void showProgressDialog(){
        if (progressDialogHandler != null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS).sendToTarget();
        }
    }

    private void dissmissProgressDialog(){
        if (progressDialogHandler != null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.DISSMISS_PROGRESS).sendToTarget();
        }
    }

    //当订阅者和发布者刚建立关系的时候调用这个方法
    @Override
    public void onStart() {
        showProgressDialog();
    }

    //取消订阅
    @Override
    public void cancelProgress() {
        if (!isUnsubscribed()){
            unsubscribe();
        }
    }

    @Override
    public void onCompleted() {
        dissmissProgressDialog();
    }

    //这个方法不用管因为错误转换器已经在这个之前调用了onError方法
    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
        subscriberOnNextListener.onNext(t);
    }
}
