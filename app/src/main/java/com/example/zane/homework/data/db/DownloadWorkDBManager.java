package com.example.zane.homework.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zane.homework.app.App;

/**
 * Created by Zane on 2016/11/15.
 * Email: zanebot96@gmail.com
 * Blog: zane96.github.io
 */

public class DownloadWorkDBManager {

    private final static int VERSION = 1;
    private final static String DB_NAME = "DownloadWork.db";
    private SQLiteDatabase database_write;
    private SQLiteDatabase database_read;
    private DownloadWorkDBHelper helper;

    private DownloadWorkDBManager(){
        helper = new DownloadWorkDBHelper(App.getInstance(), DB_NAME, null, VERSION);
        database_read = helper.getReadableDatabase();
        database_write = helper.getWritableDatabase();
    }

    private static class SingltonHolder{
        private static final DownloadWorkDBManager instance = new DownloadWorkDBManager();
    }

    public static DownloadWorkDBManager getInstance(){
        return SingltonHolder.instance;
    }

    /**
     * 插入，存在的话就修改
     * @param name
     * @param asid
     * @param attachment
     * @param filePath
     * @param kind
     */
    public void insert(String name, String asid, String attachment, String filePath, String kind){
        Cursor cursor = database_read.query(DownloadWorkDBHelper.TABLE_NAME, null,
                DownloadWorkDBHelper.ASID + "=? and " + DownloadWorkDBHelper.KIND + "=?",
                new String[]{asid, kind}, null, null, null);

        if (cursor.getCount() == 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DownloadWorkDBHelper.ATTACHMENT, attachment);
            contentValues.put(DownloadWorkDBHelper.NAME, name);
            contentValues.put(DownloadWorkDBHelper.ASID, asid);
            contentValues.put(DownloadWorkDBHelper.FILEPATH, filePath);
            database_write.insert(DownloadWorkDBHelper.TABLE_NAME, null, contentValues);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DownloadWorkDBHelper.ATTACHMENT, attachment);
            contentValues.put(DownloadWorkDBHelper.FILEPATH, filePath);
            database_write.update(DownloadWorkDBHelper.TABLE_NAME, contentValues, DownloadWorkDBHelper.ASID + "=?", new String[]{asid});
        }

        cursor.close();
    }

    /**
     * 查询文件名
     * @param asid
     * @param kind
     * @return 文件名
     */
    public String queryAttachment(String asid,String kind){
        Cursor cursor = database_read.query(DownloadWorkDBHelper.TABLE_NAME, null,
                DownloadWorkDBHelper.ASID + "=? and " + DownloadWorkDBHelper.KIND + "=?",
                new String[]{asid, kind}, null, null, null);

        String attachment = null;
        boolean hasNext = cursor.moveToFirst();
        if (hasNext){
           attachment  = cursor.getString(cursor.getColumnIndex(DownloadWorkDBHelper.ATTACHMENT));
        }
        cursor.close();
        return attachment;
    }

    /**
     * 查询文件的路径
     * @param asid
     * @param kind
     * @return 文件路径名
     */
    public String queryFilePath(String asid,String kind){
        Cursor cursor = database_read.query(DownloadWorkDBHelper.TABLE_NAME, null,
                DownloadWorkDBHelper.ASID + "=? and " + DownloadWorkDBHelper.KIND + "=?",
                new String[]{asid, kind}, null, null, null);

        String attachment = null;
        boolean hasNext = cursor.moveToNext();
        if (hasNext){
            attachment  = cursor.getString(cursor.getColumnIndex(DownloadWorkDBHelper.FILEPATH));
        }
        cursor.close();
        return attachment;
    }
}
