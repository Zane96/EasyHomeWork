package com.example.zane.homework.clazz.presenter;

import android.os.Bundle;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.base.BaseActivity;
import com.example.zane.homework.clazz.view.StuClazzView;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;

import java.util.List;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuClazzPresenter extends BaseActivity<StuClazzView> {

    private List<StuHaveClass.DataEntity> stuDatas;
    private ClassModel classModel = ClassModel.getInstance();
    private StuClazzAdapter stuClazzAdapter;

    @Override
    public Class<StuClazzView> getRootViewClass() {
        return StuClazzView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.init();
        stuClazzAdapter = new StuClazzAdapter(this);
        getData();
    }

    private void getData() {
        classModel.stuClass().subscribe(new FinalSubscriber<List<StuHaveClass.DataEntity>>(this, datas -> {
            stuDatas = (List<StuHaveClass.DataEntity>) datas;
            stuClazzAdapter.addAll(stuDatas);
            v.initRecy(stuClazzAdapter);
        }));
    }

    @Override
    public void inDestory() {

    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }
}
