package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**学生查看自己提交的某份作业详情
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class GetHoWork implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : [{"asinattachment":"20161030191924666.jpg","hoattachment":"20161030193307218.docx","addtion":"数学作业，大家好好做","deadline":"2016-12-25"}]
     */

    private int status;
    private String message;
    /**
     * asinattachment : 20161030191924666.jpg
     * hoattachment : 20161030193307218.docx
     * addtion : 数学作业，大家好好做
     * deadline : 2016-12-25
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
        private String asinattachment;
        private String hoattachment;
        private String addtion;
        private String deadline;

        public String getAsinattachment() {
            return asinattachment;
        }

        public void setAsinattachment(String asinattachment) {
            this.asinattachment = asinattachment;
        }

        public String getHoattachment() {
            return hoattachment;
        }

        public void setHoattachment(String hoattachment) {
            this.hoattachment = hoattachment;
        }

        public String getAddtion() {
            return addtion;
        }

        public void setAddtion(String addtion) {
            this.addtion = addtion;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.asinattachment);
            dest.writeString(this.hoattachment);
            dest.writeString(this.addtion);
            dest.writeString(this.deadline);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.asinattachment = in.readString();
            this.hoattachment = in.readString();
            this.addtion = in.readString();
            this.deadline = in.readString();
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

    public GetHoWork() {
    }

    protected GetHoWork(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetHoWork> CREATOR = new Parcelable.Creator<GetHoWork>() {
        @Override
        public GetHoWork createFromParcel(Parcel source) {
            return new GetHoWork(source);
        }

        @Override
        public GetHoWork[] newArray(int size) {
            return new GetHoWork[size];
        }
    };
}
