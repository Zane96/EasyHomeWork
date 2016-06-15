package com.example.zane.homework.search.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchView extends BaseViewImpl {
    @Bind(R.id.toolbar_search)
    Toolbar toolbarSearch;
    @Bind(R.id.edit_search)
    EditText editSearch;
    @Bind(R.id.textinput_search)
    TextInputLayout textinputSearch;
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
    @Bind(R.id.progressbar_search)
    ProgressBar progressbarSearch;

    private AppCompatActivity activity;
    private static final String RIGHT_ASID = "1";

    @Override
    public int getRootViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    private void initToolbar() {
        toolbarSearch.setTitle("搜索班级");
        activity.setSupportActionBar(toolbarSearch);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init() {
        initToolbar();
        final Timer timer = new Timer(true);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                progressbarSearch.setVisibility(View.VISIBLE);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        new Handler(activity.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                progressbarSearch.setVisibility(View.GONE);
                                if (JudgeSearch.isRight(s.toString())) {
                                    String str = s.toString();
                                    if (str.equals(RIGHT_ASID)) {
                                        textinputSearch.setErrorEnabled(false);
                                        setSearchData(R.drawable.back1_sq, "信管实验班", "徐志", "2016年6月13日");
                                    } else {
                                        textinputSearch.setError("找不到搜索结果");
                                        clazzItem.setVisibility(View.GONE);
                                    }
                                } else {
                                    clazzItem.setVisibility(View.GONE);
                                    textinputSearch.setError("你的班级代号输入不规范");
                                }
                            }
                        });
                    }
                };
                timer.schedule(task, 1000);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clazzItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity).setView(R.layout.dialog_search)
                        .setPositiveButton("申请", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogClick();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        });
    }

    private void dialogClick(){
        progressbarSearch.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                JUtils.Toast("申请成功!");
                progressbarSearch.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void setSearchData(int image, String clazzName, String owner, String creatTime) {
        clazzItem.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(image)
                .transform(new CircleTransform(App.getInstance()))
                .into(imageviewSearch);
        textviewSearchClazzname.setText(clazzName);
        textviewSearchOwner.setText(owner);
        textviewSearchCreatime.setText(creatTime);
    }
}
