package com.example.zane.homework.clazzdetail.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.view.HomeWorkViewHolder;
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

public class ClazzDetailHomeWorkAdapter extends BaseListAdapterPresenter<HomeWorkDetail>{

    private ImageView button;

    public ClazzDetailHomeWorkAdapter(@NonNull Context mContext) {
        super(mContext);
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        return new HomeWorkViewHolder(viewGroup, R.layout.item_homework);
    }

    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, final int i) {
        baseListViewHolder.setData(mDatas.get(i));
        button = (ImageView) baseListViewHolder.get(R.id.button_post_homework);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpLoadFileEvent event = new UpLoadFileEvent();
                event.setAsId(mDatas.get(i).getAsId());
                event.setPosition(i);
                EventBus.getDefault().post(event);
            }
        });
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
