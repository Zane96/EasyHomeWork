package com.example.zane.homework.clazz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazz.presenter.ClazzFragPresenter;
import com.example.zane.homework.clazz.presenter.StuClazzPresenter;
import com.example.zane.homework.event.ActivityReenterEvent;
import com.example.zane.homework.search.presenters.SearchClassActivity;
import com.example.zane.homework.data.sp.MySharedPre;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 */

public class ClazzFragView extends BaseViewImpl {

    @Bind(R.id.recycleview_clazz_info)
    RecyclerView recycleviewClazzInfo;
    @Bind(R.id.fab_clazzfragment)
    FloatingActionButton fabClazzfragment;
    @Bind(R.id.swiplayout_clazz_info)
    SwipeRefreshLayout swipeRefreshLayout;

    private ClazzFragPresenter presenter;
    private Activity activity;
    private LinearLayoutManager manager;

    public ClazzFragView(){
        EventBus.getDefault().register(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_clazz;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        presenter = (ClazzFragPresenter) iPersenter;
        activity = presenter.getActivity();
    }

    @Override
    public void onPresenterDestory() {
        EventBus.getDefault().unregister(this);
    }

    public void initRecycleview(ClazzFragPresenter.ClazzTeaRecyAdapterPresenter adapterPresenter) {
        manager = new LinearLayoutManager(App.getInstance());
        recycleviewClazzInfo.setAdapter(adapterPresenter);
        recycleviewClazzInfo.setLayoutManager(manager);
        fabClazzfragment.show();
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fabClazzfragment.getLayoutParams();
        final int fabMarginBottm = lp.bottomMargin;

        recycleviewClazzInfo.setHasFixedSize(true);
        recycleviewClazzInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    fabClazzfragment.animate()
                            .translationY(fabClazzfragment.getHeight() + fabMarginBottm)
                            .setInterpolator(new AccelerateInterpolator(1))
                            .start();
                } else {
                    fabClazzfragment.animate()
                            .translationY(0)
                            .setInterpolator(new DecelerateInterpolator(1))
                            .start();
                }
            }
        });
    }

    public void init(){

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshClazzData());

        RxView.clicks(fabClazzfragment).subscribe(aVoid -> {
            if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                Intent intent = new Intent(activity, SearchClassActivity.class);
                activity.startActivity(intent);
            } else {
                new AlertDialog.Builder(activity).setItems(new String[]{"创建班级", "我的班级", "搜索班级"}, (dialog, which) -> {
                    switch (which){
                        case 0:
                            View root = LayoutInflater.from(activity).inflate(R.layout.dialog_creatclazz, null);
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity).setView(root);
                            EditText className = (EditText) root.findViewById(R.id.edit_name);
                            EditText classDescription = (EditText) root.findViewById(R.id.edit_description);

                            builder.setPositiveButton("创建", (dialog1, which1) -> {
                                presenter.creatClazz(className.getText().toString(), classDescription.getText().toString());
                            }).setNegativeButton("取消", (dialog1, which1) -> {}).create().show();

                            break;
                        case 1:
                            activity.startActivity(new Intent(activity, StuClazzPresenter.class));
                            break;
                        case 2:
                            activity.startActivity(new Intent(activity, SearchClassActivity.class));
                            break;
                    }
                }).show();
            }
        });
    }

    /**
     * 取消swipe刷新
     */
    public void cancleSwiprefresh(){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showSuccess(){
        Snackbar.make(fabClazzfragment, "创建成功", Snackbar.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityReenter(ActivityReenterEvent event){
        recycleviewClazzInfo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recycleviewClazzInfo.getViewTreeObserver().removeOnPreDrawListener(this);
                recycleviewClazzInfo.requestLayout();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.startPostponedEnterTransition();
                }
                return true;
            }
        });
    }
}
