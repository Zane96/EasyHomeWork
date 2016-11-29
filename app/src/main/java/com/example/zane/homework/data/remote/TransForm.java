package com.example.zane.homework.data.remote;

import rx.Observable;

/**
 * Created by Zane on 2016/11/29.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public final class TransForm {
    public static <T> Observable<T> transform(Observable<T> tObservable){
        return tObservable
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }
}
