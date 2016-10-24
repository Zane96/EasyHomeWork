package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**简要描述：学生/老师帐号注册
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class Registe implements Parcelable {

    /**
     * status : 200
     * message : success
     * data : 1
     */

    private int status;
    private String message;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
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
        dest.writeInt(this.data);
    }

    public Registe() {
    }

    protected Registe(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readInt();
    }

    public static final Parcelable.Creator<Registe> CREATOR = new Parcelable.Creator<Registe>() {
        @Override
        public Registe createFromParcel(Parcel source) {
            return new Registe(source);
        }

        @Override
        public Registe[] newArray(int size) {
            return new Registe[size];
        }
    };
}
