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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.MainActivity;
import com.example.zane.homework.R;
import com.example.zane.homework.config.MockStudentData;
import com.example.zane.homework.config.MockTeacherData;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.event.RegisterEvent;
import com.example.zane.homework.login.Adapter.SpinnerAdapter;
import com.example.zane.homework.login.presenters.LoginFragment;
import com.example.zane.homework.login.presenters.LoginRegisterActivity;
import com.example.zane.homework.utils.JudgeSearch;
import com.example.zane.homework.data.sp.MySharedPre;
import com.jakewharton.rxbinding.support.design.widget.RxTextInputLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxRadioGroup;
import com.jakewharton.rxbinding.widget.RxTextView;


import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import rx.functions.Action1;

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
    @Bind(R.id.spinner_login)
    Spinner spinner;
    @Bind(R.id.imageView_log)
    ImageView imageView;
//    @Bind(R.id.radio_teacher)
//    RadioButton radioTeacher;
//    @Bind(R.id.radio_student)
//    RadioButton radioStudent;
//    @Bind(R.id.radiogroup_login)
//    RadioGroup radiogroupLogin;
    @Bind(R.id.button_login)
    Button buttonLogin;
    @Bind(R.id.text_register)
    TextView textRegister;

    private LoginFragment fragment;
    private int identity;//0, 1, 2 null, teacher, student

    private SpinnerAdapter adapter;

    private int[] drawableIds = { R.drawable.student, R.drawable.teacher};

    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void injectPresenter(IPersenter iPersenter) {
        fragment = (LoginFragment) iPersenter;
    }

    @Override
    public void onPresenterDestory() {

    }

    public void init(){



        RxTextView.textChanges(editLoginUsername).subscribe(c -> {
            if (!JudgeSearch.isRight(c.toString())){
                textinputLoginUsername.setError("用户名格式不正确");
            } else {
                textinputLoginUsername.setErrorEnabled(false);
            }
        });

        RxTextView.textChanges(editLoginPassword).subscribe(c -> {
            if (!JudgeSearch.isRight(c.toString())){
                //Toast.makeText(activity, c.toString() + " string", Toast.LENGTH_SHORT).show();
                textinputLoginPassword.setError("密码格式不正确");
            } else {
                textinputLoginPassword.setErrorEnabled(false);
            }
        });

//        RxRadioGroup.checkedChanges(radiogroupLogin).subscribe(integer -> {
//            if (integer == R.id.radio_student){
//                setIdentity(2);
//            } else if (integer == R.id.radio_teacher){
//                setIdentity(1);
//            } else {
//                setIdentity(0);
//            }
//        });

        RxView.clicks(textRegister).subscribe(aVoid -> {
            EventBus.getDefault().post(new RegisterEvent());
        });

        RxView.clicks(buttonLogin).subscribe(aVoid -> fragment.login());


        adapter = new SpinnerAdapter(drawableIds);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int positon, long id) {

                if (positon == 0){
                    imageView.setImageResource(R.drawable.logo_red);
                    setIdentity(2);
                } else if (positon == 1){
                    imageView.setImageResource(R.drawable.logo_yellow);
                    setIdentity(1);
                } else {
                    setIdentity(0);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        RxView.clicks(spinner).subscribe(aVoid -> {
//            adapter = new SpinnerAdapter(drawableIds);
//            spinner.setAdapter(adapter);
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int positon, long id) {
//                    int i = (int) parent.getItemAtPosition(positon);
//                    Log.i("AA", "onItemSelected: "+parent.getItemAtPosition(positon));
//                    Log.i("AB", "onItemSelected: " + i);
//                }
//
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });
//        });
    }

    private void setIdentity(int id){
        identity = id;
    }

    public int getIdentity(){
        return identity;
    }

    public String userName(){
        return editLoginUsername.getText().toString();
    }

    public String password(){
        return editLoginPassword.getText().toString();
    }
}
