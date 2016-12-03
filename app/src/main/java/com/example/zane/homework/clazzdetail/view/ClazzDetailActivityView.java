package com.example.zane.homework.clazzdetail.view;

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
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.ClazzDetailFragViewPagerAdapter;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostHomeWorkActivity;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailPostNoticeActivity;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.data.sp.MySharedPre;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
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

    public static final String COURSENAME = "courseName";
    public static final String CID = "cid";
    public static final String JID = "jid";

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

    private String courseName;

    @Override
    public int getRootViewId() {
        return R.layout.activity_clazzdetail;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init(String cid, String jid) {

        collClazzdetail.setTitle("");
        collClazzdetail.setExpandedTitleColor(this.activity.getResources().getColor(R.color.transparent));
        activity.setSupportActionBar(toolbarClazzdetail);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textviewClazzdetailCoursename.setPadding(0, 0, 0, JUtils.px2dip(JUtils.getStatusBarHeight()));

        RxToolbar.navigationClicks(toolbarClazzdetail).subscribe(aVoid -> {
            activity.finish();
        });

        RxView.clicks(fabClazzdetail).subscribe(aVoid -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                builder.setItems(new String[]{"发布作业", "发布公告"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intentWork = new Intent(activity, ClazzDetailPostHomeWorkActivity.class);
                                intentWork.putExtra(JID, jid);
                                activity.startActivity(intentWork);
                                break;
                            case 1:
                                Intent intent = new Intent(activity, ClazzDetailPostNoticeActivity.class);
                                intent.putExtra(CID, cid);
                                activity.startActivity(intent);
                                break;
                        }
                    }
                }).show();
            } else {
                builder.setItems(new String[]{"查看课程详情"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(activity, OtherInfoActivity.class);
                                intent.putExtra(COURSENAME, courseName);
                                activity.startActivity(intent);
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
        this.courseName = courseName;

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

    public void initCallBack(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.postponeEnterTransition();
        }
//        //防止闪屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
