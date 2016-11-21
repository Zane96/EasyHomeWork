package com.example.zane.homework.clazzdetail.view;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.entity.NoticeDetail;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class NoticeViewHolder extends BaseListViewHolderImpl<GetMessage.DataEntity>{

    private TextView name;
    private TextView mid;

    public NoticeViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        name = get(R.id.textview_item_notice);
        mid = get(R.id.textview_notice_mid);
    }

    @Override
    public void setData(GetMessage.DataEntity noticeDetail) {
        name.setText("消息公告");
        mid.setText("消息号：" + noticeDetail.getMid());
        //postTime.setText(noticeDetail.getPostTime());
    }
}
