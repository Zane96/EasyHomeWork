package com.example.zane.homework.clazzdetail.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.view.MemberViewHolder;
import com.example.zane.homework.clazzdetail.view.WorkMemberViewHolder;
import com.example.zane.homework.entity.MemberDetail;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailMemberAdapter extends BaseListAdapterPresenter<MemberDetail>{

    public ClazzDetailMemberAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).getModelViewType() == MemberDetail.HOMEWORK){
            return MemberDetail.HOMEWORK;
        } else {
            return MemberDetail.MEMEBER;
        }
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        if (i == MemberDetail.MEMEBER){
            return new MemberViewHolder(viewGroup, R.layout.item_member);
        } else {
            return new WorkMemberViewHolder(viewGroup, R.layout.item_member);
        }
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
