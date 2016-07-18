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

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangeOtherInfoView extends BaseViewImpl {
    @Bind(R.id.edit_change_otherinfo)
    EditText editChangeOtherinfo;
    @Bind(R.id.button_post_newotherinfo)
    Button buttonPostNewotherinfo;
    @Bind(R.id.toolbar_changotherinfo)
    Toolbar toolbarChangotherinfo;
    private AppCompatActivity activity;
    private ProgressDialog progressDialog;

    public static final String OTHER_RESULT = "otherResult";

    @Override
    public int getRootViewId() {
        return R.layout.activity_changotherinfo;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        toolbarChangotherinfo.setTitle("搜索班级");
        activity.setSupportActionBar(toolbarChangotherinfo);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChangotherinfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init(String oldData){
        initToolbar();
        editChangeOtherinfo.setHint(oldData.toString());
        progressDialog = new ProgressDialog(activity);
        buttonPostNewotherinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.hide();
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra(OTHER_RESULT, editChangeOtherinfo.getText().toString());
                        activity.setResult(Activity.RESULT_OK, intent);
                        activity.finish();
                    }
                }, 1000);
            }
        });
    }
}
