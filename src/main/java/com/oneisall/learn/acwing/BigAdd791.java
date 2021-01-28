package com.oneisall.learn.acwing;

import java.util.ArrayList;
import java.util.List;

/**
 * 高精度加法
 *
 * @author : oneisall
 * @version : v1 2021/1/23 18:38
 */
public class BigAdd791 {
    public static void main(String[] args) {
        String a = "15";
        String b = "97";
        List<Integer> aList = toList(a);
        List<Integer> bList = toList(b);
        List<Integer> result = bigAdd(aList, bList);
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i));
        }
    }

    private static List<Integer> bigAdd(List<Integer> aList, List<Integer> bList) {
        ArrayList<Integer> result = new ArrayList<>(Math.max(aList.size(), bList.size()) + 1);
        int aLimit = aList.size() - 1;
        int bLimit = bList.size() - 1;
        int t = 0;
        for (int i = 0; i <= aLimit || i <= bLimit; i++) {
            if (i <= aLimit) {
                t += aList.get(i);
            }
            if (i <= bLimit) {
                t += bList.get(i);
            }
            result.add(t % 10);
            t = t / 10;
        }
        if (t > 0) {
            result.add(t);
        }
        return result;
    }

    private static List<Integer> toList(String num) {
        List<Integer> list = new ArrayList<>(num.length());
        for (int i = num.length() - 1; i >= 0; i--) {
            list.add(num.charAt(i) - '0');
        }
        return list;
    }

}
