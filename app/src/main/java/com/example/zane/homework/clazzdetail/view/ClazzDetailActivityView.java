package com.example.zane.homework.clazzdetail.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.ClazzDetailFragViewPagerAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailActivityView extends BaseViewImpl {

    @Bind(R.id.imageview_clazzdetail_top)
    ImageView imageviewClazzdetailTop;
    @Bind(R.id.toolbar_clazzdetail)
    Toolbar toolbarClazzdetail;
    @Bind(R.id.textview_clazzdetail_classname)
    TextView textviewClazzdetailClassname;
    @Bind(R.id.textview_clazzdetail_coursename)
    TextView textviewClazzdetailCoursename;
    @Bind(R.id.tablayout_clazzdetail)
    TabLayout tablayoutClazzdetail;
    @Bind(R.id.fab_clazzdetail)
    FloatingActionButton fabClazzdetail;
    @Bind(R.id.viewpager_clazzdetail)
    ViewPager viewpagerClazzdetail;
    @Bind(R.id.coll_clazzdetail)
    CollapsingToolbarLayout collClazzdetail;

    private AppCompatActivity activity;
    private ProgressDialog progressDialog;

    @Override
    public int getRootViewId() {
        return R.layout.activity_clazzdetail;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    public void init() {
        progressDialog = new ProgressDialog(activity);

        collClazzdetail.setTitle("");
        collClazzdetail.setExpandedTitleColor(this.activity.getResources().getColor(R.color.transparent));
        activity.setSupportActionBar(toolbarClazzdetail);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarClazzdetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        fabClazzdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //activity.startActivity(new Intent(activity, ClazzDetailPostHomeWorkActivity.class));
                new AlertDialog.Builder(activity).setItems(new String[]{"发布作业", "发布公告"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                activity.startActivity(new Intent(activity, ClazzDetailPostHomeWorkActivity.class));
                                break;
                            case 1:
                                break;
                        }
                    }
                }).show();
            }
        });
    }

    public void setText(String clazzName, String courseName, int image) {
        textviewClazzdetailClassname.setText(clazzName);
        textviewClazzdetailCoursename.setText(courseName);
        int avatar = R.drawable.back1_re;
        switch (image) {
            case R.drawable.back1_sq:
                avatar = R.drawable.back1_re;
                break;
            case R.drawable.back2_sq:
                avatar = R.drawable.back2_re;
                break;
            case R.drawable.back3_sq:
                avatar = R.drawable.back3_re;
                break;
            case R.drawable.back4_sq:
                avatar = R.drawable.back4_re;
                break;
        }
        Glide.with(activity)
                .load(avatar)
                .centerCrop()
                .into(imageviewClazzdetailTop);
    }

    public void initTablayout(ClazzDetailFragViewPagerAdapter adapter) {
        viewpagerClazzdetail.setAdapter(adapter);
        tablayoutClazzdetail.setupWithViewPager(viewpagerClazzdetail);
    }

    public void showProgress() {
        progressDialog.show();
    }

    public void hideProgress() {
        progressDialog.hide();
    }
}
