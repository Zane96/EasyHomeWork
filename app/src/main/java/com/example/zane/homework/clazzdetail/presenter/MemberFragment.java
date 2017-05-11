package com.example.zane.homework.clazzdetail.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.easymvp.base.IPersenter;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.clazzdetail.view.ClazzDeatilFragmentView;
import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.MemberDetail;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.event.MemberRefreshEvent;
import com.example.zane.homework.otherinfo.presenters.OtherInfoActivity;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;
import com.jakewharton.rxbinding.support.design.widget.RxSnackbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberFragment extends BaseFragment<ClazzDeatilFragmentView> {

    public static final String MEMBER_DETAIL = "memberDetail";
    private static final String CID = "cid";
    public static final String SID = "sid";
    public static final String JID = "jid";
    public static final String NAME = "name";
    public static final String INTRO = "intro";

    private ClazzDetailMemberAdapter adapter;
    private String cid;
    private String jid;
    private ClassModel model = ClassModel.getInstance();
    private List<MemberDetail> datas;

    public static MemberFragment newInstance(String cid, String jid){
        MemberFragment fragment = new MemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CID, cid);
        bundle.putString(JID, jid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Class<ClazzDeatilFragmentView> getRootViewClass() {
        return ClazzDeatilFragmentView.class;
    }

    @Override
    public IPersenter getPersenter() {
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        adapter = new ClazzDetailMemberAdapter(getActivity());
        cid = getArguments().getString(CID);
        jid = getArguments().getString(JID);
        datas = new ArrayList<>();
        getData();
    }

    public void getData() {

        adapter.clear();
        datas.clear();

        model.classMemeber(cid)
                .map(dataEntities -> {return (List<ClassMemeber.DataEntity>)(dataEntities);})
                .flatMap(members -> Observable.from(members))
                .subscribe(new FinalSubscriber<ClassMemeber.DataEntity>(this, data -> {
                    MemberDetail memberDetail = (MemberDetail) data;
                    memberDetail.setMemeber((ClassMemeber.DataEntity)data);
                    datas.add(memberDetail);
                    adapter.addAll(datas);
                    adapter.notifyDataSetChanged();
                }));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(MemberRefreshEvent event){
        v.finishRefresh();
        getData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.initMemberRecycle(adapter, "member");
        adapter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                ClassMemeber.DataEntity data = datas.get(i).getMemeber();
                Intent intent = new Intent(getActivity(), OtherInfoActivity.class);
                intent.putExtra(JID, jid);
                intent.putExtra(SID, data.getSid());
                intent.putExtra(NAME, data.getName());
                intent.putExtra(INTRO, data.getSelfintro());
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, final int i) {
                if (MySharedPre.getInstance().getIdentity().equals("student") && StudentLogin.getInstacne().getIsOwner()){
                    Snackbar.make(view, "您想要删除这位成员嘛?~", Snackbar.LENGTH_LONG).setAction("确定", v -> {
                        adapter.remove(i);
                        adapter.notifyItemRemoved(i);
                    }).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
