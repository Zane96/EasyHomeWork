package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.data.bean.PerInfo;
import com.example.zane.homework.data.model.UserInfoModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.info.view.InfoView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class InfoActivity extends BaseActivityPresenter<InfoView> {

    private static final String TAG = InfoActivity.class.getSimpleName();
    private final UserInfoModel model = UserInfoModel.getInstance();
    private FinalSubscriber<PerInfo.DataEntity> subscriber;

    @Override
    public Class<InfoView> getRootViewClass() {
        return InfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init(MySharedPre.getInstance().getName());
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

    public void refreshData(){
        int identity = 1;
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            identity = 1;
        } else {
            identity = 2;
        }
        subscriber = new FinalSubscriber<>(this, data -> {
            PerInfo.DataEntity dataEntity = (PerInfo.DataEntity) data;
            MySharedPre sp = MySharedPre.getInstance();
            v.refreshData(dataEntity.getName(), dataEntity.getRealname(), dataEntity.getGender(), dataEntity.getSelfintro());
            sp.setRealName(dataEntity.getRealname());
            sp.setName(dataEntity.getName());
            sp.setGender(dataEntity.getGender());
            sp.setIntro(dataEntity.getSelfintro());
        });
        model.getPerInfo(identity).subscribe(subscriber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        v.OnActivityResult(requestCode, resultCode, data);
    }
}
