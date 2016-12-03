package com.example.zane.homework.message.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.ApplyDetail;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.message.view.MessageView;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageActivity extends BaseActivityPresenter<MessageView>{

    private MessageRecyAdapter adapter;
    private List<MessageDetail> datas;
    private final ClassModel model = ClassModel.getInstance();

    private final List<String> cids = new ArrayList<>();
    private FinalSubscriber<List<StuHaveClass.DataEntity>> subscriber;

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
        subscriber = new FinalSubscriber<>(this, dataEnties -> {
            List<StuHaveClass.DataEntity> datas = (List<StuHaveClass.DataEntity>) dataEnties;
            Observable.from(datas)
                    .filter(dataEntity2 -> {
                        if ("ture".equals(dataEntity2.getCreator())){
                            return true;
                        }
                        return false;
                    }).subscribe(dataEntity -> {
                        model.showApply(dataEntity.getCid()).subscribe(dataEntity3 -> {
                            ShowApply.DataEntity dataEntity1 = (ShowApply.DataEntity) dataEntity3;

                            Observable.from(dataEntity1.getTeach())
                                    .map(teachEntity -> {
                                        ApplyDetail applyDetail = (ApplyDetail) teachEntity;
                                        return applyDetail;
                                    }).subscribe(applyDetail -> {
                                        adapter.addCid(dataEntity.getCid());
                                        adapter.add(applyDetail);
                                        adapter.notifyItemInserted(adapter.getItemCount());
                                    });

                            Observable.from(dataEntity1.getStu())
                                    .map(stuEntity -> {
                                        ApplyDetail applyDetail = (ApplyDetail) stuEntity;
                                        return applyDetail;
                                    }).subscribe(applyDetail -> {
                                        adapter.addCid(dataEntity.getCid());
                                        adapter.add(applyDetail);
                                        adapter.notifyItemInserted(adapter.getItemCount());
                                    });
                        });
                    });
        });

        model.stuClass().subscribe(subscriber);
    }

    @Override
    public void inDestory() {
        if (subscriber != null){
            subscriber.cancelProgress();
        }
    }

    @Override
    public Activity getContext() {
        return this;
    }


}
