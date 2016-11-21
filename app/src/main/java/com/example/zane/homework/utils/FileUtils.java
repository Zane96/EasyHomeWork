package com.example.zane.homework.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zane.homework.BuildConfig;
import com.example.zane.homework.app.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class FileUtils {

    public static final int OPEN_FFILE = 2333;
    private static final Context mApplicationContext = App.getInstance();

    public static void openFileManager(AppCompatActivity appCompatActivity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        appCompatActivity.startActivityForResult(intent, OPEN_FFILE);
    }

    public static File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if(!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            cachePath = mApplicationContext.getCacheDir().getPath();
        } else {
            cachePath = mApplicationContext.getExternalCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 将文件写入SD卡私有外存
     * @param inputStream
     * @param file
     */
    public static void writeFile(InputStream inputStream, File file){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) >= 0){
                fos.write(buffer, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打开文件
     * @param filePath
     * @return
     */
    public static Intent openFile(String filePath){
        Log.i("fileutils", filePath);
        File file = new File(filePath);
        if(!file.exists()) return null;
        Uri uri = FileProvider.getUriForFile(App.getInstance(), "provider", file);
        Log.i("fileutils", uri+"");
        /* 取得扩展名 */
        String end=file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||
                   end.equals("xmf")||end.equals("ogg")||end.equals("wav")){
            return getAudioFileIntent(uri);
        }else if(end.equals("3gp")||end.equals("mp4")){
            return getAudioFileIntent(uri);
        }else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||
                         end.equals("jpeg")||end.equals("bmp")){
            return getImageFileIntent(uri);
        }else if(end.equals("ppt")){
            return getPptFileIntent(uri);
        }else if(end.equals("xls")){
            return getExcelFileIntent(uri);
        }else if(end.equals("doc") || end.equals("docx")){
            return getWordFileIntent(uri);
        }else if(end.equals("pdf")){
            return getPdfFileIntent(uri);
        }else if(end.equals("txt")){
            return getTextFileIntent(uri);
        } else {
            return null;
        }
    }

    //Android获取一个用于打开VIDEO文件的intent
    private static Intent getVideoFileIntent(Uri uri) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    private static Intent getAudioFileIntent(Uri uri){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(Uri uri) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    private static Intent getPptFileIntent(Uri uri){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    private static Intent getExcelFileIntent(Uri uri){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(Uri uri){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    private static Intent getTextFileIntent(Uri uri){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    private static Intent getPdfFileIntent(Uri uri){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
}
