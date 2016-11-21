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
        final StringBuilder sb = new StringBuilder();
        days = String.valueOf(day);
        months = String.valueOf(month+1);
        years = String.valueOf(year);
        if (day<10){
            days = "0"+days;
        }
        if (month+1<10){
            months = "0"+months;
        }
        return sb.append(years).append("-").append(months).append("-").append(days).toString();
    }

}
