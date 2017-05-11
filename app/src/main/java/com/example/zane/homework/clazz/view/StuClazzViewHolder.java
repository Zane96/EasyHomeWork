package com.example.zane.homework.clazz.view;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;

import butterknife.Bind;

/**
 * Created by Zane on 2017/5/11.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuClazzViewHolder extends BaseListViewHolderImpl<StuHaveClass.DataEntity>{

    private ImageView imageviewItemClazz;
    private TextView textviewItemClazzname;
    private TextView textviewItemCouresename;
    private TextView textviewItemOwner;
    private TextView textviewItemAsid;
    private ImageView imageOwner;

    public StuClazzViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    protected void initView() {
        imageviewItemClazz = get(R.id.imageview_item_clazz);
        textviewItemClazzname = get(R.id.textview_item_clazzname);
        textviewItemCouresename = get(R.id.textview_item_couresename);
        textviewItemOwner = get(R.id.textview_item_owner);
        imageOwner = get(R.id.image_item_owner);
        textviewItemAsid = get(R.id.textview_item_asid);
    }

    @Override
    public void setData(StuHaveClass.DataEntity dataEntity) {
        textviewItemOwner.setVisibility(View.GONE);
        if (!dataEntity.getCreator().equals("true")) {
            imageOwner.setVisibility(View.GONE);
        }

        textviewItemAsid.setText("CID: " + dataEntity.getCid());
        textviewItemClazzname.setText(dataEntity.getClassname());
        textviewItemCouresename.setText(dataEntity.getDescription());

        Glide.with(App.getInstance())
                .load(StudentLogin.getInstacne().getAvatar())
                .transform(new CircleTransform(App.getInstance()))
                .into(imageviewItemClazz);
    }
}
