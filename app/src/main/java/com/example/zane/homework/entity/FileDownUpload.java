package com.example.zane.homework.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文件下载
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class FileDownUpload implements Parcelable{
    private int progress;
    private long currentFileSize;
    private long totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.progress);
        dest.writeLong(this.currentFileSize);
        dest.writeLong(this.totalFileSize);
    }

    public FileDownUpload() {
    }

    protected FileDownUpload(Parcel in) {
        this.progress = in.readInt();
        this.currentFileSize = in.readLong();
        this.totalFileSize = in.readLong();
    }

    public static final Creator<FileDownUpload> CREATOR = new Creator<FileDownUpload>() {
        @Override
        public FileDownUpload createFromParcel(Parcel source) {
            return new FileDownUpload(source);
        }

        @Override
        public FileDownUpload[] newArray(int size) {
            return new FileDownUpload[size];
        }
    };
}
