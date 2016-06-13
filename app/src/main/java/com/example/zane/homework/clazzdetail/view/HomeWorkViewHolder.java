package com.example.zane.homework.clazzdetail.view;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.entity.HomeWorkDetail;
import com.example.zane.homework.event.FinishUpLoadEvent;
import com.example.zane.homework.event.UpLoadFileEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkViewHolder extends BaseListViewHolderImpl<HomeWorkDetail>{

    private TextView postTime;
    private TextView workName;
    private TextView endTime;
    private Button button;

    public HomeWorkViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        postTime = $(R.id.textview_item_postime);
        workName = $(R.id.textview_item_workname);
        endTime = $(R.id.textview_item_endtime);
        button = $(R.id.button_post_homework);
    }

    @Override
    public void setData(final HomeWorkDetail homeWorkDetail) {
        postTime.setText(homeWorkDetail.getPubTimel());
        endTime.setText(homeWorkDetail.getDeadLine());
        workName.setText(homeWorkDetail.getAddtion());
        if (homeWorkDetail.getAttachement().equals("null")){
            button.setText("上传附件");
        } else if (homeWorkDetail.getAttachement().equals("loaded")){
            button.setText("已上传");
        } else {
            button.setText("上传中");
        }
    }
}
