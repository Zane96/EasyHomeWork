package com.example.zane.homework.clazzdetail.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.view.StuWorkViewHolder;
import com.example.zane.homework.clazzdetail.view.TeaWorkViewHolder;
import com.example.zane.homework.entity.HomeWorkDetail;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailHomeWorkAdapter extends BaseListAdapterPresenter<HomeWorkDetail>{

    private ImageView button;

    public ClazzDetailHomeWorkAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).getModelViewType() == HomeWorkDetail.TEA_WORK){
            return HomeWorkDetail.TEA_WORK;
        } else {
            return HomeWorkDetail.STU_WORK;
        }
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        if (i == HomeWorkDetail.TEA_WORK){
            return new TeaWorkViewHolder(viewGroup, R.layout.item_homework);
        } else {
            return new StuWorkViewHolder(viewGroup, R.layout.item_homework);
        }
    }

    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, final int i) {
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
