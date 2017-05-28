package com.example.zane.homework.login.presenters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.example.zane.easymvp.base.IPersenter;
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
    public static final String LOGIN = "login";

    @Override
    public Class<LoginRegisterView> getRootViewClass() {
        return LoginRegisterView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if (MySharedPre.getInstance().getLogin()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(LOGIN, "not first");
            startActivity(intent);
            finish();
        } else {
            v.init(LoginFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void inDestory() {
        v.unRegister();
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
