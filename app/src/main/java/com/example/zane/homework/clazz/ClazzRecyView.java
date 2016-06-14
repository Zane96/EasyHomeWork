package com.example.zane.homework.clazz;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.event.ActivityExitingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.zane.homework.clazz.ClazzFragPresenter.CLAZZ_NAME;
import static com.example.zane.homework.clazz.ClazzFragPresenter.COURSE_NAME;
import static com.example.zane.homework.clazz.ClazzFragPresenter.IMAGE;
import static com.example.zane.homework.clazz.ClazzFragPresenter.POSITION_SHARE;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

//public class ClazzRecyView extends BaseListViewHolderImpl<Clazz>{
//
//    private static final String TAG = ClazzRecyView.class.getSimpleName();
//
//    private ImageView imageView;
//    private TextView clazzName;
//    private TextView courseName;
//    private TextView owner;
//    private CardView cardView;
//
//    public ClazzRecyView(ViewGroup parent, @LayoutRes int res) {
//        super(parent, res);
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void initView() {
//        imageView = $(R.id.imageview_item_clazz);
//        clazzName = $(R.id.textview_item_clazzname);
//        courseName = $(R.id.textview_item_couresename);
//        owner = $(R.id.textview_item_owner);
//        cardView = $(R.id.clazz_item);
//    }
//
//    @Override
//    public void setData(Clazz clazz) {
//        Glide.with(App.getInstance())
//                .load(clazz.getImage())
//                .transform(new CircleTransform(App.getInstance()))
//                .into(imageView);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            imageView.setTransitionName(clazz.getPosition()+"");
//        }
//
//        courseName.setText(clazz.getCourseName());
//        clazzName.setText(clazz.getClassName());
//        owner.setText(clazz.getOwner());
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onActivityExiting(ActivityExitingEvent event){
//        Activity activity = event.getActivity();
//        Clazz clazz = event.getClazz();
//        Intent intent = new Intent(activity, ClazzDetailActivityPresenter.class);
//        intent.putExtra(CLAZZ_NAME, clazz.getClassName());
//        intent.putExtra(COURSE_NAME, clazz.getCourseName());
//        intent.putExtra(IMAGE, clazz.getImage());
//        intent.putExtra(POSITION_SHARE, event.getPosition());
//        if (Build.VERSION.SDK_INT > 21) {
//            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity,
//                    imageView, imageView.getTransitionName()).toBundle());
//        }else {
//            activity.startActivity(intent);
//        }
//    }
//}
