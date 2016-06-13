package com.example.zane.homework.clazz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;

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
    private Context context;
    private LinearLayoutManager manager;
    private ProgressDialog progressDialog;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_clazz;
    }

    @Override
    public void setActivityContext(Activity activity) {
        context = activity;
    }

    public void initRecycleview(ClazzRecyAdapterPresenter adapterPresenter) {
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


}
