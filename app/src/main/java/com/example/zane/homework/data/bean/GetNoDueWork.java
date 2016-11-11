package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.easymvp.base.IListModel;
import com.example.zane.homework.entity.HomeWorkDetail;

import java.util.ArrayList;
import java.util.List;

/**学生查看未过期作业列表
 * Created by Zane on 16/11/1.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class GetNoDueWork implements Parcelable {

    /**
     * status : 200
     * message : 0k
     * data : [{"asid":"40","percentage":"0.21","attachement":"20161030191924666.jpg","deadline":"2016-12-25","pubtime":"2016-10-30","addtion":"数学作业，大家好好做","isFinish":"0"}]
     */

    private int status;
    private String message;
    /**
     * asid : 40
     * percentage : 0.21
     * attachement : 20161030191924666.jpg
     * deadline : 2016-12-25
     * pubtime : 2016-10-30
     * addtion : 数学作业，大家好好做
     * isFinish : 0
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

    public static class DataEntity extends HomeWorkDetail implements Parcelable, IListModel {
        private String asid;
        private String percentage;
        private String attachement;
        private String deadline;
        private String pubtime;
        private String addtion;
        private String isFinish;

        public String getAsid() {
            return asid;
        }

        public void setAsid(String asid) {
            this.asid = asid;
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

        public String getPubtime() {
            return pubtime;
        }

        public void setPubtime(String pubtime) {
            this.pubtime = pubtime;
        }

        public String getAddtion() {
            return addtion;
        }

        public void setAddtion(String addtion) {
            this.addtion = addtion;
        }

        public String getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(String isFinish) {
            this.isFinish = isFinish;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.asid);
            dest.writeString(this.percentage);
            dest.writeString(this.attachement);
            dest.writeString(this.deadline);
            dest.writeString(this.pubtime);
            dest.writeString(this.addtion);
            dest.writeString(this.isFinish);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.asid = in.readString();
            this.percentage = in.readString();
            this.attachement = in.readString();
            this.deadline = in.readString();
            this.pubtime = in.readString();
            this.addtion = in.readString();
            this.isFinish = in.readString();
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

    public GetNoDueWork() {
    }

    protected GetNoDueWork(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetNoDueWork> CREATOR = new Parcelable.Creator<GetNoDueWork>() {
        @Override
        public GetNoDueWork createFromParcel(Parcel source) {
            return new GetNoDueWork(source);
        }

        @Override
        public GetNoDueWork[] newArray(int size) {
            return new GetNoDueWork[size];
        }
    };
}
