package com.example.zane.homework.data.remote;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

import android.content.Context;
import android.util.Log;

import com.example.zane.homework.data.remote.progress.ProgressCancelListener;
import com.example.zane.homework.data.remote.progress.ProgressDialogHandler;

import rx.Subscriber;


public class FinalSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {


    private static final String TAG  = "FinalSubscriber";

    private ProgressDialogHandler progressDialogHandler;
    private SubscriberOnNextListener subscriberOnNextListener;
    private Context context;

    public FinalSubscriber(Context context, SubscriberOnNextListener listener){
        subscriberOnNextListener = listener;
        this.context = context;
        progressDialogHandler = new ProgressDialogHandler(context, this, true);
    }


    public void showProgressDialog(){
        if (progressDialogHandler != null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS).sendToTarget();
        }
    }

    public void dissmissProgressDialog(){
        if (progressDialogHandler != null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.DISSMISS_PROGRESS).sendToTarget();
        }
        progressDialogHandler = null;
    }

    //当订阅者和发布者刚建立关系的时候调用这个方法
    @Override
    public void onStart() {
        showProgressDialog();
        Log.i(TAG, "onStart");
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
