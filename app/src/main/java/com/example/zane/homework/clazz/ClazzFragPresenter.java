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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.homework.R;
import com.example.zane.homework.app.App;
import com.example.zane.homework.clazzdetail.presenter.ClazzDetailActivityPresenter;
import com.example.zane.homework.custom.CircleTransform;
import com.example.zane.homework.entity.Clazz;
import com.example.zane.homework.event.ActivityExitingEvent;
import com.example.zane.homework.utils.RandomBackImage;
import com.jude.utils.JUtils;

import org.greenrobot.eventbus.EventBus;

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

    private ProgressHandler progressHandler;
    private List<Clazz> datas;
    private ClazzRecyAdapterPresenter adapterPresenter;
    private boolean mIsDetailsActivityStarted;

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
        adapterPresenter = new ClazzRecyAdapterPresenter();
        v.showProgress();

        Message message = new Message();
        message.what = 1;
        progressHandler.sendMessageDelayed(message, 1500);

//        adapterPresenter.setOnRecycleViewItemClickListener(new BaseListAdapterPresenter.OnRecycleViewItemClickListener() {
//            @Override
//            public void onClick(View view, int i) {
////                Intent intent = new Intent(getActivity(), ClazzDetailActivityPresenter.class);
////                intent.putExtra(CLAZZ_NAME, datas.get(i).getClassName());
////                intent.putExtra(COURSE_NAME, datas.get(i).getCourseName());
////                intent.putExtra(IMAGE, datas.get(i).getImage());
////                getActivity().startActivity(intent);
//                ActivityExitingEvent event = new ActivityExitingEvent();
//                event.setActivity(getActivity());
//                event.setClazz(datas.get(i));
//                event.setPosition(i);
//                EventBus.getDefault().post(event);
//            }
//
//            @Override
//            public void onLongClick(View view, int i) {
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressHandler.removeMessages(1);
    }

    private final static class ProgressHandler extends Handler {
        private WeakReference<ClazzFragPresenter> reference;

        public ProgressHandler(ClazzFragPresenter activity) {
            reference = new WeakReference<ClazzFragPresenter>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                switch (msg.what) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            Clazz clazz = new Clazz();
                            clazz.setImage(RandomBackImage.getRandomAvatar());
                            reference.get().datas.add(clazz);
                        }
                        reference.get().v.initRecycleview(reference.get().adapterPresenter);
                        JUtils.Toast(reference.get().getResources().getString(R.string.finish_load));
                        reference.get().v.dissmissProgress();
                        break;
                }
            }
        }
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
            return datas.size();
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

        private int itemPosition;

        public ClazzRecyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void onBind(int position) {
            itemPosition = position;
            Glide.with(App.getInstance())
                    .load(datas.get(position).getImage())
                    .transform(new CircleTransform(App.getInstance()))
                    .into(imageviewItemClazz);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageviewItemClazz.setTransitionName(String.valueOf(position));
            }
            textviewItemCouresename.setText(datas.get(position).getCourseName());
            textviewItemClazzname.setText(datas.get(position).getClassName());
            textviewItemOwner.setText(datas.get(position).getOwner());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ClazzDetailActivityPresenter.class);
            intent.putExtra(POSITION_SHARE, itemPosition);
            intent.putExtra(CLAZZ_NAME, datas.get(itemPosition).getClassName());
            intent.putExtra(COURSE_NAME, datas.get(itemPosition).getCourseName());
            intent.putExtra(IMAGE, datas.get(itemPosition).getImage());
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
