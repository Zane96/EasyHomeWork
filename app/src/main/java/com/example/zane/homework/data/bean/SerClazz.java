package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class SerClazz implements Parcelable {

    /**
     * status : 200
     * message : exist
     * data : {"name":"2014级信息安全卓越班","creatime":"2016-06-13","creator":"sugar","description":"信安牛牛初长成"}
     */

    private int status;
    private String message;
    /**
     * name : 2014级信息安全卓越班
     * creatime : 2016-06-13
     * creator : sugar
     * description : 信安牛牛初长成
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
        private String creatime;
        private String creator;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatime() {
            return creatime;
        }

        public void setCreatime(String creatime) {
            this.creatime = creatime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.creatime);
            dest.writeString(this.creator);
            dest.writeString(this.description);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.creatime = in.readString();
            this.creator = in.readString();
            this.description = in.readString();
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

    public SerClazz() {
    }

    protected SerClazz(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<SerClazz> CREATOR = new Parcelable.Creator<SerClazz>() {
        @Override
        public SerClazz createFromParcel(Parcel source) {
            return new SerClazz(source);
        }

        @Override
        public SerClazz[] newArray(int size) {
            return new SerClazz[size];
        }
    };
}
