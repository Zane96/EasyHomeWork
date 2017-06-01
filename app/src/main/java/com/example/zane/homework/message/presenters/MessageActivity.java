package com.example.zane.homework.message.presenters;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.message.view.MessageView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageActivity extends BaseActivity<MessageView> {

    private MessageRecyAdapter adapter;
    private List<MessageDetail> datas;
    private final ClassModel model = ClassModel.getInstance();

    private final List<String> cids = new ArrayList<>();

    @Override
    public Class<MessageView> getRootViewClass() {
        return MessageView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        adapter = new MessageRecyAdapter(App.getInstance());
        datas = new ArrayList<>();
        v.init();
        v.initAdapter(adapter);
        getData();
    }

    public void getData(){
        adapter.clear();

        model.stuClass()
                .flatMap(dataEnties -> Observable.from(dataEnties))
                .filter(dataEntity -> {
                    if ("ture".equals(dataEntity.getCreator())){
                        return true;
                    }
                    return false;})
                .flatMap(creatData -> {
                    adapter.addCid(creatData.getCid());
                    return model.showApply(creatData.getCid());})
                .toList()
                .subscribe(new FinalSubscriber<List<ShowApply.DataEntity>>(this, showDatasRaw -> {
                    List<ShowApply.DataEntity> showDatas = (List<ShowApply.DataEntity>) showDatasRaw;
                    for (ShowApply.DataEntity showData : showDatas){
                        Observable.merge(Observable.from(showData.getTeach()), Observable.from(showData.getStu()))
                                .subscribe(finalData -> {
                                    adapter.add(finalData);
                                    adapter.notifyItemInserted(adapter.getItemCount());
                                });
                    }
                }));
    }

    @Override
    public void inDestory() {
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
