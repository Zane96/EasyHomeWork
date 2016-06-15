package com.example.zane.homework.utils;

import android.text.TextUtils;

/**
 * Created by Zane on 16/6/14.
 * Email: zanebot96@gmail.com
 */

public class JudgeSearch {

    public static boolean isRight(String s){
        if (s != null){
            if (TextUtils.isEmpty(s)){
                return false;
            }else if (s.contains(" ")){
                return false;
            }
            return true;
        }
        return false;
    }

}
