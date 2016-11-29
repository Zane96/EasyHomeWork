package com.example.zane.homework.data.model;

import android.content.Context;

import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.data.bean.NoData;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.ErrorTransForm;
import com.example.zane.homework.data.remote.SchedulerTransform;
import com.example.zane.homework.data.remote.TransForm;
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
        serviceApi = CommonProvider.getRetrofitBuilder()
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
        return TransForm.transform(serviceApi.postMessage(type, id, content));
    }

    //学生查看消息列表
    public Observable<List<GetMessage.DataEntity>> getStuMessage(int num){
        return TransForm.transform(serviceApi.getStuMessage(num));
    }

    //老师查看消息列表
    public Observable<List<GetMessage.DataEntity>> getTeaMessage(int num){
        return TransForm.transform(serviceApi.getTeaMessage(num));
    }

    //查看消息详细信息
    public Observable<GetDetailMessage.DataEntity> getDetailMessage(int num){
        return TransForm.transform(serviceApi.getDetailMessage(num));
    }
}
