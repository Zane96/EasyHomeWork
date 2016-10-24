package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**简要描述：学生查看驻班班级
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class StuHaveClass implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : [{"cid":"21","classname":"信管实验班","creatime":"2016-10-24","creator":"ture","description":"腊鸡"},{"cid":"22","classname":"信管实验班","creatime":"2016-10-24","creator":"ture","description":"腊鸡"},{"cid":"23","classname":"信管实验班","creatime":"2016-10-24","creator":"ture","description":"腊鸡"}]
     */

    private int status;
    private String message;
    /**
     * cid : 21
     * classname : 信管实验班
     * creatime : 2016-10-24
     * creator : ture
     * description : 腊鸡
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
        private String cid;
        private String classname;
        private String creatime;
        private String creator;
        private String description;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
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
            dest.writeString(this.cid);
            dest.writeString(this.classname);
            dest.writeString(this.creatime);
            dest.writeString(this.creator);
            dest.writeString(this.description);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.cid = in.readString();
            this.classname = in.readString();
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
        dest.writeList(this.data);
    }

    public StuHaveClass() {
    }

    protected StuHaveClass(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<StuHaveClass> CREATOR = new Parcelable.Creator<StuHaveClass>() {
        @Override
        public StuHaveClass createFromParcel(Parcel source) {
            return new StuHaveClass(source);
        }

        @Override
        public StuHaveClass[] newArray(int size) {
            return new StuHaveClass[size];
        }
    };
}
