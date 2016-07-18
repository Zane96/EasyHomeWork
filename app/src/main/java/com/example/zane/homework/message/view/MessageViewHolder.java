package com.example.zane.homework.message.view;

import android.os.Message;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.MessageDetail;
import com.example.zane.homework.utils.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;

import org.w3c.dom.Text;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageViewHolder extends BaseListViewHolderImpl<MessageDetail>{

    private ImageView avatar;
    private TextView action;
    private TextView state;
    private TextView className;
    private TextView owner;

    private TextView text4;

    public MessageViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        avatar = get(R.id.image_message_top);
        action = get(R.id.text_message_action);
        state = get(R.id.text_message_state);
        className = get(R.id.text_message_classname);
        owner = get(R.id.text_message_owner);
        text4 = get(R.id.text4_message);
    }

    @Override
    public void setData(MessageDetail message) {
        Glide.with(App.getInstance())
                .load(RandomBackImage.getRandomAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(avatar);
        if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
            action.setText(message.getAction());
            state.setText(message.getState());
            className.setText(message.getClassName());
            owner.setText(message.getOwner());
        } else {
            action.setText("老师申请加入");
            state.setText("已批准");
            className.setText("信管实验班");
            text4.setText("任教课程");
            owner.setText("计算机网络");
        }
    }
}
