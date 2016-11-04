package com.example.zane.homework.login.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.login.view.LoginRegisterView;
import com.example.zane.homework.data.sp.MySharedPre;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class LoginRegisterActivity extends BaseActivityPresenter<LoginRegisterView>{

    private static final String TAG = LoginRegisterActivity.class.getSimpleName();

    @Override
    public Class<LoginRegisterView> getRootViewClass() {
        return LoginRegisterView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        if (MySharedPre.getInstance().getLogin()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            v.init(LoginFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        v.setToolbarTitle();
    }

    @Override
    public void inDestory() {
        v.unRegister();
    }

    @Override
    public Activity getContext() {
        return this;
    }
}
