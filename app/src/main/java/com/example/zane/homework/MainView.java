package com.example.zane.homework;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.app.App;
import com.example.zane.homework.authorinfo.presenters.AuthorInfoActivity;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.example.zane.homework.utils.MySharedPre;

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

    public void init(ClazzFragPresenter clazzFragPresenter, String name, Uri image) {
        FragmentTransaction fragmentTransaction = context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_replace, clazzFragPresenter);
        fragmentTransaction.commit();
        View view = layoutInflater.inflate(R.layout.nav_header_main, navView);
        textView = (TextView) view.findViewById(R.id.text_main_welcom);
        imageView = (ImageView) view.findViewById(R.id.imageView_main_avatar);
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            textView.setText(MockTeacherData.userName + "老师, " + "你好!");
        } else {
            // TODO: 16/6/15 student
        }
        if (image != null){
            Glide.with(context)
                    .load(image)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imageView);
        }
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
            // TODO: 16/6/15 student
        }
    }

    public void transToAuthorInfo(){
        context.startActivity(new Intent(context, AuthorInfoActivity.class));
    }
}
