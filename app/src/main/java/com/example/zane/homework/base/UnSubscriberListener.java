package com.example.zane.homework.base;

/**
 * FinalSubscriber自动在P层的onDestory()里自动响应取消订阅回调
 * Created by Zane on 2016/12/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public interface UnSubscriberListener {
    void toSubscriber();
}
