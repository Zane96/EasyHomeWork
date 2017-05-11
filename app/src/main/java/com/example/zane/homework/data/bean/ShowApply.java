package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.zane.easymvp.base.IListModel;
import com.example.zane.homework.entity.ApplyDetail;

import java.util.ArrayList;
import java.util.List;

/**简要描述：查看某个班的申请信息
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class ShowApply implements Parcelable {

    /**
     * status : 200
     * message : ok
     * data : {"teach":[{"apid":"2","realname":"夏英","selfintro":"同学们要记住，写好文档是很重要","course":"数据库原理","done":"已处理","addtion":"卓越班数据原理课程"}],"stu":[{"apid":"4","realname":"苏丹","selfintro":"我是苏丹，请多多关照","done":"已处理"}]}
     */

    private int status;
    private String message;
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

    public static class DataEntity implements Parcelable{
        /**
         * apid : 2
         * realname : 夏英
         * selfintro : 同学们要记住，写好文档是很重要
         * course : 数据库原理
         * done : 已处理
         * addtion : 卓越班数据原理课程
         */

        private List<TeachEntity> teach;
        /**
         * apid : 4
         * realname : 苏丹
         * selfintro : 我是苏丹，请多多关照
         * done : 已处理
         */

        private List<StuEntity> stu;

        public List<TeachEntity> getTeach() {
            return teach;
        }

        public void setTeach(List<TeachEntity> teach) {
            this.teach = teach;
        }

        public List<StuEntity> getStu() {
            return stu;
        }

        public void setStu(List<StuEntity> stu) {
            this.stu = stu;
        }

        public static class TeachEntity extends ApplyDetail implements Parcelable {
            private String apid;
            private String realname;
            private String selfintro;
            private String course;
            private String done;
            private String addtion;
            private String total;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getApid() {
                return apid;
            }

            public void setApid(String apid) {
                this.apid = apid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getSelfintro() {
                return selfintro;
            }

            public void setSelfintro(String selfintro) {
                this.selfintro = selfintro;
            }

            public String getCourse() {
                return course;
            }

            public void setCourse(String course) {
                this.course = course;
            }

            public String getDone() {
                return done;
            }

            public void setDone(String done) {
                this.done = done;
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
                dest.writeString(this.apid);
                dest.writeString(this.realname);
                dest.writeString(this.selfintro);
                dest.writeString(this.course);
                dest.writeString(this.done);
                dest.writeString(this.addtion);
            }

            public TeachEntity() {
            }

            protected TeachEntity(Parcel in) {
                this.apid = in.readString();
                this.realname = in.readString();
                this.selfintro = in.readString();
                this.course = in.readString();
                this.done = in.readString();
                this.addtion = in.readString();
            }

            public static final Creator<TeachEntity> CREATOR = new Creator<TeachEntity>() {
                @Override
                public TeachEntity createFromParcel(Parcel source) {
                    return new TeachEntity(source);
                }

                @Override
                public TeachEntity[] newArray(int size) {
                    return new TeachEntity[size];
                }
            };
        }

        public static class StuEntity extends ApplyDetail implements Parcelable {

            private String apid;
            private String realname;
            private String selfintro;
            private String done;

            public String getApid() {
                return apid;
            }

            public void setApid(String apid) {
                this.apid = apid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getSelfintro() {
                return selfintro;
            }

            public void setSelfintro(String selfintro) {
                this.selfintro = selfintro;
            }

            public String getDone() {
                return done;
            }

            public void setDone(String done) {
                this.done = done;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.apid);
                dest.writeString(this.realname);
                dest.writeString(this.selfintro);
                dest.writeString(this.done);
            }

            public StuEntity() {
            }

            protected StuEntity(Parcel in) {
                this.apid = in.readString();
                this.realname = in.readString();
                this.selfintro = in.readString();
                this.done = in.readString();
            }

            public static final Creator<StuEntity> CREATOR = new Creator<StuEntity>() {
                @Override
                public StuEntity createFromParcel(Parcel source) {
                    return new StuEntity(source);
                }

                @Override
                public StuEntity[] newArray(int size) {
                    return new StuEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.teach);
            dest.writeList(this.stu);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.teach = new ArrayList<TeachEntity>();
            in.readList(this.teach, TeachEntity.class.getClassLoader());
            this.stu = new ArrayList<StuEntity>();
            in.readList(this.stu, StuEntity.class.getClassLoader());
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

    public ShowApply() {
    }

    protected ShowApply(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShowApply> CREATOR = new Parcelable.Creator<ShowApply>() {
        @Override
        public ShowApply createFromParcel(Parcel source) {
            return new ShowApply(source);
        }

        @Override
        public ShowApply[] newArray(int size) {
            return new ShowApply[size];
        }
    };
}
