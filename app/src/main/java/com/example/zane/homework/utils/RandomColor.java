package com.example.zane.homework.utils;

import com.example.zane.homework.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zane on 16/6/8.
 * Email: zanebot96@gmail.com
 */

public class RandomColor {
    private static List<Integer> Colors = new ArrayList<>();

    private static final Random RANDOM = new Random();

    public static int getRandomColor() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.color.class1;
            case 1:
                return R.color.class2;
            case 2:
                return R.color.class3;
            case 3:
                return R.color.class4;
            case 4:
                return R.color.class5;
        }
    }
    public static List<Integer> getAllColor(List<Integer> nullPosition){
        boolean isSame = false;
        for (int i = 0; i < 42; i++){
            for (int j = 0; j < nullPosition.size(); j++){
                if (i == nullPosition.get(j)){
                    isSame = true;
                    Colors.add(R.color.white);
                    break;
                }
            }
            if (!isSame){
                Colors.add(getRandomColor());
            }
            isSame = false;
        }

        return Colors;
    }
}
