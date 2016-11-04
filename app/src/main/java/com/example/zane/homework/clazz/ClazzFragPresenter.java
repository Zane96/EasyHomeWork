package com.example.zane.homework.clazz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.data.bean.StuHaveClass;
import com.example.zane.homework.data.bean.TeacherHavaClass;
import com.example.zane.homework.data.model.ClassModel;
import com.example.zane.homework.data.remote.FinalSubscriber;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.entity.StudentLogin;
import com.example.zane.homework.entity.TeacherLogin;
import com.example.zane.homework.data.sp.MySharedPre;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 16/6/7.
 * Email: zanebot96@gmail.com
 */

public class ClazzFragPresenter extends BaseFragmentPresenter<ClazzFragView> {

    private static final String TAG = ClazzFragPresenter.class.getSimpleName();
    public static final String CLAZZ_NAME = "clazzName";
    public static final String COURSE_NAME = "courseName";
    public static final String IMAGE = "IMAGE";
    public static final String POSITION_SHARE = "position_share";

    //老师的驻班班级
    private List<TeacherHavaClass.DataEntity> teaHaveClasses;
    //学生的驻班班级
    private List<StuHaveClass.DataEntity> stuHaveClasses;

    private ClazzRecyAdapterPresenter adapterPresenter;
    private boolean mIsDetailsActivityStarted;
    private FinalSubscriber<List<TeacherHavaClass.DataEntity>> teaClassSubscriber;
    private FinalSubscriber<List<StuHaveClass.DataEntity>> stuClassSubscriber;

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
        adapterPresenter = new ClazzRecyAdapterPresenter();
        ClassModel classModel = ClassModel.getInstance();
        Log.i(TAG, "request");
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            teaClassSubscriber = new FinalSubscriber<>(getActivity(), datas -> {
                teaHaveClasses = (List<TeacherHavaClass.DataEntity>) datas;
                v.initRecycleview(adapterPresenter);
            });
            classModel.teaHaveClass().subscribe(teaClassSubscriber);
        } else {
            // TODO: 16/11/4 学生的需要将课程和班级的信息融合了之后才显示 
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ClazzRecyAdapterPresenter extends RecyclerView.Adapter<ClazzRecyViewHolder> {

        private LayoutInflater mInflater;

        public ClazzRecyAdapterPresenter() {
            mInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public ClazzRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ClazzRecyViewHolder(mInflater.inflate(R.layout.item_clazz, parent, false));
        }

        @Override
        public void onBindViewHolder(ClazzRecyViewHolder holder, int position) {
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

    public class ClazzRecyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        public ClazzRecyViewHolder(View itemView) {
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
                TeacherHavaClass.DataEntity data = teaHaveClasses.get(position);
                textviewItemCouresename.setText(data.getCourse());
                textviewItemClazzname.setText(data.getClassname());
                textviewItemOwner.setText(data.getCreator());
                textviewItemAsid.setText(data.getClassid());
                TeacherLogin.getInstacne().setAvatar(RandomBackImage.getRandomAvatar());

                Glide.with(App.getInstance())
                        .load(TeacherLogin.getInstacne().getAvatar())
                        .transform(new CircleTransform(App.getInstance()))
                        .into(imageviewItemClazz);
            } else {
                textviewItemCouresename.setText(StudentLogin.getInstacne().getCourse()[position]);
                textviewItemClazzname.setText(StudentLogin.getInstacne().getClazz());
                textviewItemOwner.setText(StudentLogin.getInstacne().getOwner());
                textviewItemAsid.setText(StudentLogin.getInstacne().getIds()[0]);
                if (StudentLogin.getInstacne().getIsOwner()){
                    imageOwner.setVisibility(View.VISIBLE);
                }
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
                intent.putExtra(CLAZZ_NAME, TeacherLogin.getInstacne().getClazz()[itemPosition]);
                intent.putExtra(COURSE_NAME, TeacherLogin.getInstacne().getCourse());
                intent.putExtra(IMAGE, TeacherLogin.getInstacne().getAvatar());
            } else {
                intent.putExtra(CLAZZ_NAME, StudentLogin.getInstacne().getClazz());
                intent.putExtra(COURSE_NAME, StudentLogin.getInstacne().getCourse()[itemPosition]);
                intent.putExtra(IMAGE, StudentLogin.getInstacne().getAvatar());
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
