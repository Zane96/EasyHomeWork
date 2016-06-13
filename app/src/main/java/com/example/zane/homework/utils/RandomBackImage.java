package com.example.zane.homework.utils;

import com.example.zane.homework.R;

import java.util.Random;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class RandomBackImage {

    private static final Random RANDOM = new Random();

    public static int getRandomAvatar() {
        switch (RANDOM.nextInt(4)) {
            default:
            case 0:
                return R.drawable.back1_sq;
            case 1:
                return R.drawable.back2_sq;
            case 2:
                return R.drawable.back3_sq;
            case 3:
                return R.drawable.back4_sq;
        }
    }

    public static int getBackground(int id){
        int avatar = R.drawable.back1_re;
        switch (id) {
            case R.drawable.back1_sq:
                avatar = R.drawable.back1_re;
                break;
            case R.drawable.back2_sq:
                avatar = R.drawable.back2_re;
                break;
            case R.drawable.back3_sq:
                avatar = R.drawable.back3_re;
                break;
            case R.drawable.back4_sq:
                avatar = R.drawable.back4_re;
                break;
        }
        return avatar;
    }
}
