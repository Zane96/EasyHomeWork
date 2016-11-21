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

    private ClassMemeber.DataEntity memeber;
    private HoPerson.DataEntity hoPerson;

    public static final int HOMEWORK = 123;
    public static final int MEMEBER = 321;

    public ClassMemeber.DataEntity getMemeber() {
        return memeber;
    }

    public void setMemeber(ClassMemeber.DataEntity memeber) {
        this.memeber = memeber;
    }

    public HoPerson.DataEntity getHoPerson() {
        return hoPerson;
    }

    public void setHoPerson(HoPerson.DataEntity hoPerson) {
        this.hoPerson = hoPerson;
    }

    @Override
    public int getModelViewType() {
        if (hoPerson != null){
            return HOMEWORK;
        } else {
            return MEMEBER;
        }
    }
}
