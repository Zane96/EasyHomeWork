package com.example.zane.homework.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

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
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) >= 0){
                fos.write(buffer, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
