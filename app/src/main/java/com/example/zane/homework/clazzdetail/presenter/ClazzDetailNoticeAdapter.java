package com.example.zane.homework.clazzdetail.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.view.NoticeFootViewHolder;
import com.example.zane.homework.clazzdetail.view.NoticeViewHolder;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailNoticeAdapter extends BaseListAdapterPresenter<GetMessage.DataEntity>{

    private static final int NORMAL = 1234;
    private static final int FOOT = 4321;

    /**
     * true需要显示foot，false不需要显示foot
     */
    private boolean isShouldLoad = true;

    public void setShouldLoad(boolean shouldLoad) {
        isShouldLoad = shouldLoad;
    }

    ClazzDetailNoticeAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDatas.size()){
            return NORMAL;
        } else {
            return FOOT;
        }
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        //return new NoticeViewHolder(viewGroup, R.layout.item_notice);
        if (i == NORMAL){
            return new NoticeViewHolder(viewGroup, R.layout.item_notice);
        } else {
            return new NoticeFootViewHolder(viewGroup, R.layout.item_notice_foot);
        }
    }

    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, int i) {
        if (i == mDatas.size()){
            NoticeFootViewHolder holder = (NoticeFootViewHolder) baseListViewHolder;
            Log.i("holderclazz", isShouldLoad+"");
            if (!isShouldLoad){
                holder.noNeedLoadMore();
            }
        } else {
            baseListViewHolder.setData(mDatas.get(i));
        }
    }

    @Override
    public int setHeadNum() {
        return 0;
    }

    @Override
    public int setFootNum() {
        return 1;
    }
}
