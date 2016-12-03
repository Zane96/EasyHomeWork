package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.PerInfo;
import com.example.zane.homework.data.model.UserInfoModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.info.view.ChangeOtherInfoView;
import com.example.zane.homework.info.view.InfoView;
import com.jude.utils.JUtils;

import java.io.EOFException;

import rx.Subscriber;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class InfoActivity extends BaseActivityPresenter<InfoView> {

    private static final String TAG = InfoActivity.class.getSimpleName();
    private final UserInfoModel model = UserInfoModel.getInstance();
    //private FinalSubscriber<PerInfo.DataEntity> subscriber;

    @Override
    public Class<InfoView> getRootViewClass() {
        return InfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        MySharedPre sp = MySharedPre.getInstance();
        v.refreshData(sp.getName(), sp.getRealName(), sp.getGender(), sp.getIntro());
    }

    @Override
    public void inDestory() {
//        if (subscriber != null){
//            subscriber.cancelProgress();
//        }
    }

    @Override
    public Activity getContext() {
        return this;
    }

//    public void refreshData(){
//        int identity = 1;
//        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
//            identity = 1;
//        } else {
//            identity = 2;
//        }
//        subscriber = new FinalSubscriber<>(this, data -> {
//            PerInfo.DataEntity dataEntity = (PerInfo.DataEntity) data;
//            MySharedPre sp = MySharedPre.getInstance();
//            v.refreshData(dataEntity.getName(), dataEntity.getRealname(), dataEntity.getGender(), dataEntity.getSelfintro());
//            sp.setRealName(dataEntity.getRealname());
//            sp.setName(dataEntity.getName());
//            sp.setGender(dataEntity.getGender());
//            sp.setIntro(dataEntity.getSelfintro());
//        });
//        model.getPerInfo(identity).subscribe(subscriber);
//    }

    private void changeInfo(String flag, String name, String realName, String intro, String gender){
        MySharedPre sp = MySharedPre.getInstance();

        model.modiPerInfo(flag, gender, realName, name, intro).subscribe(str -> {
            v.showSuccess(gender, realName, name, intro);
            sp.setRealName(realName);
            sp.setName(name);
            sp.setIntro(intro);
            sp.setGender(gender);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            String name = v.getName();
            String realName = v.getRealName();
            String intro = v.getIntro();
            String gender = v.getGneder();
            String changData = data.getStringExtra(ChangeOtherInfoView.OTHER_RESULT);
            String flag;
            switch (requestCode) {
                case InfoView.USERNAME_CODE:
                    name = changData;
                    break;
                case InfoView.NAME_CODE:
                    realName = changData;
                    break;
                case InfoView.SELFINTRO_CODE:
                    intro = changData;
                    break;
                case InfoView.GENDER_CODE:
                    gender = changData;
                    break;
                default:
                    v.OnActivityResult(requestCode, resultCode, data);
                    break;
            }

            if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                flag = "1";
            } else {
                flag = "2";
            }

            changeInfo(flag, name, realName, intro, gender);
        }
    }
}
