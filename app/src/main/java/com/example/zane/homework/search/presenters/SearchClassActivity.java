package com.example.zane.homework.search.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.SerClassInfo;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.search.view.SearchClassView;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.utils.RandomBackImage;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchClassActivity extends BaseActivityPresenter<SearchClassView>{

    private ClassModel model;
    private FinalSubscriber<SerClassInfo.DataEntity> subscriber;

    @Override
    public Class<SearchClassView> getRootViewClass() {
        return SearchClassView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        model = ClassModel.getInstance();
    }

    @Override
    public void inDestory() {
        if (subscriber != null){
            subscriber.cancelProgress();
        }
    }

    @Override
    public Activity getContext() {
        return this;
    }

    public void appToClazz(){
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_view));
        //搜索班级信息
        subscriber = new FinalSubscriber<>(this, data -> {
            SerClassInfo.DataEntity serClassInfo = (SerClassInfo.DataEntity) data;
            v.setSearchData(RandomBackImage.getRandomAvatar(), serClassInfo.getName(), serClassInfo.getCreator(), serClassInfo.getCreatime());
        });
        RxSearchView.queryTextChanges(searchView).subscribe(c -> {
            if (JudgeSearch.isRight(c.toString())){
                model.serClassInfo(c.toString()).subscribe(subscriber);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
