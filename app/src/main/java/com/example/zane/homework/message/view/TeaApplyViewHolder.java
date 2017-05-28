package com.example.zane.homework.message.view;

import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;
import com.jakewharton.rxbinding.view.RxView;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class TeaApplyViewHolder extends BaseListViewHolderImpl<ShowApply.DataEntity.TeachEntity>{

    private ImageView avatar;
    private TextView realName;
    private TextView intro;
    private TextView state;
    private TextView addtion;

    public TeaApplyViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        avatar = get(R.id.image_message_top);
        realName = get(R.id.text_message_realname);
        state = get(R.id.button_message_state);
        intro = get(R.id.text_message_intro);
        addtion = get(R.id.text_message_reason);
    }

    @Override
    public void setData(ShowApply.DataEntity.TeachEntity data) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        realName.setText(data.getRealname());
        intro.setText(data.getSelfintro());
        addtion.setText(data.getAddtion());

        if ("true".equals(data.getDone())){
            state.setText("已处理");
        } else {
            state.setText("未处理");
        }
    }

    public void applySuccess(){
        state.setText("已处理");
        Snackbar.make(state, "同意已处理", Snackbar.LENGTH_SHORT).show();
    }
}
