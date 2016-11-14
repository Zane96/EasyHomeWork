package com.example.zane.homework.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文件下载
 * Created by Zane on 2016/11/14.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class FileDownload implements Parcelable{
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

    public FileDownload() {
    }

    protected FileDownload(Parcel in) {
        this.progress = in.readInt();
        this.currentFileSize = in.readLong();
        this.totalFileSize = in.readLong();
    }

    public static final Creator<FileDownload> CREATOR = new Creator<FileDownload>() {
        @Override
        public FileDownload createFromParcel(Parcel source) {
            return new FileDownload(source);
        }

        @Override
        public FileDownload[] newArray(int size) {
            return new FileDownload[size];
        }
    };
}
