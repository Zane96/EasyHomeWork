package com.example.zane.homework.login.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easyimageprovider.download.execute.LoadTask;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.data.bean.Login;
import com.example.zane.homework.data.bean.Registe;
import com.example.zane.homework.data.model.RegisterLoginModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.remote.SubscriberOnNextListener;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.login.view.RegisterView;
import com.jakewharton.rxbinding.view.RxView;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class RegisterFragment extends BaseFragmentPresenter<RegisterView>{

    private RegisterLoginModel model = RegisterLoginModel.getInstance();
    private FinalSubscriber<Registe> registeSubscriber;
    private FinalSubscriber<Login.DataEntity> loginSubscriber;

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }

    @Override
    public Class<RegisterView> getRootViewClass() {
        return RegisterView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v.init();

        RxView.clicks(v.get(R.id.button_register)).subscribe(aVoid -> {

            loginSubscriber = new FinalSubscriber<Login.DataEntity>(getActivity(), o -> {
                if (v.getIdentity() == "1"){
                    MySharedPre.getInstance().setIdentity("teacher");
                } else if (v.getIdentity() == "2"){
                    MySharedPre.getInstance().setIdentity("student");
                }

                MySharedPre.getInstance().setLogin(true);

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            });

            model.register(v.getIdentity(), v.username(), v.realname(), v.password(), v.getGnder(),
                    v.introduce()).flatMap(integer -> {
                Log.i("RegisterFragment", integer+ " registe");
                        return model.login(v.username(), v.password(), v.getIdentity());
                    }).subscribe(loginSubscriber);

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (registeSubscriber != null){
            registeSubscriber.cancelProgress();
        }
        if (loginSubscriber != null){
            loginSubscriber.cancelProgress();
        }
    }
}
