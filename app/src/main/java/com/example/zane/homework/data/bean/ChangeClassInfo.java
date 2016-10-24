package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**简要描述：学生修改班级信息
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class ChangeClassInfo implements Parcelable{
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

    public ChangeClassInfo() {
    }

    protected ChangeClassInfo(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readString();
    }

    public static final Parcelable.Creator<CreatClass> CREATOR = new Parcelable.Creator<CreatClass>() {
        @Override
        public CreatClass createFromParcel(Parcel source) {
            return new CreatClass(source);
        }

        @Override
        public CreatClass[] newArray(int size) {
            return new CreatClass[size];
        }
    };
}
