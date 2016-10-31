package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**查看学生完成的作业的批改情况
 * Created by Zane on 16/10/31.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class HoPerson implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : [{"name":"苏苏","attach":"20161030193307218.docx","finishtime":"2016-10-30","grade":"C","addtion":"你做的很好"}]
     */

    private int status;
    private String message;
    /**
     * name : 苏苏
     * attach : 20161030193307218.docx
     * finishtime : 2016-10-30
     * grade : C
     * addtion : 你做的很好
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
        private String name;
        private String attach;
        private String finishtime;
        private String grade;
        private String addtion;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
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
            dest.writeString(this.name);
            dest.writeString(this.attach);
            dest.writeString(this.finishtime);
            dest.writeString(this.grade);
            dest.writeString(this.addtion);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.attach = in.readString();
            this.finishtime = in.readString();
            this.grade = in.readString();
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

    public HoPerson() {
    }

    protected HoPerson(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<HoPerson> CREATOR = new Parcelable.Creator<HoPerson>() {
        @Override
        public HoPerson createFromParcel(Parcel source) {
            return new HoPerson(source);
        }

        @Override
        public HoPerson[] newArray(int size) {
            return new HoPerson[size];
        }
    };
}
