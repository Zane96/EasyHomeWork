package com.example.zane.homework.utils;

/**
 * Created by Zane on 16/6/12.
 * Email: zanebot96@gmail.com
 */

public class TimeUtils {
    /**
     * 根据传入的日期生成日期组
     * @param year  2015
     * @param month 1
     * @param day   2
     * @return 20150102
     */
    public static String DateFormat(int year,int month,int day){
        String days,months,years;
        days = ""+day;
        months = ""+(month+1);
        years = ""+year;
        if (day<10){
            days = "0"+days;
        }
        if (month+1<10){
            months = "0"+months;
        }
        return years+"年"+months+"月"+days+"日";
    }

}
