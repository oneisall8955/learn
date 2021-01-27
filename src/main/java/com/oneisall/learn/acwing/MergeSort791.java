package com.oneisall.learn.acwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author liuzhicong
 **/
public class MergeSort791 {

    public static void main(String[] args) {
        int[] q = new int[]{1, 2, 3, 6, 6, 7, 9, 10, 11, 12};
        int[] ans = new int[2];
        int target = 6;
        int first = binarySearchFirst(q, target);
        // check 是 >= target
        System.out.println("查找出来的要么是" + target + "要么比" + target + "大：" + q[first]);
        first = (q[first] == target ? first : -1);
        if (first == -1) {
            ans[0] = -1;
            ans[1] = -1;
        } else {
            ans[0] = first;
            ans[1] = binarySearchLast(q, target);
        }
        System.out.println(ans[0] + " " + ans[1]);

        // 例外
        target = 10000;
        int last = binarySearchLast(q, target);
        // check 是 <= target
        System.out.println("查找出来的要么是" + target + "要么比" + target + "小：" + q[last]);
    }

    // 搜索

    private static int binarySearchFirst(int[] q, int target) {
        //          ※第一个6
        // 1, 2, 3, 6, 6, 6, 7, 9, 10, 11, 12
        //             ↑
        //            mid
        //         设定 check 方法为 q[mid] >= 6
        //         满足check时候，第一个在[l,mid]-> r=mid
        //         不满足check，则第一个在[mid+1,r]-> l=mid+1

        int l = 0, r = q.length - 1;
        while (l < r) { // 不用等于，当不等于，直接为l。看q[l]是不是target就行了，也不能等于，当l=mid+1等于r，死循环
            int mid = l + r >> 1;
            if (q[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
            System.out.println("l:"+l+",r:"+r+",mid:"+mid);
        }
        return l;
    }

    private static int binarySearchLast(int[] q, int target) {
        //                ※最后一个6
        // 1, 2, 3, 6, 6, 6, 7, 9, 10, 11, 12
        //             ↑
        //            mid
        //         设定 check 方法为 q[mid] <= 6
        //         满足check时候，第一个在[mid,r]-> l=mid
        //         不满足check，则第一个在[l,mid-1]-> r=mid-1

        int l = 0, r = q.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
            System.out.println("l:"+l+",r:"+r+",mid:"+mid);
        }
        return r;
    }

    private static int[][] findK(int[] arr, int[] ks) {
        HashMap<Integer, int[]> map = new HashMap<>((int) ((float) ks.length / 0.75F + 1.0F));
        for (int k : ks) {
            map.put(k, new int[]{-1, -1});
        }
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            int[] ints = map.get(v);
            if (ints != null) {
                if (ints[0] == -1) {
                    ints[0] = i;
                }
                ints[1] = i;
            }
        }
        int[][] result = new int[ks.length][2];
        for (int i = 0; i < ks.length; i++) {
            result[i] = map.get(ks[i]);
        }
        return result;
    }

}
