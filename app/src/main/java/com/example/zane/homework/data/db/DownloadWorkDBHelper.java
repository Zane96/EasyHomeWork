package com.example.zane.homework.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 存储记录下载了的作业
 * Created by Zane on 2016/11/15.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class DownloadWorkDBHelper extends SQLiteOpenHelper{

    private final static String CREATE_TABLE = "create table if not exists ";
    private final static String ID = "id";
    public final static String TABLE_NAME = "downloadwork";
    private final static String INTEGER_PRIMARY_KEY = " integer primary key autoincrement,";
    public final static String ASID = "asid";
    public final static String KIND = "kind"; //1是学生的提交作业，2是老师上传的作业附件
    public final static String ATTACHMENT = "attachment";
    public final static String NAME = "name";
    public final static String FILEPATH = "filepath";
    private final static String VARCHAR_32 = " varchar(32),";
    private final static String VARCHAR_8 = " varchar(8),";
    private final static String VARCHAR_16 = " VARCHAR(16),";
    private final static String START_SQL = "(" + ID + INTEGER_PRIMARY_KEY;
    private final static String END_SQL = " VARCHAR(16)" + ");";

    private String sql;

    public DownloadWorkDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = CREATE_TABLE + TABLE_NAME + START_SQL + ASID + VARCHAR_8 + KIND + VARCHAR_8 + NAME + VARCHAR_8 + FILEPATH + VARCHAR_32 + ATTACHMENT + END_SQL;

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.delete(TABLE_NAME, null, null);
            db.execSQL(sql);
        }
    }
}
