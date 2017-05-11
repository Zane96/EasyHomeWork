package com.example.zane.homework.info.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.info.presenters.ChangeOtherInfoActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangeOtherInfoView extends BaseActivityView {
    @Bind(R.id.edit_change_otherinfo)
    EditText editChangeOtherinfo;
    @Bind(R.id.button_post_newotherinfo)
    Button buttonPostNewotherinfo;
    @Bind(R.id.toolbar_changotherinfo)
    Toolbar toolbarChangotherinfo;
    private ChangeOtherInfoActivity activity;

    public static final String OTHER_RESULT = "otherResult";

    @Override
    public int getRootViewId() {
        return R.layout.activity_changotherinfo;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (ChangeOtherInfoActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        super.initToolbar(activity, toolbarChangotherinfo, "修改信息");
    }

    public void init(String oldData){
        initToolbar();
        editChangeOtherinfo.setHint(oldData.toString());
        RxView.clicks(buttonPostNewotherinfo).subscribe(aVoid -> {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra(OTHER_RESULT, editChangeOtherinfo.getText().toString());
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        });
    }
}
