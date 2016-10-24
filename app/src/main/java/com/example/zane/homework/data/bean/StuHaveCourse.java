package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**简要描述：同学查看已加入课程
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuHaveCourse implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : [{"jid":"5","sid":"2","course":"数据库原理","addtion":"写好文档是个好习惯"},{"jid":"5","sid":"2","course":"数据库原理","addtion":"写好文档是个好习惯"}]
     */

    private int status;
    private String message;
    /**
     * jid : 5
     * sid : 2
     * course : 数据库原理
     * addtion : 写好文档是个好习惯
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
        private String sid;
        private String course;
        private String addtion;

        public String getJid() {
            return jid;
        }

        public void setJid(String jid) {
            this.jid = jid;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getAddtion() {
            return addtion;
        }

        public void setAddtion(String addtion) {
            this.addtion = addtion;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.jid);
            dest.writeString(this.sid);
            dest.writeString(this.course);
            dest.writeString(this.addtion);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.jid = in.readString();
            this.sid = in.readString();
            this.course = in.readString();
            this.addtion = in.readString();
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

    public StuHaveCourse() {
    }

    protected StuHaveCourse(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<StuHaveCourse> CREATOR = new Parcelable.Creator<StuHaveCourse>() {
        @Override
        public StuHaveCourse createFromParcel(Parcel source) {
            return new StuHaveCourse(source);
        }

        @Override
        public StuHaveCourse[] newArray(int size) {
            return new StuHaveCourse[size];
        }
    };
}
