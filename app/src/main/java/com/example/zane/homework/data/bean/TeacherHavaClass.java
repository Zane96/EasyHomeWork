package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**简要描述：老师查看驻班班级
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class TeacherHavaClass implements Parcelable {

    /**
     * status : 200
     * message : exist
     * data : [{"classid":"1","course":"数据库原理","classname":"2014级信息安全卓越班","creator":"sugar"},{"classid":"2","course":"数据库原理","classname":"2014级经济管理学院信管实验班","creator":"sugar"}]
     */

    private int status;
    private String message;
    /**
     * classid : 1
     * course : 数据库原理
     * classname : 2014级信息安全卓越班
     * creator : sugar
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

    public static class DataEntity implements Parcelable {
        private String jid;
        private String cid;
        private String course;
        private String classname;
        private String creator;

        public String getJid() {
            return jid;
        }

        public void setJid(String jid) {
            this.jid = jid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.jid);
            dest.writeString(this.cid);
            dest.writeString(this.course);
            dest.writeString(this.classname);
            dest.writeString(this.creator);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.jid = in.readString();
            this.cid = in.readString();
            this.course = in.readString();
            this.classname = in.readString();
            this.creator = in.readString();
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

    public TeacherHavaClass() {
    }

    protected TeacherHavaClass(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<TeacherHavaClass> CREATOR = new Parcelable.Creator<TeacherHavaClass>() {
        @Override
        public TeacherHavaClass createFromParcel(Parcel source) {
            return new TeacherHavaClass(source);
        }

        @Override
        public TeacherHavaClass[] newArray(int size) {
            return new TeacherHavaClass[size];
        }
    };
}
