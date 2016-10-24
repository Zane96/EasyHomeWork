package com.example.zane.homework.login.view;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.config.MockStudentData;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.event.RegisterEvent;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.data.sp.MySharedPre;


import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class LoginView extends BaseViewImpl {
    @Bind(R.id.edit_login_username)
    EditText editLoginUsername;
    @Bind(R.id.textinput_login_username)
    TextInputLayout textinputLoginUsername;
    @Bind(R.id.edit_login_password)
    EditText editLoginPassword;
    @Bind(R.id.textinput_login_password)
    TextInputLayout textinputLoginPassword;
    @Bind(R.id.radio_teacher)
    RadioButton radioTeacher;
    @Bind(R.id.radio_student)
    RadioButton radioStudent;
    @Bind(R.id.radiogroup_login)
    RadioGroup radiogroupLogin;
    @Bind(R.id.button_login)
    Button buttonLogin;
    @Bind(R.id.text_register)
    TextView textRegister;

    private AppCompatActivity activity;
    private ProgressDialog progressDialog;
    private int identity;//0, 1, 2 null, teacher, student

    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init(){
        progressDialog = new ProgressDialog(activity);
        radiogroupLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_student){
                    setIdentity(2);
                } else if (checkedId == R.id.radio_teacher){
                    setIdentity(1);
                } else {
                    setIdentity(0);
                }
            }
        });
        editLoginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!JudgeSearch.isRight(s.toString())){
                    textinputLoginUsername.setError("用户名格式不正确");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!JudgeSearch.isRight(s.toString())){
                    textinputLoginPassword.setError("密码格式不正确");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editLoginUsername.getText().toString().equals(MockTeacherData.userName) &&
                            editLoginPassword.getText().toString().equals(MockTeacherData.password) &&
                        identity == 1){
                    MySharedPre.getInstance().setIdentity("teacher");
                    MySharedPre.getInstance().setLogin(true);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.hide();
                            //序列化存储保持登陆
                            if (MySharedPre.getInstance().getIdentity().equals("teacher")){
                                TeacherLogin teacherLogin = TeacherLogin.getInstacne();
                                teacherLogin.setAvatar(0);
                                teacherLogin.setCourse(MockTeacherData.course);
                                teacherLogin.setGender(MockTeacherData.gender);
                                teacherLogin.setName(MockTeacherData.name);
                                teacherLogin.setPsd(MockTeacherData.password);
                                teacherLogin.setSelfIntro(MockTeacherData.selfIntro);
                                teacherLogin.setSessionid(MockTeacherData.sessionId);
                                teacherLogin.setUserName(MockTeacherData.userName);
                                teacherLogin.setClazz(MockTeacherData.className);
                                teacherLogin.setOwners(MockTeacherData.owners);
                                teacherLogin.setIds(MockTeacherData.ids);
                            }
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        }
                    }, 1000);
                } else if (editLoginPassword.getText().toString().equals(MockStudentData.password) && editLoginUsername.getText().toString().equals(MockStudentData.userName) &&
                              identity == 2){
                    MySharedPre.getInstance().setIdentity("student");
                    MySharedPre.getInstance().setLogin(true);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.hide();
                            //序列化存储保持登陆
                            if (MySharedPre.getInstance().getIdentity().equals("student")){
                                StudentLogin studentLogin = StudentLogin.getInstacne();
                                studentLogin.setAvatar(0);
                                studentLogin.setCourse(MockStudentData.courseNames);
                                studentLogin.setGender(MockStudentData.gender);
                                studentLogin.setLogin(true);
                                studentLogin.setName(MockStudentData.name);
                                studentLogin.setPsd(MockStudentData.password);
                                studentLogin.setSelfIntro(MockStudentData.selfIntro);
                                studentLogin.setSessionid(MockStudentData.sessionId);
                                studentLogin.setUserName(MockStudentData.userName);
                                studentLogin.setClazz(MockStudentData.clazzNames);
                                studentLogin.setOwner(MockStudentData.owners);
                                studentLogin.setIsOwner(MockStudentData.owner);
                                studentLogin.setIds(MockStudentData.ids);
                            }
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        }
                    }, 1000);
                } else {
                    Snackbar.make(textinputLoginPassword, "身份,用户名,密码错误!~", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RegisterEvent());
            }
        });
    }
    private void setIdentity(int id){
        identity = id;
    }
}
