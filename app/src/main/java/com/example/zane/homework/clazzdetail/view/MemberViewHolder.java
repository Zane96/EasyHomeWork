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
import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.utils.RandomBackImage;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberViewHolder extends BaseListViewHolderImpl<ClassMemeber.DataEntity>{

    private ImageView avatar;
    private TextView name;
    private TextView number;
    private TextView selfInfo;

    public MemberViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        avatar = get(R.id.imageview_item_member);
        name = get(R.id.textview_item_membername);
        number = get(R.id.textview_item_sid);
        selfInfo = get(R.id.textview_item_selfinfo);
    }

    @Override
    public void setData(ClassMemeber.DataEntity memberDetail) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        name.setText(memberDetail.getName());
        number.setText("SID：" + memberDetail.getSid());
        selfInfo.setText(memberDetail.getSelfintro());
    }
}
