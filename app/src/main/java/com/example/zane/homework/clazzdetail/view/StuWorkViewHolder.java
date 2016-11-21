package com.example.zane.homework.clazzdetail.view;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.GetNoDueWork;

/**
 * Created by Zane on 2016/11/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuWorkViewHolder extends BaseListViewHolderImpl<GetNoDueWork.DataEntity>{

    private TextView percentage;
    private TextView addtion;
    private TextView endTime;
    //private ImageView button;

    public StuWorkViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    protected void initView() {
        percentage = get(R.id.textview_item_percentage);
        addtion = get(R.id.textview_item_workname);
        endTime = get(R.id.textview_item_endtime);
        //button = get(R.id.button_post_homework);
    }

    @Override
    public void setData(GetNoDueWork.DataEntity dataEntity) {
        percentage.setText(dataEntity.getPercentage());
        addtion.setText(dataEntity.getAddtion());
        endTime.setText(dataEntity.getDeadline());

        // TODO: 2016/11/11 学生上传作业
    }
}
