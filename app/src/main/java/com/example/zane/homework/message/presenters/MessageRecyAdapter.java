package com.example.zane.homework.message.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;
import com.example.zane.homework.R;
import com.example.zane.homework.clazzdetail.view.StuWorkViewHolder;
import com.example.zane.homework.data.bean.ShowApply;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.entity.ApplyDetail;
import com.example.zane.homework.message.view.StuApplyViewHolder;
import com.example.zane.homework.message.view.TeaApplyViewHolder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Zane on 16/6/16.
 * Email: zanebot96@gmail.com
 */

public class MessageRecyAdapter extends BaseListAdapterPresenter<ApplyDetail>{

    private final List<String> cids;
    private final ClassModel model = ClassModel.getInstance();

    public MessageRecyAdapter(@NonNull Context mContext) {
        super(mContext);
        cids = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position) instanceof ShowApply.DataEntity.TeachEntity){
            return ApplyDetail.TEAAPPLY;
        } else{
            return ApplyDetail.STUAPPLY;
        }
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        if (i == ApplyDetail.TEAAPPLY){
            return new TeaApplyViewHolder(viewGroup, R.layout.item_message);
        } else {
            return new StuApplyViewHolder(viewGroup, R.layout.item_message);
        }
    }

    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl baseListViewHolder, int i) {

        baseListViewHolder.setData(mDatas.get(i));

        RxView.clicks(baseListViewHolder.get(R.id.button_message_state))
                .subscribe(aVoid -> {
                    if (mDatas.get(i) instanceof ShowApply.DataEntity.TeachEntity){
                        ShowApply.DataEntity.TeachEntity data = (ShowApply.DataEntity.TeachEntity) mDatas.get(i);
                        TeaApplyViewHolder holder = (TeaApplyViewHolder) baseListViewHolder;
                        model.okTeaApply(cids.get(i), data.getApid(), data.getCourse(), data.getAddtion())
                                .subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {
                                    }
                                    @Override
                                    public void onError(Throwable e) {
                                        holder.applySuccess();
                                    }
                                    @Override
                                    public void onNext(String s) {
                                        holder.applySuccess();
                                    }
                                });
                    } else {
                        ShowApply.DataEntity.StuEntity data = (ShowApply.DataEntity.StuEntity) mDatas.get(i);
                        StuApplyViewHolder holder = (StuApplyViewHolder) baseListViewHolder;
                        model.okStuApply(cids.get(i), data.getApid())
                                .subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {
                                    }
                                    @Override
                                    public void onError(Throwable e) {
                                        holder.applySuccess();
                                    }
                                    @Override
                                    public void onNext(String s) {
                                        holder.applySuccess();
                                    }
                                });
                    }
                });
    }

    @Override
    public int setHeadNum() {
        return 0;
    }

    @Override
    public int setFootNum() {
        return 0;
    }

    public void addCid(String cid){
        cids.add(cid);
    }
}
