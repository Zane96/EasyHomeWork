package com.example.zane.homework.info.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easyimageprovider.OnGetImageListener;
import com.example.zane.easyimageprovider.builder.ImageProviderBuilder;
import com.example.zane.easyimageprovider.builder.core.EasyImage;
import com.example.zane.easyimageprovider.builder.factory.EasyImageFactory;
import com.example.zane.easyimageprovider.builder.factory.ProviderFactory;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.info.presenters.ChangPasswordActivity;
import com.example.zane.homework.info.presenters.ChangeOtherInfoActivity;
import com.example.zane.homework.info.presenters.InfoActivity;
import com.jude.utils.JUtils;

import butterknife.Bind;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class InfoFragmentView extends BaseViewImpl implements View.OnClickListener{

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
    TextView textInfoCourse;
    @Bind(R.id.card_info_course)
    CardView cardInfoCourse;
    private AppCompatActivity activity;
    private EasyImage image;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_info;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    OnGetImageListener<Uri> listener = new OnGetImageListener<Uri>() {
        @Override
        public void getDataBack(Uri uri) {
            Glide.with(activity)
                    .load(uri)
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imagetSelfAvatar);
            Log.i("InfoFragmentView", String.valueOf(uri));
            MockTeacherData.avatar = uri;
        }
    };

    public void init(){

        cardInfoAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageProviderBuilder builder = new ImageProviderBuilder()
                        .with(activity)
                        .setGetImageListener("uri", listener)
                        .useCrop(200, 200);
                new AlertDialog.Builder(activity).setItems(new String[]{"相册", "相机"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                builder.userAlbum();
                                break;
                            case 1:
                                builder.useCamera();
                                break;
                        }
                        EasyImageFactory factory = new ProviderFactory(builder);
                        initImageProvider(factory);
                    }
                }).show();
            }
        });

        textInfoName.setText(TeacherLogin.getInstacne().getName());
        textInfoCourse.setText(TeacherLogin.getInstacne().getCourse());
        textInfoUsername.setText(TeacherLogin.getInstacne().getUserName());
        textInfoSelfintro.setText(TeacherLogin.getInstacne().getSelfIntro());

        //imagetSelfAvatar.setImageResource(R.drawable.ic_upload);
        Glide.with(activity)
                .load(MockTeacherData.avatar)
                .transform(new CircleTransform(App.getInstance()))
                .into(imagetSelfAvatar);

        cardInfoCourse.setOnClickListener(this);
        cardInfoName.setOnClickListener(this);
        cardInfoPassword.setOnClickListener(this);
        cardInfoSelfintro.setOnClickListener(this);
        cardInfoUsername.setOnClickListener(this);
    }

    private void initImageProvider(EasyImageFactory factory){
        image = factory.init();
        image.execute();
    }

    public void OnActivityResult(int requestCode, int resultCode, Intent data){
        if (image != null){
            image.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == RESULT_OK){
            if (requestCode == PASSWORD_CODE){
                MockTeacherData.password = data.getStringExtra(ChangePasswordView.PASSWORD_RESULT);
                TeacherLogin.getInstacne().setPsd(data.getStringExtra(ChangePasswordView.PASSWORD_RESULT));
            }else {
                String str = data.getStringExtra(ChangeOtherInfoView.OTHER_RESULT);
                switch (requestCode){
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
                        textInfoCourse.setText(str);
                        break;
                }
            }
            Snackbar.make(cardInfoCourse, "修改成功~", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, ChangeOtherInfoActivity.class);
        switch (v.getId()){
            case R.id.card_info_password:
                Intent intentPassword = new Intent(activity, ChangPasswordActivity.class);
                intentPassword.putExtra(CHANGE_CONTENT, textInfoPassword.getText());
                activity.startActivityForResult(intentPassword, PASSWORD_CODE);
                break;
            case R.id.card_info_course:
                intent.putExtra(CHANGE_CONTENT, textInfoCourse.getText());
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
                activity.startActivityForResult(intent,USERNAME_CODE);
                break;
        }
    }
}
