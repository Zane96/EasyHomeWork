package com.example.zane.homework;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.clazz.presenter.ClazzFragPresenter;
import com.example.zane.homework.data.bean.PerInfo;
import com.example.zane.homework.data.bean.QuitLogin;
import com.example.zane.homework.data.model.RegisterLoginModel;
import com.example.zane.homework.data.model.UserInfoModel;
import com.example.zane.homework.data.remote.CommonProvider;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.event.ActivityReenterEvent;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.example.zane.homework.login.presenters.LoginRegisterActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<MainView> implements View.OnClickListener{//NavigationView.OnNavigationItemSelectedListener

    private ClazzFragPresenter clazzFragPresenter;
    private InfoActivity infoActivity;
    private SharedElementCallback mCallback;
    private boolean isReenterState = false;
    private final UserInfoModel userInfoModel = UserInfoModel.getInstance();

    @Override
    public Class<MainView> getRootViewClass() {
        return MainView.class;
    }

    public void initFragment(){
        clazzFragPresenter = ClazzFragPresenter.newInstance();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注意这里设置了缓存策略
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            //这里并不应该使用Final......Subscriber
            v.reFlashData(MySharedPre.getInstance().getName());
        } else {
            StudentLogin studentLogin = StudentLogin.getInstacne();
            userInfoModel.getPerInfo(2).subscribe(data -> {
                if (data != null) {
                    studentLogin.setUserName(data.getName());
                    studentLogin.setName(data.getRealname());
                    studentLogin.setGender(data.getGender());
                    studentLogin.setSelfIntro(data.getSelfintro());
                    v.reFlashData(studentLogin.getUserName());
                }
            });
        }
    }

    //获取学生个人信息
    private void getStuInfo(){
        userInfoModel.getPerInfo(2).subscribe(this::setPerInfoData);
    }
    //获取老师个人信息
    private void getTeaInfo() {
        userInfoModel.getPerInfo(1).subscribe(this::setPerInfoData);
    }

    //存储个人信息
    private void setPerInfoData(PerInfo.DataEntity data){
        if (data != null) {
            MySharedPre sp = MySharedPre.getInstance();
            sp.setGender(data.getGender());
            sp.setName(data.getName());
            v.reFlashData(data.getName());
        }
    }

    @Override
    public void inCreat(Bundle bundle) {

        requestPermission();

        Toolbar toolbar = (Toolbar) v.get(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCallBack();
        initFragment();
        v.init(clazzFragPresenter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//        View include = findViewById(R.id.nav_hmenu);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.drawer_class);
        RelativeLayout relativeLayout1 = (RelativeLayout) findViewById(R.id.drawer_message);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.drawer_info);
        RelativeLayout relativeLayout3 = (RelativeLayout) findViewById(R.id.drawer_about);
        RelativeLayout relativeLayout4 = (RelativeLayout) findViewById(R.id.drawer_setting);
        RelativeLayout relativeLayout5 = (RelativeLayout) findViewById(R.id.drawer_login);
        relativeLayout.setOnClickListener(MainActivity.this);
        relativeLayout1.setOnClickListener(MainActivity.this);
        relativeLayout2.setOnClickListener(MainActivity.this);
        relativeLayout3.setOnClickListener(MainActivity.this);
        relativeLayout4.setOnClickListener(MainActivity.this);
        relativeLayout5.setOnClickListener(MainActivity.this);
//
//        //点击监听事件
//        ClickView(include);

        //获取用户信息
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            getTeaInfo();
        } else {
            getStuInfo();
        }
    }

    private void requestPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
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
    public IPersenter getPersenter() {
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

    /**
     * 退出登录，清除所有的cookie
     */
    private void quitLogin(){
        FinalSubscriber<QuitLogin.DataEntity> subscriber = new FinalSubscriber<>(MainActivity.this, o -> {
            MySharedPre.getInstance().setLogin(false);
            CommonProvider.cookieJar.clear();
            startActivity(new Intent(this, LoginRegisterActivity.class));
            finish();
        });
        RegisterLoginModel.getInstance().quitLogin().subscribe(subscriber);
    }

//    private void ClickView(View view){
//        ViewGroup viewGroup = (ViewGroup) view.getParent();
//        switch (viewGroup.getId()){
//            case R.id.nav_menu:
//                if (view.getId() == R.id.drawer_class) {
//                    v.transToClazzFragment(clazzFragPresenter);
//                } else if (view.getId() == R.id.drawer_message) {
//                    if (MySharedPre.getInstance().getIdentity().equals("student")){
//
//                    }
//                    v.transToMessage();
//                } else if (view.getId() == R.id.drawer_info) {
//                    v.transToInfoActivity();
//                } else if (view.getId() == R.id.drawer_about) {
//                    v.transToAuthorInfo();
//                } else if (view.getId() == R.id.drawer_setting) {
//
//                } else if (view.getId() == R.id.drawer_login){
//                    quitLogin();
//                }
//                break;
//
//        }
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.drawer_class) {
//            v.transToClazzFragment(clazzFragPresenter);
//        } else if (id == R.id.drawer_message) {
//            if (MySharedPre.getInstance().getIdentity().equals("student")){
//
//            }
//            v.transToMessage();
//        } else if (id == R.id.drawer_info) {
//            v.transToInfoActivity();
//        } else if (id == R.id.drawer_about) {
//            v.transToAuthorInfo();
//        } else if (id == R.id.drawer_setting) {
//
//        } else if (id == R.id.drawer_login){
//            quitLogin();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        //MenuItem item = menu.findItem(R.id.search_view);
//
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.drawer_class) {
                    v.transToClazzFragment(clazzFragPresenter);
                } else if (view.getId() == R.id.drawer_message) {
                    if (MySharedPre.getInstance().getIdentity().equals("student")){

                    }
                    v.transToMessage();
                } else if (view.getId() == R.id.drawer_info) {
                    v.transToInfoActivity();
                } else if (view.getId() == R.id.drawer_about) {
                    v.transToAuthorInfo();
                } else if (view.getId() == R.id.drawer_setting) {

                } else if (view.getId() == R.id.drawer_login){
                    quitLogin();
                }
//        switch (vi.getId()){
//            case R.id.nav_hmenu:
//                View view = findViewById(R.id.nav_hmenu);
//                if (view.getId() == R.id.drawer_class) {
//                    v.transToClazzFragment(clazzFragPresenter);
//                } else if (view.getId() == R.id.drawer_message) {
//                    if (MySharedPre.getInstance().getIdentity().equals("student")){
//
//                    }
//                    v.transToMessage();
//                } else if (view.getId() == R.id.drawer_info) {
//                    v.transToInfoActivity();
//                } else if (view.getId() == R.id.drawer_about) {
//                    v.transToAuthorInfo();
//                } else if (view.getId() == R.id.drawer_setting) {
//
//                } else if (view.getId() == R.id.drawer_login){
//                    quitLogin();
//                }
//                break;
//        }
    }
}
