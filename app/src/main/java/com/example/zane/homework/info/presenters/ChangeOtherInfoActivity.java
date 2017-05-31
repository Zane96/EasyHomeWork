package com.example.zane.homework.info.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.data.model.UserInfoModel;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.info.view.ChangeOtherInfoView;
import com.example.zane.homework.info.view.InfoView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangeOtherInfoActivity extends BaseActivityPresenter<ChangeOtherInfoView>{

    private final UserInfoModel model = UserInfoModel.getInstance();

    @Override
    public Class<ChangeOtherInfoView> getRootViewClass() {
        return ChangeOtherInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        String oldData = getIntent().getStringExtra(InfoView.CHANGE_CONTENT);
        v.init(oldData);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }


    private void changeInfo(String flag, String name, String realName, String intro,String password){
        MySharedPre sp = MySharedPre.getInstance();

        model.modiPerInfo(flag, password, realName, name, intro).subscribe(str -> {
            v.showSuccess(password, realName, name, intro);
            sp.setRealName(realName);
            sp.setName(name);
            sp.setIntro(intro);
            //   sp.setGender(gender);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            String name = v.getName();
            String realName = v.getRealName();
            String intro = v.getIntro();
//            String gender = v.getGneder();
            String changData = data.getStringExtra(ChangeOtherInfoView.OTHER_RESULT);

            name = changData;
            realName = changData;
            intro = changData;
//            gender = changData;
            v.OnActivityResult(requestCode, resultCode, data);
            }
        String flag;
            if (MySharedPre.getInstance().getGender().equals("男")){
                flag = "1";
            } else {
                flag = "2";
            }

//            changeInfo(flag, name, realName, intro, password);
        }
}

