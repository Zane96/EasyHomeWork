package com.example.zane.homework.data.remote;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 * 最终响应onNext的接口，在调用的地方实现方法
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
