package com.example.zane.homework.search.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.search.presenters.SearchClassActivity;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.data.sp.MySharedPre;
import com.jakewharton.rxbinding.view.RxView;
import com.jude.utils.JUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchClassView extends BaseActivityView {

    @Bind(R.id.toolbar_search)
    Toolbar toolbarSearch;
    @Bind(R.id.imageview_search)
    ImageView imageviewSearch;
    @Bind(R.id.clazz_item)
    CardView clazzItem;
    @Bind(R.id.textview_search_clazzname)
    TextView textviewSearchClazzname;
    @Bind(R.id.textview_search_owner)
    TextView textviewSearchOwner;
    @Bind(R.id.textview_search_creatime)
    TextView textviewSearchCreatime;

    private EditText teaCourse;
    private EditText addtion;
    private EditText total;

    private SearchClassActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (SearchClassActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init() {
        super.initToolbar(activity, toolbarSearch, "搜索班级");
        RxView.clicks(clazzItem).subscribe(aVoid -> {
            AlertDialog.Builder builder;

            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
                View root = LayoutInflater.from(activity).inflate(R.layout.dialog_search, null);
                builder = new AlertDialog.Builder(activity).setView(root);
                teaCourse = (EditText) root.findViewById(R.id.edit_search_course);
                addtion = (EditText) root.findViewById(R.id.edit_search_reason);
                total = (EditText) root.findViewById(R.id.edit_search_total);
            } else {
                View root = LayoutInflater.from(activity).inflate(R.layout.dialog_student_search, null);
                builder = new AlertDialog.Builder(activity).setView(root);
                addtion = (EditText) root.findViewById(R.id.edit_search_reason_stu);
            }

            builder.setPositiveButton("申请", ((dialog, which) -> {
                //申请加入班级
                if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                    activity.appToClazz(teaCourse.getText().toString(), addtion.getText().toString(), total.getText().toString());
                } else {
                    activity.appToClazz("", addtion.getText().toString(), "");
                }
            })).setNegativeButton("取消", (dialog, which) -> {}).create().show();
        });
    }


    public void setSearchData(int image, String clazzName, String owner, String creatTime) {
        clazzItem.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(image)
                .transform(new CircleTransform(App.getInstance()))
                .into(imageviewSearch);
        textviewSearchClazzname.setText(clazzName);
        textviewSearchOwner.setText(owner);
        textviewSearchCreatime.setText(creatTime);
    }

    public void showSuccess(){
        clazzItem.setVisibility(View.VISIBLE);
        Snackbar.make(clazzItem, "申请成功", Snackbar.LENGTH_SHORT).show();
    }

    public void showError(){
        clazzItem.setVisibility(View.GONE);
    }
}
