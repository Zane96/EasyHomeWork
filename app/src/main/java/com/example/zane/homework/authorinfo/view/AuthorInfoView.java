package com.example.zane.homework.authorinfo.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.authorinfo.AuthorInfoFragment;
import com.example.zane.homework.authorinfo.presenters.AuthorInfoActivity;
import com.example.zane.homework.base.BaseActivityView;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class AuthorInfoView extends BaseActivityView {

    @Bind(R.id.toolbar_authorinfo)
    Toolbar toolbarAuthorinfo;
    @Bind(R.id.fragment_replace_about)
    FrameLayout fragmentReplaceAbout;
    private AppCompatActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_authorinfo;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (AuthorInfoActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void initToolbar() {
        super.initToolbar(activity, toolbarAuthorinfo, "作者信息");
    }

    public void transToAboutFragment(AuthorInfoFragment fragment){
        activity.getFragmentManager().beginTransaction().replace(R.id.fragment_replace_about, fragment).commit();
    }
}
