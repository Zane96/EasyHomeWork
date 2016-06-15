package com.example.zane.homework.login.presenters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.login.view.LoginView;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class LoginFragment extends BaseFragmentPresenter<LoginView>{

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
