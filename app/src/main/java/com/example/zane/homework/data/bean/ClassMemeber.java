package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.homework.entity.MemberDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class ClassMemeber implements Parcelable {

    /**
     * status : 200
     * message : exist
     * data : [{"sid":"1","name":"Dafanzi","gender":"男","isadmin":false,"selfintro":"我是一个快乐的学生"},{"sid":"4","name":"sugar","gender":"女","isadmin":false,"selfintro":"我是苏丹，请多多关照"}]
     */

    private int status;
    private String message;
    /**
     * sid : 1
     * name : Dafanzi
     * gender : 男
     * isadmin : false
     * selfintro : 我是一个快乐的学生
     */

    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity extends MemberDetail implements Parcelable {
        private String sid;
        private String name;
        private String gender;
        private boolean isadmin;
        private String selfintro;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public boolean isIsadmin() {
            return isadmin;
        }

        public void setIsadmin(boolean isadmin) {
            this.isadmin = isadmin;
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
            dest.writeString(this.sid);
            dest.writeString(this.name);
            dest.writeString(this.gender);
            dest.writeByte(this.isadmin ? (byte) 1 : (byte) 0);
            dest.writeString(this.selfintro);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.sid = in.readString();
            this.name = in.readString();
            this.gender = in.readString();
            this.isadmin = in.readByte() != 0;
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
        dest.writeList(this.data);
    }

    public ClassMemeber() {
    }

    protected ClassMemeber(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClassMemeber> CREATOR = new Parcelable.Creator<ClassMemeber>() {
        @Override
        public ClassMemeber createFromParcel(Parcel source) {
            return new ClassMemeber(source);
        }

        @Override
        public ClassMemeber[] newArray(int size) {
            return new ClassMemeber[size];
        }
    };
}
