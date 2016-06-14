package com.example.zane.homework.clazzdetail.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SharedElementCallback;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.ClazzDetailFragViewPagerAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostNoticeActivity;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;
import java.util.Map;

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
    private SharedElementCallback mCallBack;

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
                                activity.startActivity(new Intent(activity, ClazzDetailPostNoticeActivity.class));
                                break;
                        }
                    }
                }).show();
            }
        });
    }

    Callback callback = new Callback() {
        @Override
        public void onSuccess() {
            startPost();
        }

        @Override
        public void onError() {
            startPost();
        }
    };

    public void setText(String clazzName, String courseName, int image, int position) {
        textviewClazzdetailClassname.setText(clazzName);
        textviewClazzdetailCoursename.setText(courseName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageviewClazzdetailTop.setTransitionName(position+"");
        }
        RequestCreator requestCreator;
        requestCreator = Picasso.with(activity).load(image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageviewClazzdetailTop.setTransitionName(String.valueOf(position));
        }

        requestCreator.into(imageviewClazzdetailTop, callback);
    }

    public void startPost(){
        imageviewClazzdetailTop.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                imageviewClazzdetailTop.getViewTreeObserver().removeOnPreDrawListener(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.startPostponedEnterTransition();
                }
                return true;
            }
        });
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initCallBack(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.postponeEnterTransition();
        }
//        //防止闪屏
        activity.getWindow().setEnterTransition(null);
        mCallBack = new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                imageviewClazzdetailTop = getImageView();
                if (imageviewClazzdetailTop == null){
                    names.clear();
                    sharedElements.clear();
                }
            }
        };
        activity.setEnterSharedElementCallback(mCallBack);
    }

    @Nullable
    private ImageView getImageView(){
        if (isInScreen(activity.getWindow().getDecorView(), imageviewClazzdetailTop)){
            return imageviewClazzdetailTop;
        }
        return null;
    }

    private boolean isInScreen(@NonNull View parent, @NonNull View imageView){
        Rect rect = new Rect();
        //获得decorview的可视矩形
        parent.getHitRect(rect);
        //看imageview在不在这个可视矩形里面
        return imageView.getLocalVisibleRect(rect);
    }

}
