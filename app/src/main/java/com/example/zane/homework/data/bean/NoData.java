package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**简要描述：老师/学生申请进驻某班
 * 学生修改班级信息
 * 学生创建班级
 * 发布公告
 * 老师批改作业
 * 同意学生/老师加入某班
 * 学生提交作业
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class NoData implements Parcelable {

    /**
     * status : 200
     * message : succssfully
     * data : null
     */

    private int status;
    private String message;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
        dest.writeString(this.data);
    }

    public NoData() {
    }

    protected NoData(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readString();
    }

    public static final Parcelable.Creator<NoData> CREATOR = new Parcelable.Creator<NoData>() {
        @Override
        public NoData createFromParcel(Parcel source) {
            return new NoData(source);
        }

        @Override
        public NoData[] newArray(int size) {
            return new NoData[size];
        }
    };
}
