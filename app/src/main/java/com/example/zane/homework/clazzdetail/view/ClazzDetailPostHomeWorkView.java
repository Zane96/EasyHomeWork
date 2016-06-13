package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.utils.FileUtils;
import com.example.zane.homework.utils.TimeUtils;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailPostHomeWorkView extends BaseViewImpl {

    @Bind(R.id.toolbar_posthomework)
    Toolbar toolbarPosthomework;
    @Bind(R.id.button_choose_time)
    Button buttonChooseTime;
    @Bind(R.id.button_choose_file)
    Button buttonChooseFile;
    @Bind(R.id.button_posthomework)
    Button buttonPosthomework;
    @Bind(R.id.textview_time_choosed)
    TextView textviewTimeChoosed;
    @Bind(R.id.edit_text_posthomework)
    EditText editTextPosthomework;
    private AppCompatActivity activity;
    private String endTime;
    private String file = "null";

    @Override
    public int getRootViewId() {
        return R.layout.activity_posthomework;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    private void initToolbar() {
        toolbarPosthomework.setTitle("发布作业");
        activity.setSupportActionBar(toolbarPosthomework);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPosthomework.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init() {
        initToolbar();
        final Calendar calendar = Calendar.getInstance();
        buttonChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        endTime = TimeUtils.DateFormat(year, monthOfYear + 1, dayOfMonth);
                        textviewTimeChoosed.setText(endTime);
                    }
                }, calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH) + 1).show();
            }
        });
        buttonChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.openFileManager(activity);
            }
        });
        buttonPosthomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.show();
                new Handler(activity.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.hide();
                        activity.finish();

                        PostHomeWorkEvent event = new PostHomeWorkEvent();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        String postTime = TimeUtils.DateFormat(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                        event.setPostTime(postTime);
                        event.setEndTime(endTime);
                        event.setAddtion(editTextPosthomework.getText().toString());
                        event.setAttachement(file);
                        EventBus.getDefault().post(event);

                        JUtils.Toast("发布成功~");
                    }
                }, 1500);
            }
        });
    }

    public void loading() {
        buttonChooseFile.setText("上传中");
        file = "loading";
    }

    public void loaded() {
        buttonChooseFile.setText("已上传");
        file = "loaded";
    }

}
