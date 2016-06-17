package com.example.zane.homework.message.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.message.view.MessageViewHolder;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageRecyAdapter extends BaseListAdapterPresenter<MessageDetail>{

    public MessageRecyAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        return new MessageViewHolder(viewGroup, R.layout.item_message);
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
