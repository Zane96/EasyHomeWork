package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**简要描述：注销登录
 * Created by Zane on 16/10/24.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class QuitLogin implements Parcelable {

    /**
     * status : 200
     * message : destroy succesfull
     * data : {"sessionid":[]}
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

    public static class DataEntity implements Parcelable {
        private List<String> sessionid;

        public List<String> getSessionid() {
            return sessionid;
        }

        public void setSessionid(List<String> sessionid) {
            this.sessionid = sessionid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringList(this.sessionid);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.sessionid = in.createStringArrayList();
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

    public QuitLogin() {
    }

    protected QuitLogin(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<QuitLogin> CREATOR = new Parcelable.Creator<QuitLogin>() {
        @Override
        public QuitLogin createFromParcel(Parcel source) {
            return new QuitLogin(source);
        }

        @Override
        public QuitLogin[] newArray(int size) {
            return new QuitLogin[size];
        }
    };
}
