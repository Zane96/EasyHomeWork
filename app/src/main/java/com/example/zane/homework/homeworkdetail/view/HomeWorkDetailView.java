package com.example.zane.homework.homeworkdetail.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.clazzdetail.ClazzDetailFragViewPagerAdapter;
import com.example.zane.homework.homeworkdetail.HomeWorkDetailViewpagerAdapter;
import com.example.zane.homework.homeworkdetail.presenters.HomeWorkDetailActivity;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetailView extends BaseActivityView {

    @Bind(R.id.toolbar_homeworkdetail)
    Toolbar toolbarHomeworkdetail;
    @Bind(R.id.tablayout_homeworkdetail)
    TabLayout tablayoutHomeworkdetail;
    @Bind(R.id.viewpager_homeworkdetail)
    ViewPager viewpagerHomeworkdetail;
    @Bind(R.id.fab_homeworkdetail)
    FloatingActionButton fabHomeworkdetail;
    private AppCompatActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_homeworkdetail;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (HomeWorkDetailActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void initToolbar() {
        super.initToolbar(activity, toolbarHomeworkdetail, "完成名单");
    }

    public void initTabLayout(HomeWorkDetailViewpagerAdapter adapter){
        viewpagerHomeworkdetail.setAdapter(adapter);
        tablayoutHomeworkdetail.setupWithViewPager(viewpagerHomeworkdetail);
    }
}
