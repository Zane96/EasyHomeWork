package com.example.zane.homework.info.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.zane.easyimageprovider.builder.ImageProviderBuilder;
import com.example.zane.easyimageprovider.builder.core.EasyImage;
import com.example.zane.easyimageprovider.provider.listener.OnGetImageListener;
import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.info.presenters.ChangPasswordActivity;
import com.example.zane.homework.info.presenters.ChangeOtherInfoActivity;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class InfoView extends BaseViewImpl implements View.OnClickListener {

    private static final String TAG = InfoActivity.class.getSimpleName();
    public static final String CHANGE_CONTENT = "changeContent";
    public static final int USERNAME_CODE = 111;
    public static final int NAME_CODE = 222;
    public static final int SELFINTRO_CODE = 333;
    public static final int PASSWORD_CODE = 444;
    public static final int GENDER_CODE = 555;

    @Bind(R.id.imaget_self_avatar)
    ImageView imagetSelfAvatar;
    @Bind(R.id.card_info_avatar)
    CardView cardInfoAvatar;
    @Bind(R.id.text_info_username)
    TextView textInfoUsername;
    @Bind(R.id.card_info_username)
    CardView cardInfoUsername;
    @Bind(R.id.text_info_name)
    TextView textInfoName;
    @Bind(R.id.card_info_name)
    CardView cardInfoName;
    @Bind(R.id.text_info_selfintro)
    TextView textInfoSelfintro;
    @Bind(R.id.card_info_selfintro)
    CardView cardInfoSelfintro;
    @Bind(R.id.text_info_password)
    TextView textInfoPassword;
    @Bind(R.id.card_info_password)
    CardView cardInfoPassword;
    @Bind(R.id.text_info_gender)
    TextView textInfoGender;
    @Bind(R.id.card_info_gender)
    CardView cardInfoGender;
    @Bind(R.id.toolbar_info)
    Toolbar toolbarInfo;

    private InfoActivity activity;
    private EasyImage image;

    @Override
    public int getRootViewId() {
        return R.layout.activity_info;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (InfoActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    OnGetImageListener<Uri> listener = new OnGetImageListener<Uri>() {
        @Override
        public void getDataBack(Uri uri) {
            Looper.prepare();
            android.os.Handler handler = new android.os.Handler();
            Looper.loop();
            Glide.with(activity)
                    .load(uri)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imagetSelfAvatar);
        }
    };

    public void init() {

        toolbarInfo.setTitle("我的信息");
        activity.setSupportActionBar(toolbarInfo);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RxToolbar.navigationClicks(toolbarInfo).subscribe(aVoid -> {activity.finish();});

        cardInfoAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageProviderBuilder builder = new ImageProviderBuilder()
                                                             .with(activity)
                                                             .setGetImageListener("uri", listener)
                                                             .useCrop(200, 200);
                new AlertDialog.Builder(activity).setItems(new String[]{"相册", "相机"}, (dialog, which) -> {
                            switch (which) {
                                case 0:
                                    builder.useAlbum();
                                    break;
                                case 1:
                                    builder.useCamera();
                                    break;
                            }
                            image = EasyImage.creat(builder);
                            image.execute();
                        }).show();
            }
        });

        cardInfoGender.setOnClickListener(this);
        cardInfoName.setOnClickListener(this);
        //cardInfoPassword.setOnClickListener(this);
        cardInfoSelfintro.setOnClickListener(this);
        cardInfoUsername.setOnClickListener(this);
    }

    public void refreshData(String name, String realName, String gender, String intro){
        textInfoGender.setText(gender);
        textInfoName.setText(realName);
        textInfoSelfintro.setText(intro);
        textInfoUsername.setText(name);
    }

    public void OnActivityResult(int requestCode, int resultCode, Intent data) {
        if (image != null) {
            image.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, ChangeOtherInfoActivity.class);
        switch (v.getId()) {
            case R.id.card_info_password:
                Intent intentPassword = new Intent(activity, ChangPasswordActivity.class);
                intentPassword.putExtra(CHANGE_CONTENT, textInfoPassword.getText());
                activity.startActivityForResult(intentPassword, PASSWORD_CODE);
                break;
            case R.id.card_info_gender:
                intent.putExtra(CHANGE_CONTENT, textInfoGender.getText());
                activity.startActivityForResult(intent, GENDER_CODE);
                break;
            case R.id.card_info_name:
                intent.putExtra(CHANGE_CONTENT, textInfoName.getText());
                activity.startActivityForResult(intent, NAME_CODE);
                break;
            case R.id.card_info_selfintro:
                intent.putExtra(CHANGE_CONTENT, textInfoSelfintro.getText());
                activity.startActivityForResult(intent, SELFINTRO_CODE);
                break;
            case R.id.card_info_username:
                intent.putExtra(CHANGE_CONTENT, textInfoUsername.getText());
                activity.startActivityForResult(intent, USERNAME_CODE);
                break;
        }
    }

    public String getName(){
        return textInfoUsername.getText().toString();
    }

    public String getRealName(){
        return textInfoName.getText().toString();
    }

    public String getGneder(){
        return textInfoGender.getText().toString();
    }

    public String getIntro(){
        return textInfoSelfintro.getText().toString();
    }


    public void showSuccess(String gender, String realName, String name, String intro){
        Snackbar.make(textInfoGender, "信息修改成功～", Snackbar.LENGTH_SHORT).show();
        textInfoUsername.setText(name);
        textInfoName.setText(realName);
        textInfoGender.setText(gender);
        textInfoSelfintro.setText(intro);
    }

    public void showFaile(){
        Snackbar.make(textInfoGender, "信息修改失败～", Snackbar.LENGTH_SHORT).show();
    }
}
