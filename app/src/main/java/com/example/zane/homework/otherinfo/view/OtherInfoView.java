package com.example.zane.homework.otherinfo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.utils.RandomBackImage;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class OtherInfoView extends BaseViewImpl {
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
    @Bind(R.id.text_otherinfo_number)
    TextView textOtherinfoNumber;
    @Bind(R.id.text_otherinfo_score)
    TextView textOtherinfoScore;
    @Bind(R.id.text_otherinfo_noworktimes)
    TextView textOtherinfoNoworktimes;
    @Bind(R.id.text_otherinfo_worktimes)
    TextView textOtherinfoWorktimes;

    private AppCompatActivity activity;
    private ProgressDialog progressDialog;

    @Override
    public int getRootViewId() {
        return R.layout.activity_otherinfo;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    private void initToolbar() {
        progressDialog = new ProgressDialog(activity);
        toolbarOtherinfo.setTitle("");
        activity.setSupportActionBar(toolbarOtherinfo);
        activity.setSupportActionBar(toolbarOtherinfo);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarOtherinfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void init(String name, String selfInfo, int avatar){
        initToolbar();
        textviewOtherifoName.setText(name);
        textviewOtherinfoSelf.setText(selfInfo);
        imageviewOtherinfoTop.setImageResource(RandomBackImage.getRandomAvatar());
        Glide.with(App.getInstance())
                .load(avatar)
                .transform(new CircleTransform(App.getInstance()))
                .into(imageviewOtherinfoAvatar);
    }

    public void setData(String number, String score, String works, String noWorks){
        textOtherinfoNumber.setText(number);
        textOtherinfoScore.setText(score);
        textOtherinfoWorktimes.setText(works);
        textOtherinfoNoworktimes.setText(noWorks);
    }

    public void showProgress(){
        progressDialog.show();
    }

    public void hideProgress(){
        progressDialog.hide();
    }
}
