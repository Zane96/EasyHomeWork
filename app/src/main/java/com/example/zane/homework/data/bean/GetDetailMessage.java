package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.easymvp.base.IListModel;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class GetDetailMessage implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : {"content":"这是第二条"}
     */

    private int status;
    private String message;
    /**
     * content : 这是第二条
     */

    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Parcelable, IListModel{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.content = in.readString();
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
        dest.writeString(this.message);
        dest.writeParcelable(this.data, flags);
    }

    public GetDetailMessage() {
    }

    protected GetDetailMessage(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetDetailMessage> CREATOR = new Parcelable.Creator<GetDetailMessage>() {
        @Override
        public GetDetailMessage createFromParcel(Parcel source) {
            return new GetDetailMessage(source);
        }

        @Override
        public GetDetailMessage[] newArray(int size) {
            return new GetDetailMessage[size];
        }
    };
}
