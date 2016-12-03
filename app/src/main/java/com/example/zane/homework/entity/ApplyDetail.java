package com.example.zane.homework.entity;

import com.example.zane.easymvp.base.IListModel;
import com.example.zane.homework.data.bean.ShowApply;

/**
 * 申请加入信息有老师和学生两种
 * Created by Zane on 2016/12/2.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class ApplyDetail implements IListModel{
    private ShowApply.DataEntity.StuEntity stuEntity;
    private ShowApply.DataEntity.TeachEntity teachEntity;
    public static final int STUAPPLY = 666;
    public static final int  TEAAPPLY = 555;

    public ShowApply.DataEntity.TeachEntity getTeachEntity() {
        return teachEntity;
    }

    public void setTeachEntity(ShowApply.DataEntity.TeachEntity teachEntity) {
        this.teachEntity = teachEntity;
    }

    public ShowApply.DataEntity.StuEntity getStuEntity() {
        return stuEntity;
    }

    public void setStuEntity(ShowApply.DataEntity.StuEntity stuEntity) {
        this.stuEntity = stuEntity;
    }

    @Override
    public int getModelViewType() {
        if (teachEntity != null){
            return TEAAPPLY;
        } else {
            return STUAPPLY;
        }
    }
}
