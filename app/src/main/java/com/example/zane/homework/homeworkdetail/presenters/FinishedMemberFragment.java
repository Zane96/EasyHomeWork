package com.example.zane.homework.homeworkdetail.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailMemberAdapter;
import com.example.zane.homework.clazzdetail.presenter.MemberFragment;
import com.example.zane.homework.clazzdetail.presenter.WorkJudgePresenter;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.model.HomeWorkModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.HomeWorkDetail;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.event.WorkDetailRefreshEvent;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.utils.RandomBackImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/** 这里默认是所有学生的完成信息
 * Created by Zane on 16/6/13.
 * Email: zanebot96@gmail.com
 */

public class FinishedMemberFragment extends BaseFragment<ClazzDeatilFragmentView> {

    private List<HoPerson.DataEntity> datas;
    private ClazzDetailMemberAdapter adapter;
    private ClassModel classModel = ClassModel.getInstance();
    private HomeWorkModel workModel = HomeWorkModel.getInstance();
    private List<String> sids;

    public static final String CID = "cid";
    public static final String ASID = "asid";
    public static final String SID = "sid";
    public static final String ADDTION = "addtion";
    public static final String DEGREE = "degree";
    public static final String ATTACHMENT = "attachement";
    public static final String STU_NAME = "stuname";

    private String cid;
    private String asid;

    public static FinishedMemberFragment newInstance(String cid, String asid){
        FinishedMemberFragment fragment = new FinishedMemberFragment();

        Bundle bundle = new Bundle();
        bundle.putString(CID, cid);
        bundle.putString(ASID, asid);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public Class<ClazzDeatilFragmentView> getRootViewClass() {
        return ClazzDeatilFragmentView.class;
    }

    @Override
    public FragmentActivity getContext() {
        return getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        init();
        getData();
    }

    private void init() {
        datas = new ArrayList<>();
        adapter = new ClazzDetailMemberAdapter(App.getInstance());
        cid = getArguments().getString(CID);
        asid = getArguments().getString(ASID);
        sids = new ArrayList<>();
    }

    public void getData(){
        adapter.clear();
        classModel.classMemeber(cid)
                .flatMap(memberDatas -> Observable.from(memberDatas))
                .flatMap(memberData -> {
                    sids.add(memberData.getSid());
                    return workModel.showHoPerson(asid, memberData.getSid());})
                .map(hoPersons -> {return hoPersons.get(0);})
                .toList()
                .subscribe(new FinalSubscriber<List<HoPerson.DataEntity>>(this, dataEntitie -> {
                    List<HoPerson.DataEntity> dataEntities = (List<HoPerson.DataEntity>) dataEntitie;
                    MemberDetail memberDetail = (MemberDetail) dataEntities.get(0);
                    memberDetail.setHoPerson(dataEntities.get(0));
                    datas.add((HoPerson.DataEntity)dataEntities.get(0));
                    adapter.add(memberDetail);
                    adapter.notifyItemInserted(datas.size());
                }));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.initMemberRecycle(adapter, "work");
        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                HoPerson.DataEntity data = datas.get(i);
                //跳转作业批改
                Intent intent = new Intent(getActivity(), WorkJudgePresenter.class);
                intent.putExtra(SID, sids.get(i));
                intent.putExtra(ASID, asid);
                intent.putExtra(STU_NAME, data.getName());
                intent.putExtra(ADDTION, data.getAddtion());
                intent.putExtra(ATTACHMENT, data.getAttach());
                intent.putExtra(DEGREE, data.getGrade());
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int i) {
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(WorkDetailRefreshEvent event){
        v.finishRefresh();
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
