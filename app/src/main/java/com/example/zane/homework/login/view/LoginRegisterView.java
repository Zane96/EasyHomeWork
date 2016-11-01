package com.example.zane.homework.login.view;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.event.LoginEvent;
import com.example.zane.homework.event.RegisterEvent;
import com.example.zane.homework.login.presenters.LoginFragment;
import com.example.zane.homework.login.presenters.RegisterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class LoginRegisterView extends BaseViewImpl {
    @Bind(R.id.toolbar_login_register)
    Toolbar toolbarLoginRegister;
    @Bind(R.id.fragment_login_replace)
    FrameLayout fragmentLoginReplace;
    private AppCompatActivity activity;

    public LoginRegisterView(){
        EventBus.getDefault().register(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_login_register;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar(){
        toolbarLoginRegister.setTitle("登录注册");
        activity.setSupportActionBar(toolbarLoginRegister);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLoginRegister.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init(LoginFragment fragment){
        initToolbar();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login_replace, fragment);
        transaction.commit();
    }

    public void transToRegister(RegisterFragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login_replace, fragment);
        transaction.commit();
    }

    public void transToLogin(LoginFragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login_replace, fragment);
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegister(RegisterEvent event){
        transToRegister(RegisterFragment.newInstance());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent event){
        transToLogin(LoginFragment.newInstance());
    }
}
