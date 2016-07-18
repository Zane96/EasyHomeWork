package com.example.zane.homework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.app.App;
import com.example.zane.homework.authorinfo.presenters.AuthorInfoActivity;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.config.MockStudentData;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.example.zane.homework.message.presenters.MessageActivity;
import com.example.zane.homework.utils.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MainView extends BaseViewImpl {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_fragment_replace)
    FrameLayout mainFragmentReplace;
    @Bind(R.id.content_main)
    ConstraintLayout contentMain;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private AppCompatActivity context;
    private LayoutInflater layoutInflater;
    ImageView imageView;
    TextView textView;

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setActivityContext(Activity activity) {
        context = (AppCompatActivity) activity;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init(ClazzFragPresenter clazzFragPresenter) {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_replace, clazzFragPresenter);
        fragmentTransaction.commit();
        View view = layoutInflater.inflate(R.layout.nav_header_main, navView);
        textView = (TextView) view.findViewById(R.id.text_main_welcom);
        imageView = (ImageView) view.findViewById(R.id.imageView_main_avatar);
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            textView.setText(TeacherLogin.getInstacne().getUserName() + "老师, " + "你好!");
            if (MockTeacherData.avatar != null){
                Glide.with(context)
                        .load(MockTeacherData.avatar)
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageView);
            } else {
                Glide.with(context)
                        .load(RandomBackImage.getRandomAvatar())
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageView);
            }
        } else {
            textView.setText(StudentLogin.getInstacne().getUserName() + "同学" + "你好!");
            if (MockStudentData.avatar != null){
                Glide.with(context)
                        .load(MockStudentData.avatar)
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageView);
            } else {
                Glide.with(context)
                        .load(RandomBackImage.getRandomAvatar())
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageView);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().setStatusBarColor(Color.TRANSPARENT);
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void transToClazzFragment(ClazzFragPresenter clazzFragPresenter) {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_replace, clazzFragPresenter);
        fragmentTransaction.commit();
    }

    public void transToInfoActivity(){
        context.startActivity(new Intent(context, InfoActivity.class));
    }

    public void reFlashData(String name){
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            textView.setText(TeacherLogin.getInstacne().getUserName() + "老师, " + "你好!");
            Glide.with(context)
                    .load(MockTeacherData.avatar)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imageView);
        } else {
            textView.setText(StudentLogin.getInstacne().getUserName() + "老师, " + "你好!");
            Glide.with(context)
                    .load(MockStudentData.avatar)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imageView);
        }
    }

    public void transToAuthorInfo(){
        context.startActivity(new Intent(context, AuthorInfoActivity.class));
    }
    public void transToMessage(){
        context.startActivity(new Intent(context, MessageActivity.class));
    }
}
