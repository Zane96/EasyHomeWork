package com.example.zane.homework.clazzdetail.view;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.utils.RandomBackImage;

/**
 * 完成成绩的学生列表
 * Created by Zane on 2016/11/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class WorkMemberViewHolder extends BaseListViewHolderImpl<HoPerson.DataEntity>{

    private ImageView avatar;
    private TextView name;
    private TextView finishtime;
    private TextView grade;

    public WorkMemberViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    protected void initView() {
        avatar = get(R.id.imageview_item_member);
        name = get(R.id.textview_item_membername);
        finishtime = get(R.id.textview_item_membernumber);
        grade = get(R.id.textview_item_selfinfo);
    }

    @Override
    public void setData(HoPerson.DataEntity dataEntity) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        name.setText(dataEntity.getName());
        finishtime.setText("完成时间 " + dataEntity.getFinishtime());
        grade.setText("成绩 " + dataEntity.getGrade());
    }
}
