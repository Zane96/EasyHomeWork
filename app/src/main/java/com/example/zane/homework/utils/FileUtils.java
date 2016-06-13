package com.example.zane.homework.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class FileUtils {

    public static final int OPEN_FFILE = 2333;

    public static void openFileManager(AppCompatActivity appCompatActivity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        appCompatActivity.startActivityForResult(intent, OPEN_FFILE);
    }
}
