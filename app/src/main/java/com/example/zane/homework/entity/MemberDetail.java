package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;
import com.example.zane.homework.data.bean.ClassMemeber;
import com.example.zane.homework.data.bean.HoPerson;
import com.example.zane.homework.data.sp.MySharedPre;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class MemberDetail implements IListModel{

    private List<ClassMemeber.DataEntity> memebers;
    private List<HoPerson.DataEntity> hoPersons;

    public static final int HOMEWORK = 123;
    public static final int MEMEBER = 321;

    public List<ClassMemeber.DataEntity> getMemebers() {
        return memebers;
    }

    public void setMemebers(List<ClassMemeber.DataEntity> memebers) {
        this.memebers = memebers;
    }

    public List<HoPerson.DataEntity> getHoPersons() {
        return hoPersons;
    }

    public void setHoPersons(List<HoPerson.DataEntity> hoPersons) {
        this.hoPersons = hoPersons;
    }

    @Override
    public int getModelViewType() {
        if (hoPersons != null){
            return HOMEWORK;
        } else {
            return MEMEBER;
        }
    }
}
