package com.example.zane.homework.message.view;

import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.utils.RandomBackImage;

/**
 * Created by Zane on 2016/12/3.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuApplyViewHolder extends BaseListViewHolderImpl<ShowApply.DataEntity.StuEntity>{
    private ImageView avatar;
    private TextView realName;
    private TextView intro;
    private Button state;
    private TextView addtion;

    public StuApplyViewHolder(ViewGroup parent, @LayoutRes int res) {
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
    public void setData(ShowApply.DataEntity.StuEntity data) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        realName.setText(data.getRealname());
        intro.setText(data.getSelfintro());
        addtion.setText("无");
        if ("true".equals(data.getDone())){
            state.setBackgroundResource(R.drawable.round_state_done);
            state.setText("已处理");
            state.setClickable(false);
        } else {
            state.setBackgroundResource(R.drawable.round_shape);
            state.setText("未处理");
        }
    }

    public void applySuccess(){
        state.setBackgroundResource(R.drawable.round_state_done);
        state.setText("已处理");
        state.setClickable(false);
        Snackbar.make(state, "同意已处理", Snackbar.LENGTH_SHORT).show();
    }
}
