package com.example.zane.homework.clazz.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazz.view.StuClazzViewHolder;
import com.example.zane.homework.data.bean.StuHaveClass;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuClazzAdapter extends BaseListAdapterPresenter<StuHaveClass.DataEntity>{

    public StuClazzAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        return new StuClazzViewHolder(viewGroup, R.layout.item_clazz);
    }

    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, int i) {
        baseListViewHolder.setData(mDatas.get(i));
    }

    @Override
    public int setHeadNum() {
        return 0;
    }

    @Override
    public int setFootNum() {
        return 0;
    }
}
