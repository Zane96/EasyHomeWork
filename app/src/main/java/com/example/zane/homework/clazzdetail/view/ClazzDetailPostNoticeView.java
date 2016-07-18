package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.event.PostNoticeEvent;
import com.example.zane.homework.utils.TimeUtils;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

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
    private AppCompatActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_postnotice;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
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

    public void init(){
        initToolbar();
        buttonPostnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.show();
                new Handler(activity.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.hide();
                        activity.finish();

                        PostNoticeEvent event = new PostNoticeEvent();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        String postTime = TimeUtils.DateFormat(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        event.setPostTime(postTime);
                        event.setContent(editTextPosnotice.getText().toString());
                        EventBus.getDefault().post(event);

                        JUtils.Toast("发布成功~");
                    }
                }, 1500);
            }
        });
    }
}
