package com.example.zane.homework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.event.ActivityReenterEvent;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.example.zane.homework.login.presenters.LoginRegisterActivity;
import com.example.zane.homework.utils.MySharedPre;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivityPresenter<MainView>
        implements NavigationView.OnNavigationItemSelectedListener {

    private ClazzFragPresenter clazzFragPresenter;
    private InfoActivity infoActivity;
    private SharedElementCallback mCallback;
    private boolean isReenterState = false;


    @Override
    public Class<MainView> getRootViewClass() {
        return MainView.class;
    }

    public void initFragment(){
        clazzFragPresenter = ClazzFragPresenter.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.reFlashData(TeacherLogin.getInstacne().getUserName());
    }

    @Override
    public void inCreat(Bundle bundle) {
        Toolbar toolbar = (Toolbar) v.get(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCallBack();
        initFragment();
        v.init(clazzFragPresenter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                                                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    void initCallBack(){
        //监听reenter的状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCallback = new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    /**
                     * 如果是reenter状态,不用做任何事情,
                     * 因为系统会默认return/reenter的element就是exiting/enter中共享的
                     * 如果reenter的时候,需要共享的组件变了,那么就需要重新设置sharedElements了
                     */
                    if (isReenterState){
                        isReenterState = false;
                    } else {
                        //如果是exiting状态,除了后面要共享的ImageView,这里添加状态栏和导航栏
                        //防止闪屏
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setExitTransition(null);
                        }
                        View statusBar = findViewById(android.R.id.statusBarBackground);
                        View navigationBar = findViewById(android.R.id.navigationBarBackground);
                        if (statusBar != null){
                            //应该系统控件默认set了transName的吧。。。。
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                names.add(statusBar.getTransitionName());
                                sharedElements.put(statusBar.getTransitionName(), statusBar);
                            }
                        }
                        if (navigationBar != null){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                names.add(navigationBar.getTransitionName());
                                sharedElements.put(navigationBar.getTransitionName(), navigationBar);
                            }
                        }
                    }
                }
            };
            setExitSharedElementCallback(mCallback);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        //推迟绘制
        postponeEnterTransition();
        isReenterState = true;
        EventBus.getDefault().post(new ActivityReenterEvent());
    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_class) {
            v.transToClazzFragment(clazzFragPresenter);
        } else if (id == R.id.drawer_message) {
            v.transToMessage();
        } else if (id == R.id.drawer_info) {
            v.transToInfoActivity();
        } else if (id == R.id.drawer_about) {
            v.transToAuthorInfo();
        } else if (id == R.id.drawer_setting) {

        } else if (id == R.id.drawer_login){
            startActivity(new Intent(this, LoginRegisterActivity.class));
            MySharedPre.getInstance().setLogin(false);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
