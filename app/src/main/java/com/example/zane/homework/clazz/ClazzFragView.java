package com.example.zane.homework.clazz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.event.ActivityReenterEvent;

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
    private AppCompatActivity context;
    private LinearLayoutManager manager;
    private ProgressDialog progressDialog;

    public ClazzFragView(){
        EventBus.getDefault().register(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_clazz;
    }

    @Override
    public void setActivityContext(Activity activity) {
        context = (AppCompatActivity) activity;
    }

    public void initRecycleview(ClazzFragPresenter.ClazzRecyAdapterPresenter adapterPresenter) {
        manager = new LinearLayoutManager(App.getInstance());
        recycleviewClazzInfo.setAdapter(adapterPresenter);
        recycleviewClazzInfo.setLayoutManager(manager);
        fabClazzfragment.show();

        recycleviewClazzInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy <= 0) {
                    fabClazzfragment.show();
                } else {
                    fabClazzfragment.hide();
                }
            }
        });
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    public void dissmissProgress() {
        progressDialog.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityReenter(ActivityReenterEvent event){
        recycleviewClazzInfo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recycleviewClazzInfo.getViewTreeObserver().removeOnPreDrawListener(this);
                recycleviewClazzInfo.requestLayout();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    context.startPostponedEnterTransition();
                }
                return true;
            }
        });
    }
}
