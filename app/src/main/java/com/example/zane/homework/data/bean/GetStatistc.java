package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**某个学生的作业得分情况
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class GetStatistc implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : {"absent":7,"score":"1.0762","total":"20","coursename":"数据库原理"}
     */

    private int status;
    private String message;
    /**
     * absent : 7
     * score : 1.0762
     * total : 20
     * coursename : 数据库原理
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
        private int absent;
        private String score;
        private String total;
        private String coursename;

        public int getAbsent() {
            return absent;
        }

        public void setAbsent(int absent) {
            this.absent = absent;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCoursename() {
            return coursename;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.absent);
            dest.writeString(this.score);
            dest.writeString(this.total);
            dest.writeString(this.coursename);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.absent = in.readInt();
            this.score = in.readString();
            this.total = in.readString();
            this.coursename = in.readString();
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

    public GetStatistc() {
    }

    protected GetStatistc(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetStatistc> CREATOR = new Parcelable.Creator<GetStatistc>() {
        @Override
        public GetStatistc createFromParcel(Parcel source) {
            return new GetStatistc(source);
        }

        @Override
        public GetStatistc[] newArray(int size) {
            return new GetStatistc[size];
        }
    };
}
