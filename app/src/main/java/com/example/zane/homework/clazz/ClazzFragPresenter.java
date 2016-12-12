package com.example.zane.homework.clazz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.base.BaseFragment;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.bean.TeacherHavaClass;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 */

public class ClazzFragPresenter extends BaseFragment<ClazzFragView> {

    private static final String TAG = ClazzFragPresenter.class.getSimpleName();

    public static final String CLAZZ_NAME = "clazzName";
    public static final String COURSE_NAME = "courseName";
    public static final String IMAGE = "IMAGE";
    public static final String CID = "cid";
    public static final String JID = "jid";
    public static final String POSITION_SHARE = "position_share";

    //老师的驻班班级
    private List<TeacherHavaClass.DataEntity> teaHaveClasses;
    //学生的驻班班级
    private List<StuHaveClass.DataEntity> stuHaveClasses;

    private ClazzTeaRecyAdapterPresenter adapterPresenter;
    private boolean mIsDetailsActivityStarted;
    private ClassModel classModel = ClassModel.getInstance();

    public static ClazzFragPresenter newInstance() {
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDetailsActivityStarted = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v.init();
        adapterPresenter = new ClazzTeaRecyAdapterPresenter();

        if (MySharedPre.getInstance().getIdentity().equals("teacher")){

            classModel.teaHaveClass().subscribe(new FinalSubscriber<List<TeacherHavaClass.DataEntity>>(this, datas -> {
                teaHaveClasses = (List<TeacherHavaClass.DataEntity>) datas;
                v.initRecycleview(adapterPresenter);
            }));

        } else {
            // TODO: 16/11/4 学生的需要将课程和班级的信息融合了之后才显示 
        }
    }

    /**
     * 后期可以加入DiffUtils优化
     */
    public void refreshClazzData(){
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            Log.i("refresh", "refresh");
            classModel.teaHaveClass().subscribe(newDatas -> {
                teaHaveClasses = newDatas;
                adapterPresenter.notifyDataSetChanged();
                v.cancleSwiprefresh();
            });
        } else {
            // TODO: 2016/11/9 学生模块刷新数据 
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ClazzTeaRecyAdapterPresenter extends RecyclerView.Adapter<ClazzTeaRecyViewHolder> {

        private LayoutInflater mInflater;

        public ClazzTeaRecyAdapterPresenter() {
            mInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public ClazzTeaRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ClazzTeaRecyViewHolder(mInflater.inflate(R.layout.item_clazz, parent, false));
        }

        @Override
        public void onBindViewHolder(ClazzTeaRecyViewHolder holder, int position) {
            holder.onBind(position);
        }

        @Override
        public int getItemCount() {
            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
                return teaHaveClasses.size();
            } else {
                // TODO: 16/11/4 学生
                return stuHaveClasses.size();
            }
        }
    }

    public class ClazzTeaRecyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.imageview_item_clazz)
        ImageView imageviewItemClazz;
        @Bind(R.id.textview_item_clazzname)
        TextView textviewItemClazzname;
        @Bind(R.id.textview_item_couresename)
        TextView textviewItemCouresename;
        @Bind(R.id.textview_item_owner)
        TextView textviewItemOwner;
        @Bind(R.id.clazz_item)
        CardView clazzItem;
        @Bind(R.id.textview_item_asid)
        TextView textviewItemAsid;
        @Bind(R.id.image_item_owner)
        ImageView imageOwner;

        private int itemPosition;
        private TeacherHavaClass.DataEntity teaData;

        public ClazzTeaRecyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void onBind(int position) {
            itemPosition = position;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageviewItemClazz.setTransitionName(String.valueOf(position));
            }

            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {

                teaData = teaHaveClasses.get(position);
                textviewItemCouresename.setText(teaData.getCourse());
                textviewItemClazzname.setText(teaData.getClassname());
                textviewItemOwner.setText(teaData.getCreator());
                textviewItemAsid.setText("CID " + teaData.getCid());

                TeacherLogin.getInstacne().setAvatar(RandomBackImage.getRandomAvatar());

                Glide.with(App.getInstance())
                        .load(TeacherLogin.getInstacne().getAvatar())
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageviewItemClazz);
            } else {
                StudentLogin.getInstacne().setAvatar(RandomBackImage.getRandomAvatar());
                Glide.with(App.getInstance())
                        .load(StudentLogin.getInstacne().getAvatar())
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageviewItemClazz);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ClazzDetailActivityPresenter.class);
            intent.putExtra(POSITION_SHARE, itemPosition);

            if (MySharedPre.getInstance().getIdentity().equals("teacher")) {
                intent.putExtra(CLAZZ_NAME, teaData.getClassname());
                intent.putExtra(COURSE_NAME, teaData.getCourse());
                intent.putExtra(IMAGE, TeacherLogin.getInstacne().getAvatar());
                intent.putExtra(CID, teaData.getCid());
                intent.putExtra(JID, teaData.getJid());
            } else {
                // TODO: 2016/11/14 学生模块的跳转
            }

            if (Build.VERSION.SDK_INT > 21) {
                mIsDetailsActivityStarted = true;
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                        imageviewItemClazz, imageviewItemClazz.getTransitionName()).toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
