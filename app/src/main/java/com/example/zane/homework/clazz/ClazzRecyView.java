package com.example.zane.homework.clazz;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.utils.RandomBackImage;
import com.example.zane.homework.utils.RandomColor;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzRecyView extends BaseListViewHolderImpl<Clazz>{

    private static final String TAG = ClazzRecyView.class.getSimpleName();

    private ImageView imageView;
    private TextView clazzName;
    private TextView courseName;
    private TextView owner;
    private CardView cardView;

    public ClazzRecyView(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        imageView = $(R.id.imageview_item_clazz);
        clazzName = $(R.id.textview_item_clazzname);
        courseName = $(R.id.textview_item_couresename);
        owner = $(R.id.textview_item_owner);
        cardView = $(R.id.clazz_item);
    }

    @Override
    public void setData(Clazz clazz) {
        Glide.with(App.getInstance())
                .load(clazz.getImage())
                .transform(new CircleTransform(App.getInstance()))
                .into(imageView);

        courseName.setText(clazz.getCourseName());
        clazzName.setText(clazz.getClassName());
        owner.setText(clazz.getOwner());
    }
}
