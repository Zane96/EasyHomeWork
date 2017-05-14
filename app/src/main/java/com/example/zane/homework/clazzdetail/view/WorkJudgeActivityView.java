package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivityView;
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

public class WorkJudgeActivityView extends BaseActivityView {

    @Bind(R.id.toolbar_workjudge)
    Toolbar toolbarWorkjudge;
    @Bind(R.id.button_workjudge_download)
    Button buttonWorkjudgeDownload;
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
    public void injectPresenter(IPersenter iPersenter) {
        activity = (WorkJudgePresenter) iPersenter;
    }

    public void init(String name, String addtion, String attach, String attachDB){
        super.initToolbar(activity, toolbarWorkjudge, "批改" + name + "同学作业");

        RxView.clicks(fabWorkjudge)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {activity.judgeWork(degree, editWorkjudgeAddtion.getText().toString());});


        spinnerWorkjudge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] degrees = activity.getResources().getStringArray(R.array.degree);
                degree = degrees[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editWorkjudgeAddtion.setHint(addtion);

        if (attachDB != null) {
            if (attachDB.equals(attach)) {
                openFile();
            } else if (attach != null) {
                downloadFile();
            } else {
                buttonWorkjudgeDownload.setEnabled(false);
                Snackbar.make(fabWorkjudge, "该同学未上传作业", Snackbar.LENGTH_SHORT).show();
            }
        } else {
            downloadFile();
        }
    }

    public void downloadFile(){
        buttonWorkjudgeDownload.setText("下载");

        RxView.clicks(buttonWorkjudgeDownload)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {activity.downloadWork();});
    }

    public void openFile(){
        buttonWorkjudgeDownload.setText("打开");
        buttonWorkjudgeDownload.setEnabled(true);
        RxView.clicks(buttonWorkjudgeDownload)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {activity.openFile();});
    }

    public void judgeSuccess(){
        Snackbar.make(fabWorkjudge, "批改成功", Snackbar.LENGTH_SHORT).show();
    }

    public void downloading(){
        buttonWorkjudgeDownload.setText("下载中");
        buttonWorkjudgeDownload.setEnabled(false);
    }

    @Override
    public void onPresenterDestory() {

    }
}
