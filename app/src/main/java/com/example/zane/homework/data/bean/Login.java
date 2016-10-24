package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**简要描述：学生/老师登录验证
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class Login implements Parcelable {

    /**
     * status : 200
     * message : success
     * data : {"sessionid":"4"}
     */

    private int status;
    private String message;
    /**
     * sessionid : 4
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
        private String sessionid;

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sessionid);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.sessionid = in.readString();
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

    public Login() {
    }

    protected Login(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
