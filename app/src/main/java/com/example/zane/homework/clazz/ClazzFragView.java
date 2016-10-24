package com.example.zane.homework.clazz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.event.ActivityReenterEvent;
import com.example.zane.homework.search.presenters.SearchActivity;
import com.example.zane.homework.data.sp.MySharedPre;

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

    @Override
    public void onPresenterDestory() {
        EventBus.getDefault().unregister(this);
    }

    public void initRecycleview(ClazzFragPresenter.ClazzRecyAdapterPresenter adapterPresenter) {

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
                    //fabClazzfragment.show();
                    fabClazzfragment.animate()
                            .translationY(fabClazzfragment.getHeight() + fabMarginBottm)
                            .setInterpolator(new AccelerateInterpolator(2))
                            .start();
                } else {
                    //fabClazzfragment.hide();
                    fabClazzfragment.animate()
                            .translationY(0)
                            .setInterpolator(new DecelerateInterpolator(2))
                            .start();
                }

            }
        });


        fabClazzfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                    Intent intent = new Intent(context, SearchActivity.class);
                    intent.putExtra("STUDENT", 1);
                    context.startActivity(intent);
                } else {
                    new AlertDialog.Builder(context).setItems(new String[]{"创建班级", "搜索班级"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, SearchActivity.class);
                            switch (which){
                                case 0:
                                    intent.putExtra("STUDENT", 0);
                                    break;
                                case 1:
                                    intent.putExtra("STUDENT", 1);
                                    break;
                            }
                            context.startActivity(intent);
                        }
                    }).show();
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
