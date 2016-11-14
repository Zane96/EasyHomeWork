package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.clazzdetail.view.WorkJudgeActivityView;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.homeworkdetail.presenters.FinishedMemberFragment;
import com.jude.utils.JUtils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.functions.Action1;

/**
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class WorkJudgePresenter extends BaseActivityPresenter<WorkJudgeActivityView>{

    private HomeWorkModel workModel = HomeWorkModel.getInstance();

    private String cid;
    private String asid;
    private String attachment;

    //下载文件的base url
    private static final String BASE_URL = "http://115.159.113.116/index.php/Homework/downLoad/";
    public static final String DOWNLOAD_URL = "download_url";

    @Override
    public Class<WorkJudgeActivityView> getRootViewClass() {
        return WorkJudgeActivityView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        attachment = getIntent().getStringExtra(FinishedMemberFragment.ATTACHMENT);

        v.init(getIntent().getStringExtra(FinishedMemberFragment.STU_NAME), getIntent().getStringExtra(FinishedMemberFragment.ADDTION),
                attachment);

        cid = getIntent().getStringExtra(FinishedMemberFragment.CID);
        asid = getIntent().getStringExtra(FinishedMemberFragment.ASID);

    }

    public void judgeWork(String degree, String addtion){

    }

    public void downloadWork(){
        String fileUrl = BASE_URL + attachment + "/2";

    }

    @Override
    public void inDestory() {

    }

    @Override
    public Activity getContext() {
        return this;
    }
}
