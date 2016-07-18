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
import com.jude.utils.JUtils;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangePasswordView extends BaseViewImpl {
    @Bind(R.id.toolbar_changepassword)
    Toolbar toolbarChangepassword;
    @Bind(R.id.edit_change_newpassword)
    EditText editChangeNewpassword;
    @Bind(R.id.edit_change_newpassword_again)
    EditText editChangeNewpasswordAgain;
    @Bind(R.id.button_post_newpassword)
    Button buttonPostNewpassword;
    private AppCompatActivity activity;
    private ProgressDialog progressDialog;

    public static final String PASSWORD_RESULT = "passResult";

    @Override
    public int getRootViewId() {
        return R.layout.activity_changpassword;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        toolbarChangepassword.setTitle("修改信息");
        activity.setSupportActionBar(toolbarChangepassword);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChangepassword.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init(){
        initToolbar();
        progressDialog = new ProgressDialog(activity);
        buttonPostNewpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (editChangeNewpassword.getText().toString().equals(editChangeNewpasswordAgain.getText().toString())){
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.hide();
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra(PASSWORD_RESULT, editChangeNewpassword.getText().toString());
                            activity.setResult(Activity.RESULT_OK, intent);
                            activity.finish();
                        }
                    }, 1000);
                } else {
                    Snackbar.make(v, "前后密码填写不一致!~", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
