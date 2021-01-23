package com.oneisall.learn.acwing;

/**
 * 快排模板
 *
 * @author : oneisall
 * @version : v1 2021/1/23 18:38
 */
public class QuickSort785 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 3, 9, 8};
        int num = arr.length;

        quickSort(arr, 0, num - 1);

        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    /**
     * 3 5 3 9 8   // x=3
     * ↑       ↑
     * i       j
     * -------------------
     * q[i]<3 q[j]>3
     */
    private static void quickSort(int[] q, int l, int r) {
        if (l >= r) {
            return;
        }
        // 第1步，找x，注意定义为两侧
        int x = q[l], i = l - 1, j = r + 1;
        while (i < j) {
            // 第2步，以x为标准分隔，走左右两边
            do {
                i++;
            } while (q[i] < x);
            do {
                j--;
            } while (q[j] > x);
            // 第3步，两个指针还没有相遇，交换
            if (i < j) {
                int tmp = q[i];
                q[i] = q[j];
                q[j] = tmp;
            }
        }
        // 第四步，递归排左和右
        quickSort(q, l, j);
        quickSort(q, j + 1, r);
    }
}
