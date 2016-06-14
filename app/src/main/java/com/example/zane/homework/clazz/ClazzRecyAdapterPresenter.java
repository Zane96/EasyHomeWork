package com.example.zane.homework.clazz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.entity.Clazz;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

//public class ClazzRecyAdapterPresenter extends BaseListAdapterPresenter<Clazz>{
//
//    private static final String TAG = ClazzRecyAdapterPresenter.class.getSimpleName();
//
//    public ClazzRecyAdapterPresenter(@NonNull Context mContext) {
//        super(mContext);
//    }
//
//    @Override
//    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
//        return new ClazzRecyView(viewGroup, R.layout.item_clazz);
//    }
//
//    @Override
//    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, int i) {
//        mDatas.get(i).setPosition(i);
//        baseListViewHolder.setData(mDatas.get(i));
//    }
//
//    @Override
//    public int setHeadNum() {
//        return 0;
//    }
//
//    @Override
//    public int setFootNum() {
//        return 0;
//    }
//}
