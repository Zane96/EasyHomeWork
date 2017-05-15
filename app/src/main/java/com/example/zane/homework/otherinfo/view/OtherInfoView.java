package com.example.zane.homework.otherinfo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.utils.RandomBackImage;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class OtherInfoView extends BaseActivityView {


    @Bind(R.id.imageview_otherinfo_top)
    ImageView imageviewOtherinfoTop;
    @Bind(R.id.imageview_otherinfo_avatar)
    ImageView imageviewOtherinfoAvatar;
    @Bind(R.id.toolbar_otherinfo)
    Toolbar toolbarOtherinfo;
    @Bind(R.id.textview_otherifo_name)
    TextView textviewOtherifoName;
    @Bind(R.id.textview_otherinfo_self)
    TextView textviewOtherinfoSelf;
    @Bind(R.id.text_otherinfo_coursename)
    TextView textOtherinfoCoursename;
    @Bind(R.id.text_otherinfo_total)
    TextView textOtherinfoTotal;
    @Bind(R.id.text_otherinfo_score)
    TextView textOtherinfoScore;
    @Bind(R.id.text_otherinfo_absent)
    TextView textOtherinfoAbsent;

    private OtherInfoActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_otherinfo;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (OtherInfoActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        //super.initToolbar(activity, toolbarOtherinfo, "成员信息");
    }

    public void init(String name, String selfInfo, int avatar) {
        initToolbar();
        textviewOtherifoName.setText(name);
        textviewOtherinfoSelf.setText(selfInfo);
        imageviewOtherinfoTop.setImageResource(RandomBackImage.getRandomAvatar());
        Glide.with(App.getInstance())
                .load(avatar)
                .transform(new CircleTransform(App.getInstance()))
                .into(imageviewOtherinfoAvatar);
    }

    public void setData(String couseName, String total, String score, String absent) {
        textOtherinfoCoursename.setText(couseName);
        textOtherinfoScore.setText(score);
        textOtherinfoTotal.setText(total);
        textOtherinfoAbsent.setText(absent);
    }

}
