package com.example.zane.homework.info.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.config.MockStudentData;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.info.presenters.ChangPasswordActivity;
import com.example.zane.homework.info.presenters.ChangeOtherInfoActivity;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.example.zane.homework.data.sp.MySharedPre;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;

import butterknife.Bind;

import static android.app.Activity.RESULT_OK;

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
    public static final int COURSE_CODE = 555;

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
    @Bind(R.id.text_info_course)
    TextView textInfoGender;
    @Bind(R.id.card_info_course)
    CardView cardInfoGender;
    @Bind(R.id.toolbar_info)
    Toolbar toolbarInfo;
    @Bind(R.id.text6)

    private InfoActivity activity;
    private EasyImage image;

    @Override
    public int getRootViewId() {
        return R.layout.activity_info;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (InfoActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    OnGetImageListener<Uri> listener = new OnGetImageListener<Uri>() {
        @Override
        public void getDataBack(Uri uri) {
            Glide.with(activity)
                    .load(uri)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imagetSelfAvatar);
//            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
//                MockTeacherData.avatar = uri;
//            } else {
//                MockStudentData.avatar = uri;
//            }
        }
    };

    public void init(String name) {

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
                            EasyImage easyImage = EasyImage.creat(builder);
                            easyImage.execute();
                        }).show();
            }
        });

        if (!name.equals("")){
            textInfoName.setText(TeacherLogin.getInstacne().getName());
            textInfoGender.setText(TeacherLogin.getInstacne().getCourse());
            textInfoUsername.setText(TeacherLogin.getInstacne().getUserName());
            textInfoSelfintro.setText(TeacherLogin.getInstacne().getSelfIntro());
        } else {
            activity.refreshData();
        }
//        Glide.with(activity)
//                .load(MockTeacherData.avatar)
//                .transform(new CircleTransform(App.getInstance()))
//                .into(imagetSelfAvatar);

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
        if (resultCode == RESULT_OK) {
            if (requestCode == PASSWORD_CODE) {
                if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                    MockTeacherData.password = data.getStringExtra(ChangePasswordView.PASSWORD_RESULT);
                    TeacherLogin.getInstacne().setPsd(data.getStringExtra(ChangePasswordView.PASSWORD_RESULT));
                } else {
                    MockStudentData.password = data.getStringExtra(ChangePasswordView.PASSWORD_RESULT);
                    StudentLogin.getInstacne().setPsd(data.getStringExtra(ChangePasswordView.PASSWORD_RESULT));
                }
            } else {
                if (MySharedPre.getInstance().equals("teacher")){
                    String str = data.getStringExtra(ChangeOtherInfoView.OTHER_RESULT);
                    switch (requestCode) {
                        case USERNAME_CODE:
                            MockTeacherData.userName = str;
                            TeacherLogin.getInstacne().setUserName(str);
                            textInfoUsername.setText(str);
                            break;
                        case NAME_CODE:
                            MockTeacherData.name = str;
                            TeacherLogin.getInstacne().setName(str);
                            textInfoName.setText(str);
                            break;
                        case SELFINTRO_CODE:
                            MockTeacherData.selfIntro = str;
                            TeacherLogin.getInstacne().setSelfIntro(str);
                            textInfoSelfintro.setText(str);
                            break;
                        case COURSE_CODE:
                            MockTeacherData.course = str;
                            TeacherLogin.getInstacne().setCourse(str);
                            textInfoGender.setText(str);
                            break;
                    }
                } else {
                    String str = data.getStringExtra(ChangeOtherInfoView.OTHER_RESULT);
                    switch (requestCode) {
                        case USERNAME_CODE:
                            MockStudentData.userName = str;
                            StudentLogin.getInstacne().setUserName(str);
                            textInfoUsername.setText(str);
                            break;
                        case NAME_CODE:
                            MockStudentData.name = str;
                            StudentLogin.getInstacne().setName(str);
                            textInfoName.setText(str);
                            break;
                        case SELFINTRO_CODE:
                            MockStudentData.selfIntro = str;
                            StudentLogin.getInstacne().setSelfIntro(str);
                            textInfoSelfintro.setText(str);
                            break;
                        case COURSE_CODE:
                            MockStudentData.clazzNames = str;
                            StudentLogin.getInstacne().setClazz(str);
                            textInfoGender.setText(str);
                            break;
                    }
                }
            }
            Snackbar.make(cardInfoGender, "修改成功~", Snackbar.LENGTH_SHORT).show();
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
            case R.id.card_info_course:
                intent.putExtra(CHANGE_CONTENT, textInfoGender.getText());
                activity.startActivityForResult(intent, COURSE_CODE);
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
}
