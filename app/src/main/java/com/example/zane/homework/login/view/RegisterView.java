package com.example.zane.homework.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.event.LoginEvent;
import com.example.zane.homework.utils.JudgeSearch;
import com.jakewharton.rxbinding.widget.RxRadioGroup;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class RegisterView extends BaseViewImpl {
    @Bind(R.id.radiogroup_register_identity)
    RadioGroup radiogroupRegisterIdentity;
    @Bind(R.id.radiogroup_register_gender)
    RadioGroup radiogroupRegisterGender;
    @Bind(R.id.edit_register_username)
    EditText editRegisterUsername;
    @Bind(R.id.textinput_register_username)
    TextInputLayout textinputRegisterUsername;
    @Bind(R.id.edit_register_name)
    EditText editRegisterName;
    @Bind(R.id.textinput_register_name)
    TextInputLayout textinputRegisterName;
    @Bind(R.id.edit_register_password)
    EditText editRegisterPassword;
    @Bind(R.id.textinput_register_password)
    TextInputLayout textinputRegisterPassword;
    @Bind(R.id.edit_register_selfintro)
    EditText editRegisterSelfintro;
    @Bind(R.id.textinput_register_selfintro)
    TextInputLayout textinputRegisterSelfintro;
    @Bind(R.id.button_register)
    Button buttonRegister;

    private AppCompatActivity activity;
    private String gender;
    private String identity;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_register;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init(){

        RxRadioGroup.checkedChanges(radiogroupRegisterGender).subscribe(integer -> {
            if (integer == R.id.radio_male){
                gender = "男";
            } else if (integer == R.id.radio_female){
                gender = "女";
            } else {
                gender = null;
            }
        });

        RxRadioGroup.checkedChanges(radiogroupRegisterIdentity).subscribe(integer -> {
            if (integer == R.id.radio_register_student){
                identity = "2";
            } else if (integer == R.id.radio_register_teacher){
                identity = "1";
            } else {
                identity = null;
            }
        });

        RxTextView.textChanges(editRegisterName).subscribe(s -> {
            if (!JudgeSearch.isRight(s.toString())){
                textinputRegisterName.setError("真实姓名格式不正确");
            } else {
                textinputRegisterName.setErrorEnabled(false);
            }
        });

        RxTextView.textChanges(editRegisterPassword).subscribe(s -> {
            if (!JudgeSearch.isRight(s.toString())){
                textinputRegisterPassword.setError("密码格式不正确");
            } else {
                textinputRegisterPassword.setErrorEnabled(false);
            }
        });

        RxTextView.textChanges(editRegisterUsername).subscribe(s -> {
            if (!JudgeSearch.isRight(s.toString())){
                textinputRegisterUsername.setError("用户名格式不正确");
            } else {
                textinputRegisterUsername.setErrorEnabled(false);
            }
        });

//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (gender != null && identity != null){
//                    if (identity.equals("teacher")){
//                        MockTeacherData.userName = editRegisterUsername.getText().toString();
//                        MockTeacherData.avatar = null;
//                        MockTeacherData.gender = gender;
//                        MockTeacherData.identity = identity;
//                        MockTeacherData.password = editRegisterPassword.getText().toString();
//                        MockTeacherData.selfIntro = editRegisterSelfintro.getText().toString();
//                        MockTeacherData.name = editRegisterName.getText().toString();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                activity.startActivity(new Intent(activity, MainActivity.class));
//                            }
//                        }, 1000);
//                    } else {
//                        // TODO: 16/6/15 student
//                    }
//                    EventBus.getDefault().post(new LoginEvent());
//                } else {
//                    Snackbar.make(textinputRegisterName, "信息输入不正确!", Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public String getGnder(){
        return gender;
    }

    public String getIdentity(){
        return identity;
    }

    public String username(){
        return editRegisterUsername.getText().toString();
    }

    public String realname(){
        return editRegisterName.getText().toString();
    }

    public String password(){
        return editRegisterPassword.getText().toString();
    }

    public String introduce(){
        return editRegisterSelfintro.getText().toString();
    }

}
