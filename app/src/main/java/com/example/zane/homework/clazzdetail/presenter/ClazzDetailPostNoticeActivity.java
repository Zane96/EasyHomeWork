package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.clazzdetail.view.ClazzDetailActivityView;
import com.example.zane.homework.clazzdetail.view.ClazzDetailPostNoticeView;
import com.example.zane.homework.data.model.MessageModel;
import com.example.zane.homework.data.remote.FinalSubscriber;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostNoticeActivity extends BaseActivity<ClazzDetailPostNoticeView> {

    private final MessageModel model = MessageModel.getInstance();
    private String cid;

    @Override
    public Class<ClazzDetailPostNoticeView> getRootViewClass() {
        return ClazzDetailPostNoticeView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        cid = getIntent().getStringExtra(ClazzDetailActivityView.CID);
    }

    /**
     * 发消息
     * @param type
     * @param id 如果跟某个人发的私信，就是填入用户id，如果跟班级发的公告就是填入cid
     * @param content
     */
    public void postNotice(String type, String id, String content){
        String typeId = id;
        if (id.equals("")){
            typeId = cid;
        }

        model.postMessage(type, typeId, content).subscribe(new FinalSubscriber<String>(this, data->{
            v.postSuccess();
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
