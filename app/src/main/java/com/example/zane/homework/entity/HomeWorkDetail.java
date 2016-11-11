package com.example.zane.homework.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.easymvp.base.IListModel;
import com.example.zane.homework.data.bean.GetNoDueWork;
import com.example.zane.homework.data.bean.NoDueHomeWork;
import com.example.zane.homework.data.sp.MySharedPre;

/** 老师和学生的未过期作业集合
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class HomeWorkDetail implements IListModel, Parcelable{

    public static final int TEA_WORK = 1234;
    public static final int STU_WORK = 4321;

    @Override
    public int getModelViewType() {
        if (MySharedPre.getInstance().getIdentity().equals("teacher")){
            return TEA_WORK;
        } else {
            return STU_WORK;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public HomeWorkDetail() {
    }

    protected HomeWorkDetail(Parcel in) {
    }

    public static final Creator<HomeWorkDetail> CREATOR = new Creator<HomeWorkDetail>() {
        @Override
        public HomeWorkDetail createFromParcel(Parcel source) {
            return new HomeWorkDetail(source);
        }

        @Override
        public HomeWorkDetail[] newArray(int size) {
            return new HomeWorkDetail[size];
        }
    };
}
