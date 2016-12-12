package com.example.zane.homework.search.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.data.bean.SerClassInfo;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.search.view.SearchClassView;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.utils.RandomBackImage;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
import com.jude.utils.JUtils;

import rx.functions.Func1;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class SearchClassActivity extends BaseActivity<SearchClassView> {

    private ClassModel model;
    private FinalSubscriber<String> appSubscriber;
    private String classId;

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
        if (appSubscriber != null){
            appSubscriber.cancelProgress();
        }
    }

    @Override
    public Activity getContext() {
        return this;
    }

    /**
     * 老师/学生 申请加入班级
     * @param course
     * @param addtion
     */
    public void appToClazz(String course, String addtion){
        appSubscriber = new FinalSubscriber<>(this, s -> {
            v.showSuccess();
        });
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            model.teaAppClass(classId, "sir", course, addtion).subscribe(appSubscriber);
        } else {
            model.stuAppClass(classId, "stu").subscribe(appSubscriber);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_view));

//        RxSearchView.queryTextChangeEvents(searchView)
//                .filter(searchViewQueryTextEvent -> {
//                    if (JudgeSearch.isRight(searchViewQueryTextEvent.queryText().toString())){
//                        return true;
//                    }
//                    return false;})
//                .flatMap(charSequence -> {
//                    classId = charSequence.toString();
//                    return model.serClassInfo(charSequence.toString());})
//                .subscribe(new FinalSubscriber<SerClassInfo.DataEntity>(this, data -> {
//                    SerClassInfo.DataEntity serClassInfo = (SerClassInfo.DataEntity) data;
//                    v.setSearchData(RandomBackImage.getRandomAvatar(), serClassInfo.getName(), serClassInfo.getCreator(), serClassInfo.getCreatime());
//                }));

        RxSearchView.queryTextChanges(searchView).subscribe(charSequence -> {
            if (JudgeSearch.isRight(charSequence.toString())){
                //搜索班级信息
                classId = charSequence.toString();
                model.serClassInfo(charSequence.toString())
                        .subscribe(new FinalSubscriber<SerClassInfo.DataEntity>(this, data -> {
                            SerClassInfo.DataEntity serClassInfo = (SerClassInfo.DataEntity) data;
                            v.setSearchData(RandomBackImage.getRandomAvatar(), serClassInfo.getName(), serClassInfo.getCreator(), serClassInfo.getCreatime());
                        }));
                v.showError();
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
