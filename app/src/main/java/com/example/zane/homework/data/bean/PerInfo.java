package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zane on 16/11/3.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class PerInfo implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : {"name":"夏老师","realname":"夏英","gender":"女","selfintro":"我是夏老师"}
     */

    private int status;
    private String message;
    /**
     * name : 夏老师
     * realname : 夏英
     * gender : 女
     * selfintro : 我是夏老师
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

    public static class DataEntity implements Parcelable {
        private String name;
        private String realname;
        private String gender;
        private String selfintro;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSelfintro() {
            return selfintro;
        }

        public void setSelfintro(String selfintro) {
            this.selfintro = selfintro;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.realname);
            dest.writeString(this.gender);
            dest.writeString(this.selfintro);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.realname = in.readString();
            this.gender = in.readString();
            this.selfintro = in.readString();
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

    public PerInfo() {
    }

    protected PerInfo(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<PerInfo> CREATOR = new Parcelable.Creator<PerInfo>() {
        @Override
        public PerInfo createFromParcel(Parcel source) {
            return new PerInfo(source);
        }

        @Override
        public PerInfo[] newArray(int size) {
            return new PerInfo[size];
        }
    };
}
