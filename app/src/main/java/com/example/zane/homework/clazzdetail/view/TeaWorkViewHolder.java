package com.example.zane.homework.clazzdetail.view;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.NoDueHomeWork;
import com.example.zane.homework.entity.HomeWorkDetail;
import com.example.zane.homework.event.FinishUpLoadEvent;
import com.example.zane.homework.event.UpLoadFileEvent;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class TeaWorkViewHolder extends BaseListViewHolderImpl<NoDueHomeWork.DataEntity>{

    private TextView percentage;
    private TextView workName;
    private TextView endTime;
    private ImageView button;

    public TeaWorkViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        percentage = get(R.id.textview_item_percentage);
        workName = get(R.id.textview_item_workname);
        endTime = get(R.id.textview_item_endtime);
        button = get(R.id.button_post_homework);
    }

    @Override
    public void setData(NoDueHomeWork.DataEntity dataEntity) {

        percentage.setText("百分比： " + dataEntity.getPercentage());
        endTime.setText("截止事件: " + dataEntity.getDeadline());
        workName.setText(dataEntity.getCourse());

        if (dataEntity.getAttachement().equals("")){
            button.setImageResource(R.drawable.ic_upload);
        } else {
            button.setImageResource(R.drawable.ic_upload_2);
        }

//        RxView.clicks(button).subscribe(aVoid -> {
//            UpLoadFileEvent event = new UpLoadFileEvent();
//            event.setAsId(Integer.parseInt(dataEntity.getAsid()));
//            event.setPosition(i);
//            EventBus.getDefault().post(event);
//        });
    }


}
