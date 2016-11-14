package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class WorkJudgeActivityView extends BaseViewImpl {

    @Bind(R.id.toolbar_workjudge)
    Toolbar toolbarWorkjudge;
    @Bind(R.id.image_workjudge_download)
    ImageView imageWorkjudgeDownload;
    @Bind(R.id.spinner_workjudge)
    Spinner spinnerWorkjudge;
    @Bind(R.id.edit_workjudge_addtion)
    EditText editWorkjudgeAddtion;
    @Bind(R.id.fab_workjudge)
    FloatingActionButton fabWorkjudge;

    private WorkJudgePresenter activity;
    private String degree;

    @Override
    public int getRootViewId() {
        return R.layout.activity_judgework_layout;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (WorkJudgePresenter) activity;
    }

    public void init(String name, String addtion, String attach){
        toolbarWorkjudge.setTitle("批改" + name + "同学作业");
        activity.setSupportActionBar(toolbarWorkjudge);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RxToolbar.navigationClicks(toolbarWorkjudge).subscribe(aVoid -> {
            activity.finish();
        });

        RxView.clicks(fabWorkjudge)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {activity.judgeWork(degree, editWorkjudgeAddtion.getText().toString());});

        spinnerWorkjudge.setOnItemClickListener(((parent, view1, position, id) -> {
            String[] degrees = activity.getResources().getStringArray(R.array.degree);
            degree = degrees[position];
        }));

        editWorkjudgeAddtion.setHint(addtion);

        if (attach != null){
            imageWorkjudgeDownload.setImageResource(R.drawable.d_green);
            imageWorkjudgeDownload.setClickable(true);
        } else {
            imageWorkjudgeDownload.setImageResource(R.drawable.d_gray);
            imageWorkjudgeDownload.setClickable(false);
        }

        RxView.clicks(imageWorkjudgeDownload)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {});
    }

    @Override
    public void onPresenterDestory() {

    }
}
