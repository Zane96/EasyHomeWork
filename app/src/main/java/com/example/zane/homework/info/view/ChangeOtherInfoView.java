package com.example.zane.homework.info.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.zane.easyimageprovider.builder.ImageProviderBuilder;
import com.example.zane.easyimageprovider.builder.core.EasyImage;
import com.example.zane.easyimageprovider.provider.listener.OnGetImageListener;
import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseActivityView;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.info.presenters.ChangeOtherInfoActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxRadioGroup;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class ChangeOtherInfoView extends BaseActivityView {

    public static final int USERNAME_CODE = 111;
    public static final int NAME_CODE = 222;
    public static final int SELFINTRO_CODE = 333;
    public static final int PASSWORD_CODE = 444;
    public static final int GENDER_CODE = 555;
    private EasyImage image;
    private String gender;

    @Bind(R.id.radiogroup_change_gender)
    RadioGroup radioGroup;
    @Bind(R.id.change_info_head)
    ImageView imageView_head;

    @Bind(R.id.edit_change_username)
    EditText editchange_username;
    @Bind(R.id.edit_change_name)
    EditText editchange_name;
    @Bind(R.id.edit_change_sercet)
    EditText editchange_serect;
    @Bind(R.id.edit_change_info)
    EditText editChangeOtherinfo;
    @Bind(R.id.button_post_newotherinfo)
    Button buttonPostNewotherinfo;
    @Bind(R.id.toolbar_changotherinfo)
    Toolbar toolbarChangotherinfo;

    private ChangeOtherInfoActivity activity;

    public static final String OTHER_RESULT = "otherResult";

    @Override
    public int getRootViewId() {
        return R.layout.activity_changotherinfo;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        activity = (ChangeOtherInfoActivity) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    private void initToolbar() {
        super.initToolbar(activity, toolbarChangotherinfo, "修改信息");
    }

    public void init(String oldData){
        initToolbar();

        OnGetImageListener<Uri> listener = new OnGetImageListener<Uri>() {
            @Override
            public void getDataBack(Uri uri) {
                Looper.prepare();
                android.os.Handler handler = new android.os.Handler();
                Looper.loop();
            Glide.with(activity)
                    .load(uri)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imageView_head);
            }
        };


        imageView_head.setOnClickListener(new View.OnClickListener() {
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

    //    editChangeOtherinfo.setHint(oldData.toString());

        RxView.clicks(buttonPostNewotherinfo).subscribe(aVoid -> {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra(OTHER_RESULT, editChangeOtherinfo.getText().toString());
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        });

        RxRadioGroup.checkedChanges(radioGroup).subscribe(integer -> {
            if (integer == R.id.radio_change_male){
                gender = "男";
            } else if (integer == R.id.radio_change_female){
                gender = "女";
            } else {
                gender = null;
            }
        });

    }
    public void OnActivityResult(int requestCode, int resultCode, Intent data) {
        if (image != null) {
            image.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String getName(){
        return editchange_username.getText().toString();
    }

    public String getRealName(){
        return editchange_name.getText().toString();
    }

    public String getPassWord(){
        return editchange_serect.getText().toString();
    }
    public String getGnder(){
        return gender;
    }

    public String getIntro(){
        return editChangeOtherinfo.getText().toString();
    }

    public void showSuccess(String serect, String realName, String name, String intro){
        Snackbar.make(editchange_username, "信息修改成功～", Snackbar.LENGTH_SHORT).show();
        editchange_username.setText(name);
        editchange_name.setText(realName);
        editchange_serect.setText(serect);
        //  textInfoGender.setText(gender);
        editChangeOtherinfo.setText(intro);
    }

    public void showFaile(){
        Snackbar.make(editchange_username, "信息修改失败～", Snackbar.LENGTH_SHORT).show();
    }

}
