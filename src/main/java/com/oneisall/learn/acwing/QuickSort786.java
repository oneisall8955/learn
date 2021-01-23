package com.oneisall.learn.acwing;

import java.util.Scanner;

/**
 * 快排找出第k小的数
 *
 * @author : oneisall
 * @version : v1 2021/1/23 18:38
 */
public class QuickSort786 {

    public static void main(String[] args) {
        // 对输入数据进行初始化
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();
        // 求解的第 k小的数, 下标为 k -1
        System.out.println(quickSort(arr, 0, n - 1, k));
    }

    /**
     * 3 5 3 9 8   // x=3
     * ↑       ↑
     * i       j
     * -------------------
     * q[i]<3 q[j]>3
     * --------------------
     * if sizeLeft >= k
     * quickSort Left(l,j,k)
     * else
     * quickSort Right(j+1,r,k-sizeLeft)
     * -------------------------
     * 排右边的参数为k-sizeLeft，原因是左边已经不满足，在右边还需要k-sizeLeft个数就是答案
     */
    @SuppressWarnings("all")
    private static int quickSort(int[] q, int l, int r, int k) {
        if (l >= r) {
            return q[l];
        }
        int x = q[l], i = l - 1, j = r + 1;
        while (i < j) {
            while (q[++i] < x) { }
            while (q[--j] > x) { }
            if (i < j) {
                swap(q, i, j);
            }
        }
        int sizeLeft = j - l + 1;
        if (sizeLeft >= k) {
            return quickSort(q, l, j, k);
        } else {
            return quickSort(q, j + 1, r, k - sizeLeft);
        }
    }

    public static void swap(int[] q, int a, int b) {
        int t = q[a];
        q[a] = q[b];
        q[b] = t;
    }
}
