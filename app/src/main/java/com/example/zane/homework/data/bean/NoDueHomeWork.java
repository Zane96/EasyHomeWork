package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**老师查看自己布置的未过期的作业列表
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class NoDueHomeWork implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : [{"asid":"40","cid":"7","percentage":"0.21","attachement":"20161030191924666.jpg","deadline":"2016-12-25","course":"数据库原理","name":"2014级信息安全卓越班"}]
     */

    private int status;
    private String message;
    /**
     * asid : 40
     * cid : 7
     * percentage : 0.21
     * attachement : 20161030191924666.jpg
     * deadline : 2016-12-25
     * course : 数据库原理
     * name : 2014级信息安全卓越班
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
        private String asid;
        private String cid;
        private String percentage;
        private String attachement;
        private String deadline;
        private String course;
        private String name;

        public String getAsid() {
            return asid;
        }

        public void setAsid(String asid) {
            this.asid = asid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getAttachement() {
            return attachement;
        }

        public void setAttachement(String attachement) {
            this.attachement = attachement;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.asid);
            dest.writeString(this.cid);
            dest.writeString(this.percentage);
            dest.writeString(this.attachement);
            dest.writeString(this.deadline);
            dest.writeString(this.course);
            dest.writeString(this.name);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.asid = in.readString();
            this.cid = in.readString();
            this.percentage = in.readString();
            this.attachement = in.readString();
            this.deadline = in.readString();
            this.course = in.readString();
            this.name = in.readString();
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

    public NoDueHomeWork() {
    }

    protected NoDueHomeWork(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<NoDueHomeWork> CREATOR = new Parcelable.Creator<NoDueHomeWork>() {
        @Override
        public NoDueHomeWork createFromParcel(Parcel source) {
            return new NoDueHomeWork(source);
        }

        @Override
        public NoDueHomeWork[] newArray(int size) {
            return new NoDueHomeWork[size];
        }
    };
}
