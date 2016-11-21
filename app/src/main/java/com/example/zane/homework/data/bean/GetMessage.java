package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.easymvp.base.IListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class GetMessage implements Parcelable {

    /**
     * status : 200
     * 1 : ok
     * data : [{"mid":"4","poster":"10","reciever":"8","isRead":"0"},{"mid":"5","poster":"10","reciever":"8","isRead":"0"}]
     */

    private int status;
    @SerializedName("1")
    private String value1;
    /**
     * mid : 4
     * poster : 10
     * reciever : 8
     * isRead : 0
     */

    private List<DataEntity> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity implements Parcelable, IListModel {
        private String mid;
        private String poster;
        private String reciever;
        private String isRead;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getReciever() {
            return reciever;
        }

        public void setReciever(String reciever) {
            this.reciever = reciever;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mid);
            dest.writeString(this.poster);
            dest.writeString(this.reciever);
            dest.writeString(this.isRead);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.mid = in.readString();
            this.poster = in.readString();
            this.reciever = in.readString();
            this.isRead = in.readString();
        }

        public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
            @Override
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            @Override
            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };

        @Override
        public int getModelViewType() {
            return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.value1);
        dest.writeList(this.data);
    }

    public GetMessage() {
    }

    protected GetMessage(Parcel in) {
        this.status = in.readInt();
        this.value1 = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetMessage> CREATOR = new Parcelable.Creator<GetMessage>() {
        @Override
        public GetMessage createFromParcel(Parcel source) {
            return new GetMessage(source);
        }

        @Override
        public GetMessage[] newArray(int size) {
            return new GetMessage[size];
        }
    };
}
