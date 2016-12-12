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

    private SearchClassActivity activity;
    private static final String RIGHT_ASID = "123";

    @Override
    public int getRootViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (SearchClassActivity) activity;
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
            } else {
                View root = LayoutInflater.from(activity).inflate(R.layout.dialog_student_search, null);
                builder = new AlertDialog.Builder(activity).setView(root);
                addtion = (EditText) root.findViewById(R.id.edit_search_reason_stu);
            }

            builder.setPositiveButton("申请", ((dialog, which) -> {
                //申请加入班级
                if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                    Log.i("SearchClass", teaCourse.getText().toString() + " text");
                    activity.appToClazz(teaCourse.getText().toString(), addtion.getText().toString());
                } else {
                    activity.appToClazz("", addtion.getText().toString());
                }
            })).setNegativeButton("取消", (dialog, which) -> {}).create().show();
        });

//        if (activity.getIntent().getIntExtra("STUDENT", -1) == 0) {
//            editSearch.setHint("输入你想要创建的班级名称");
//            buttonCreatclazz.setVisibility(View.VISIBLE);
//            buttonCreatclazz.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (JudgeSearch.isRight(editSearch.getText().toString())){
//                        progressbarSearch.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //MockStudentData.clazzNames[MockStudentData.clazzNames.length] = editSearch.getText().toString();
//                                progressbarSearch.setVisibility(View.GONE);
//                                JUtils.Toast("创建成功, 等待老师加入~");
//                            }
//                        }, 1000);
//                    } else {
//                        textinputSearch.setError("你输入的班级名称不正确");
//                    }
//                }
//            });
//        } else if (activity.getIntent().getIntExtra("STUDENT", -1) == 1){
//            editSearch.setHint("输入你想要搜索的班级代号");
//            buttonCreatclazz.setHeight(0);
//            editSearch.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                }
//
//                @Override
//                public void onTextChanged(final CharSequence s, int start, int before, int count) {
//                    progressbarSearch.setVisibility(View.VISIBLE);
//                    TimerTask task = new TimerTask() {
//                        @Override
//                        public void run() {
//                            new Handler(activity.getMainLooper()).post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressbarSearch.setVisibility(View.GONE);
//                                    if (JudgeSearch.isRight(s.toString())) {
//                                        String str = s.toString();
//                                        if (str.equals(RIGHT_ASID)) {
//                                            textinputSearch.setErrorEnabled(false);
//                                            setSearchData(R.drawable.back1_sq, "信管实验班", "徐志", "2016年6月13日");
//                                        } else {
//                                            textinputSearch.setError("找不到搜索结果");
//                                            clazzItem.setVisibility(View.GONE);
//                                        }
//                                    } else {
//                                        clazzItem.setVisibility(View.GONE);
//                                        textinputSearch.setError("你的班级代号输入不规范");
//                                    }
//                                }
//                            });
//                        }
//                    };
//                    timer.schedule(task, 1000);
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });

//            clazzItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder builder;
//                    if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
//                        builder = new AlertDialog.Builder(activity).setView(R.layout.dialog_search);
//                    } else {
//                        builder = new AlertDialog.Builder(activity).setView(R.layout.dialog_student_search);
//                    }
//                    builder.setPositiveButton("申请", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialogClick();
//                        }
//                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    }).show();
//                }
//            });
        //}
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
