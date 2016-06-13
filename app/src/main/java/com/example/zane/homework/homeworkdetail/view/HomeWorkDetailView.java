package com.example.zane.homework.homeworkdetail.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.zane.easymvp.view.BaseViewImpl;
import com.example.zane.homework.R;

import butterknife.Bind;

/**
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetailView extends BaseViewImpl {
    @Bind(R.id.toolbar_postnotice)
    Toolbar toolbarPostnotice;
    @Bind(R.id.edit_text_posnotice)
    EditText editTextPosnotice;
    @Bind(R.id.button_postnotice)
    Button buttonPostnotice;
    private AppCompatActivity activity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_postnotice;
    }

    @Override
    public void setActivityContext(Activity activity) {
        this.activity = activity;
    }
}
