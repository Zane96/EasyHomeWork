package com.example.zane.homework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.event.ActivityReenterEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivityPresenter<MainView>
        implements NavigationView.OnNavigationItemSelectedListener {

    private ClazzFragPresenter clazzFragPresenter;
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
    public void inCreat(Bundle bundle) {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void initCallBack(){
        //监听reenter的状态
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
                    getWindow().setExitTransition(null);
                    View statusBar = findViewById(android.R.id.statusBarBackground);
                    View navigationBar = findViewById(android.R.id.navigationBarBackground);
                    if (statusBar != null){
                        //应该系统控件默认set了transName的吧。。。。
                        names.add(statusBar.getTransitionName());
                        sharedElements.put(statusBar.getTransitionName(), statusBar);
                    }
                    if (navigationBar != null){
                        names.add(navigationBar.getTransitionName());
                        sharedElements.put(navigationBar.getTransitionName(), navigationBar);
                    }
                }
            }
        };
        setExitSharedElementCallback(mCallback);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_class) {
            // Handle the camera action
        } else if (id == R.id.drawer_message) {

        } else if (id == R.id.drawer_info) {

        } else if (id == R.id.drawer_about) {

        } else if (id == R.id.drawer_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
