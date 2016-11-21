package com.example.zane.homework.clazzdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.model.MessageModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.jude.utils.JUtils;

/**
 * Created by Zane on 2016/11/20.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class NoticeDetailFrgament extends DialogFragment{

    private String mid;
    private FinalSubscriber<GetDetailMessage.DataEntity> messageSubscriber;

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout((int) (JUtils.getScreenWidth() * 0.8), (int) (JUtils.getScreenHeight() * 0.6));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticedetail_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_noticedetail_content);

        MessageModel model = MessageModel.getInstance();
        if (mid != null){
            messageSubscriber = new FinalSubscriber<>(getActivity(), data -> {
                GetDetailMessage.DataEntity dataEntity = (GetDetailMessage.DataEntity) data;
                textView.setText(dataEntity.getContent());
            });
            model.getDetailMessage(Integer.parseInt(mid)).subscribe(messageSubscriber);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (messageSubscriber != null){
            messageSubscriber.cancelProgress();
        }
    }
}
