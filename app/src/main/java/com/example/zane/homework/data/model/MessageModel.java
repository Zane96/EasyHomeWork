package com.example.zane.homework.data.model;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.service.MessageService;

import java.util.List;

import rx.Observable;

/**
 * Created by Zane on 16/11/2.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class MessageModel {
    private Context context;
    private MessageService serviceApi;

    private MessageModel(){
        context = App.getInstance();
        serviceApi = CommonProvider.provideRetrofit()
                             .baseUrl(MessageService.BASE_URL)
                             .build()
                             .create(MessageService.class);
    }

    private static final class InstanceHolder{
        private final static MessageModel instance = new MessageModel();
    }

    public static MessageModel getInstance(){
        return MessageModel.InstanceHolder.instance;
    }

    //发消息
    public Observable<String> postMessage(String type, String id, String content){
        return serviceApi.postMessage(type, id, content)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //查看消息列表
    public Observable<List<GetMessage.DataEntity>> getMessage(int num){
        return serviceApi.getMessage(num)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }

    //查看消息详细信息
    public Observable<GetDetailMessage.DataEntity> getDetailMessage(int num){
        return serviceApi.getDetailMessage(num)
                       .compose(new SchedulerTransform<>())
                       .compose(new ErrorTransForm<>());
    }
}
