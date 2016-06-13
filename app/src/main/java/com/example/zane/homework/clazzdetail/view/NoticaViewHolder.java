package com.example.zane.homework.clazzdetail.view;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.entity.NoticeDetail;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class NoticaViewHolder extends BaseListViewHolderImpl<NoticeDetail>{

    private TextView name;
    private TextView postTime;

    public NoticaViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        name = $(R.id.textview_item_notice);
        postTime = $(R.id.textview_notice_postime);
    }

    @Override
    public void setData(NoticeDetail noticeDetail) {
        name.setText(noticeDetail.getContent());
        postTime.setText(noticeDetail.getPostTime());
    }
}
