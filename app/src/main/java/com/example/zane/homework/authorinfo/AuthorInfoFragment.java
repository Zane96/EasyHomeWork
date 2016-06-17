package com.example.zane.homework.authorinfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.jude.utils.JUtils;

/**
 * Created by Zane on 16/6/15.
 * Email: zanebot96@gmail.com
 */

public class AuthorInfoFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    private static final String TEACHER = "teacherName";
    private static final String VERSION_CHECK = "versionCheck";
    private static final String CURRENT_VERSION = "currentVersion";
    private static final String SHARE = "share";
    private static final String STAR = "star";
    private static final String BLOG = "blog";
    private static final String GITHUB = "github";
    private static final String EMAIL = "email";

    private Preference mTeacher;
    private Preference mVersionCheck;
    private Preference mShare;
    private Preference mStar;
    private Preference mBolg;
    private Preference mGithub;
    private Preference mEmail;
    private Preference mCurrentVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about);

        mTeacher = findPreference(TEACHER);
        mVersionCheck = findPreference(VERSION_CHECK);
        mShare = findPreference(SHARE);
        mStar = findPreference(STAR);
        mBolg = findPreference(BLOG);
        mGithub = findPreference(GITHUB);
        mEmail = findPreference(EMAIL);
        mCurrentVersion = findPreference(CURRENT_VERSION);

        mTeacher.setOnPreferenceClickListener(this);
        mVersionCheck.setOnPreferenceClickListener(this);
        mShare.setOnPreferenceClickListener(this);
        mStar.setOnPreferenceClickListener(this);
        mBolg.setOnPreferenceClickListener(this);
        mGithub.setOnPreferenceClickListener(this);
        mEmail.setOnPreferenceClickListener(this);
        mCurrentVersion.setOnPreferenceClickListener(this);

        //获取当前的版本名
        mCurrentVersion.setSummary("HomeWork Version-" + JUtils.getAppVersionName() + "-" + JUtils.getAppVersionCode());
        mTeacher.setSummary(getResources().getString(R.string.teachername));
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        //调用浏览器来打开readme
        if (mTeacher == preference){
            //指定uri
//            Uri uri = Uri.parse(getString(R.string.readme));
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setData(uri);
//            //启动浏览器
//            getActivity().startActivity(intent);
        } else if (mEmail == preference){
            copyToClipboard(getView(), mEmail.getSummary().toString());
        } else if (mGithub == preference){
            new AlertDialog.Builder(getActivity()).setTitle("Github")
                    .setMessage("Zane96的github")
                    .setNegativeButton("复制", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            copyToClipboard(getView(), mGithub.getSummary().toString());
                        }
                    })
                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse(mGithub.getSummary().toString());
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            getActivity().startActivity(intent);
                        }
                    })
                    .show();
        } else if (mBolg == preference){
            new AlertDialog.Builder(getActivity()).setTitle("Blog")
                    .setMessage("Zane96的个人博客")
                    .setNegativeButton("复制", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            copyToClipboard(getView(), mBolg.getSummary().toString());
                        }
                    })
                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse(mBolg.getSummary().toString());
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            getActivity().startActivity(intent);
                        }
                    })
                    .show();
        } else if (mShare == preference){

        } else if (mStar == preference){
            new AlertDialog.Builder(getActivity()).setTitle("BookManager")
                    .setMessage("给作者的项目来波star~")
                    .setNegativeButton("复制", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            copyToClipboard(getView(), getString(R.string.homework));
                        }
                    })
                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse(getString(R.string.homework));
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            getActivity().startActivity(intent);
                        }
                    })
                    .show();
        } else if (mVersionCheck == preference){
            //版本更新检测
//            if (NetUtils.hasNetwork()){
//                FIR.checkForUpdateInFIR(FirConfig.API_TOKEN, new VersionCheckCallback() {
//                    @Override
//                    public void onSuccess(String versionJson) {
//                        Log.i("fir", "check from fir.im success! " + "\n" + versionJson);
//                        Gson gson = new Gson();
//                        VersionApi versionApi = gson.fromJson(versionJson, VersionApi.class);
//                        VersionCheck.checkVersion(versionApi, getActivity(), getView());
//                    }
//
//                    @Override
//                    public void onFail(Exception exception) {
//                        Log.i("fir", "check fir.im fail! " + "\n" + exception.getMessage());
//                        Snackbar.make(getView(), "获取失败～", Snackbar.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onStart() {
//                        Snackbar.make(getView(), "正在获取~", Snackbar.LENGTH_SHORT).show();
//                    }
//                });
//            } else {
//                Snackbar.make(getView(), "检查一下你的网络嘛～", Snackbar.LENGTH_SHORT).show();
//            }
        }


        return false;
    }


    private void copyToClipboard(View view, String info) {
        ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("msg", info);
        manager.setPrimaryClip(clipData);
        Snackbar.make(view, "已经复制到剪切板~", Snackbar.LENGTH_SHORT).show();
    }
}
