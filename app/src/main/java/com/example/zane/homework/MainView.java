package com.example.zane.homework;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.clazz.ClazzFragPresenter;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MainView extends BaseViewImpl{

    private AppCompatActivity context;

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setActivityContext(Activity activity) {
        context = (AppCompatActivity) activity;
    }

    public void init(ClazzFragPresenter clazzFragPresenter){
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_replace, clazzFragPresenter);
        fragmentTransaction.commit();
    }

    public void transToClazzFragment(ClazzFragPresenter clazzFragPresenter){
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_replace, clazzFragPresenter);
        fragmentTransaction.commit();
    }


}
