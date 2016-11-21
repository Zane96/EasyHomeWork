package com.example.zane.homework.clazzdetail.presenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.clazz.ClazzFragPresenter;
import com.example.zane.homework.clazzdetail.ClazzDetailFragViewPagerAdapter;
import com.example.zane.homework.clazzdetail.view.ClazzDetailActivityView;
import com.example.zane.homework.event.UpLoadFileEvent;
import com.example.zane.homework.service.UpLoadFileServicess;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class ClazzDetailActivityPresenter extends BaseActivityPresenter<ClazzDetailActivityView>{

    private static final String TAG = ClazzDetailActivityPresenter.class.getSimpleName();
    public static final String IMAGE_NAME = "imageName";
    public static final String POSITION = "position";
    public static final int OPEN_FFILE = 2333;

    private HomeWorkFragment homeWorkFragment;
    private MemberFragment memberFragment;
    private NoticeFragment noticeFragment;

    private ClazzDetailFragViewPagerAdapter adapter;
    //private ProgressHandler handler;
    private UpLoadFileServicess upLoadFileService;
    //下载的position
    private List<Integer> positions;
    //最后一个被添加的下载
    private int position;
    private int share_position;
    private boolean isReturn;

    @Override
    public Class<ClazzDetailActivityView> getRootViewClass() {
        return ClazzDetailActivityView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        v.initCallBack();
        EventBus.getDefault().register(this);

        String cid = getIntent().getStringExtra(ClazzFragPresenter.CID);
        String clazzName = getIntent().getStringExtra(ClazzFragPresenter.CLAZZ_NAME);
        String courseName = getIntent().getStringExtra(ClazzFragPresenter.COURSE_NAME);
        String jid = getIntent().getStringExtra(ClazzFragPresenter.JID);

        share_position = getIntent().getIntExtra(ClazzFragPresenter.POSITION_SHARE, 0);
        //背景图写死了
        int image = getIntent().getIntExtra(ClazzFragPresenter.IMAGE, R.drawable.back1_re);

        //handler = new ProgressHandler(this);
        positions = new ArrayList<>();
        v.init(cid, jid);
        initData(cid);
        v.setText(clazzName, courseName, image, share_position);
        v.initTablayout(adapter);
    }

    private void initData(String cid){
        adapter = new ClazzDetailFragViewPagerAdapter(getSupportFragmentManager());
        adapter.addHomeWorkFrag(HomeWorkFragment.newInstance(), "作业");
        adapter.addNoticeFrag(NoticeFragment.newInstance(), "消息");
        adapter.addMemberFrag(MemberFragment.newInstance(cid), "成员");
    }

    @Override
    public void finishAfterTransition() {
        Intent intent = new Intent();
        intent.putExtra(ClazzFragPresenter.POSITION_SHARE, String.valueOf(share_position));
        setResult(RESULT_OK, intent);
        super.finishAfterTransition();
    }

    @Override
    public void inDestory() {
        //handler.removeMessages(1);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Activity getContext() {
        return this;
    }

    /**
     * 打开文件管理器
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpLoadFileSubscriber(UpLoadFileEvent event){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        positions.add(event.getPosition());
        position = event.getPosition();
        startActivityForResult(intent, OPEN_FFILE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UpLoadFileServicess.MyBinder binder = (UpLoadFileServicess.MyBinder) service;
            ClazzDetailActivityPresenter.this.upLoadFileService = binder.getServiceContext();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case OPEN_FFILE:
                    String filePath = Uri.decode(data.getDataString());
                    filePath = filePath.substring(7, filePath.length());
                    //开启服务上传文件
                    Intent intent = new Intent(ClazzDetailActivityPresenter.this, UpLoadFileServicess.class);
                    intent.putExtra(POSITION, position);
                    startService(intent);
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
