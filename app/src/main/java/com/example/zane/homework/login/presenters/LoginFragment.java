package com.example.zane.homework.login.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.data.bean.Login;
import com.example.zane.homework.data.model.RegisterLoginModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.login.view.LoginView;
import com.jakewharton.rxbinding.view.RxView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class LoginFragment extends BaseFragment<LoginView> {

    private static RegisterLoginModel model = RegisterLoginModel.getInstance();

    public static final String LOGIN = "login";

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public Class<LoginView> getRootViewClass() {
        return LoginView.class;
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v.init();
    }

    public void login(){
        model.login(v.userName(), v.password(), v.getIdentity()+"")
                .subscribe(new FinalSubscriber<Login.DataEntity>(this, o -> {
                    if (v.getIdentity() == 1){
                        MySharedPre.getInstance().setIdentity("teacher");
                    } else if (v.getIdentity() == 2){
                        MySharedPre.getInstance().setIdentity("student");
                    }

                    Login.DataEntity data = (Login.DataEntity) o;

                    MySharedPre.getInstance().setSid(data.getSessionid());
                    MySharedPre.getInstance().setLogin(true);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra(LoginRegisterActivity.LOGIN, "first");
                    startActivity(intent);
                    getActivity().finish();
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
