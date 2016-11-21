package com.example.zane.homework.clazzdetail.view;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.data.bean.GetDetailMessage;
import com.example.zane.homework.data.bean.GetMessage;
import com.example.zane.homework.event.LoadMoreNoticeEvent;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

/**
 * 消息界面的foot（点击加载更多）
 * Created by Zane on 2016/11/16.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class NoticeFootViewHolder extends BaseListViewHolderImpl<GetMessage.DataEntity>{

    private TextView touchMore;

    public NoticeFootViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    protected void initView() {
        touchMore = get(R.id.text_touch_more);
        RxView.clicks(touchMore)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {EventBus.getDefault().post(new LoadMoreNoticeEvent());});
    }

    @Override
    public void setData(GetMessage.DataEntity dataEntity) {
    }

    public void noNeedLoadMore(){
        touchMore.setVisibility(View.GONE);
        Log.i("holderclazz", "gone");
    }
}
