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
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.utils.RandomBackImage;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberViewHolder extends BaseListViewHolderImpl<MemberDetail>{

    private ImageView avatar;
    private TextView name;
    private TextView number;

    public MemberViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        avatar = $(R.id.imageview_item_member);
        name = $(R.id.textview_item_membername);
        number = $(R.id.textview_item_membernumber);
    }

    @Override
    public void setData(MemberDetail memberDetail) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomImage())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        name.setText(memberDetail.getName());
        number.setText(memberDetail.getNumber());
    }
}
