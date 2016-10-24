package com.example.zane.homework.data.remote;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zane on 16/3/14.
 * 线程转换器
 */
public class SchedulerTransform<T> implements Observable.Transformer<T, T> {

    private static final String TAG = "SchedulerTransform" ;

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .unsubscribeOn(Schedulers.io());
    }
}
