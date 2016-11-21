package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostNoticeActivity;
import com.example.zane.homework.event.PostNoticeEvent;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.utils.TimeUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostNoticeView extends BaseViewImpl {

    @Bind(R.id.toolbar_postnotice)
    Toolbar toolbarPostnotice;
    @Bind(R.id.edit_text_posnotice)
    EditText editTextPosnotice;
    @Bind(R.id.button_postnotice)
    Button buttonPostnotice;
    @Bind(R.id.edit_postnotice)
    EditText editPostnotice;
    @Bind(R.id.textinput_postnotice)
    TextInputLayout textinputPostnotice;

    private ClazzDetailPostNoticeActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_postnotice;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (ClazzDetailPostNoticeActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        toolbarPostnotice.setTitle("发布作业");
        activity.setSupportActionBar(toolbarPostnotice);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPostnotice.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init() {
        initToolbar();

        RxTextView.textChanges(editPostnotice).subscribe(c -> {
            if (!JudgeSearch.isRight(c.toString())){
                textinputPostnotice.setError("用户名格式不正确");
            } else {
                initButton();
                textinputPostnotice.setErrorEnabled(false);
            }
        });
        initButton();
    }

    private void initButton(){
        RxView.clicks(buttonPostnotice).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid -> {
            String type = "person";
            String id = editPostnotice.getText().toString();
            if (id.equals("")){
                type = "class";
            }
            activity.postNotice(type, id, editTextPosnotice.getText().toString());
        });
    }

    public void postSuccess(){
        Snackbar.make(buttonPostnotice, "发布成功～", Snackbar.LENGTH_SHORT).show();
    }
}
