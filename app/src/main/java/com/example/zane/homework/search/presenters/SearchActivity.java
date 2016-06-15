package com.example.zane.homework.search.presenters;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.search.view.SearchView;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchActivity extends BaseActivityPresenter<SearchView>{
    @Override
    public Class<SearchView> getRootViewClass() {
        return SearchView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }
}
