package com.example.zane.homework.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.zane.homework.app.App;

import java.io.File;

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
}
