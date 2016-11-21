package com.example.zane.homework.login.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
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

public class LoginFragment extends BaseFragmentPresenter<LoginView>{

    private static RegisterLoginModel model = RegisterLoginModel.getInstance();
    private FinalSubscriber<Login.DataEntity> subscriber;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public Class<LoginView> getRootViewClass() {
        return LoginView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v.init();
    }

    public void login(){
        subscriber = new FinalSubscriber<Login.DataEntity>(getActivity(), o -> {
            if (v.getIdentity() == 1){
                MySharedPre.getInstance().setIdentity("teacher");
            } else if (v.getIdentity() == 2){
                MySharedPre.getInstance().setIdentity("student");
            }

            MySharedPre.getInstance().setLogin(true);

            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        });

        model.login(v.userName(), v.password(), v.getIdentity()+"")
                .subscribe(subscriber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriber != null){
            subscriber.cancelProgress();
        }
    }
}
