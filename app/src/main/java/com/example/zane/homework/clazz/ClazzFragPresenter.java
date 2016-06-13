package com.example.zane.homework.clazz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.utils.RandomBackImage;
import com.example.zane.homework.utils.RandomColor;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 */

public class ClazzFragPresenter extends BaseFragmentPresenter<ClazzFragView>{

    private static final String TAG = ClazzFragPresenter.class.getSimpleName();
    public static final String CLAZZ_NAME = "clazzName";
    public static final String COURSE_NAME = "courseName";
    public static final String IMAGE = "IMAGE";

    private ProgressHandler progressHandler;
    private List<Clazz> datas;
    private ClazzRecyAdapterPresenter adapterPresenter;

    public static ClazzFragPresenter newInstance(){
        ClazzFragPresenter fragment = new ClazzFragPresenter();
        return fragment;
    }

    @Override
    public Class<ClazzFragView> getRootViewClass() {
        return ClazzFragView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        datas = new ArrayList<>();
        progressHandler = new ProgressHandler(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterPresenter = new ClazzRecyAdapterPresenter(App.getInstance());
        v.showProgress();

        Message message = new Message();
        message.what = 1;
        progressHandler.sendMessageDelayed(message, 1500);

        adapterPresenter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(getActivity(), ClazzDetailActivityPresenter.class);
                intent.putExtra(CLAZZ_NAME, datas.get(i).getClassName());
                intent.putExtra(COURSE_NAME, datas.get(i).getCourseName());
                intent.putExtra(IMAGE, datas.get(i).getImage());
                getActivity().startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int i) {
                
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressHandler.removeMessages(1);
    }

    private final static class ProgressHandler extends Handler{
        private WeakReference<ClazzFragPresenter> reference;
        public ProgressHandler(ClazzFragPresenter activity){
            reference = new WeakReference<ClazzFragPresenter>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null){
                switch (msg.what){
                    case 1:
                        for (int i = 0; i < 10; i++){
                            Clazz clazz = new Clazz();
                            clazz.setImage(RandomBackImage.getRandomImage());
                            reference.get().datas.add(clazz);
                        }
                        reference.get().adapterPresenter.addAll(reference.get().datas);
                        reference.get().v.initRecycleview(reference.get().adapterPresenter);
                        JUtils.Toast(reference.get().getResources().getString(R.string.finish_load));
                        reference.get().v.dissmissProgress();
                        break;
                }
            }
        }
    }
}
