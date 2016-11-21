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
import android.widget.ImageView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;
import com.example.zane.homework.event.PostHomeWorkEvent;
import com.example.zane.homework.utils.FileUtils;
import com.example.zane.homework.utils.TimeUtils;
import com.jakewharton.rxbinding.view.RxView;
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
    ImageView buttonChooseFile;
    @Bind(R.id.button_posthomework)
    Button buttonPosthomework;
    @Bind(R.id.edit_text_posthomework)
    EditText editTextPosthomework;
    @Bind(R.id.edit_posthomework_percentage)
    EditText editPosthomeworkPercentage;

    private ClazzDetailPostHomeWorkActivity activity;
    private String endTime;
    private String file = "null";

    @Override
    public int getRootViewId() {
        return R.layout.activity_posthomework;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (ClazzDetailPostHomeWorkActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

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

        RxView.clicks(buttonChooseTime).subscribe(aVoid -> {
            new DatePickerDialog(activity, (view1, year, month, dayOfMonth) -> {
                endTime = TimeUtils.DateFormat(year, month + 1, dayOfMonth);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1).show();
        });

        RxView.clicks(buttonChooseFile).subscribe(aVoid -> {
            FileUtils.openFileManager(activity);
            loading();
        });

        RxView.clicks(buttonPosthomework).subscribe(aVoid -> {
            activity.startPostWork(editPosthomeworkPercentage.getText().toString(),
                    endTime, editTextPosthomework.getText().toString());
        });
    }

    private void loading() {
        buttonChooseFile.setImageResource(R.drawable.ic_upload_1);
        file = "loading";
    }

    public void loaded() {
        buttonChooseFile.setImageResource(R.drawable.ic_upload_2);
        file = "loaded";
    }

}
