package com.oneisall.learn.acwing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author liuzhicong
 **/
public class MergeSort788 {

    private static int N = 100010;
    public static int[] tmp = new int[N];
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] q = new int[n];
        String[] arrStr = reader.readLine().split(" ");
        int length = arrStr.length;
        for (int i = 0; i < length; i++) {
            q[i] = Integer.parseInt(arrStr[i]);
        }
        reader.close();
        long value = mergeSort(q, 0, q.length - 1);
        System.out.println(value);
    }

    @SuppressWarnings("DuplicatedCode")
    private static long mergeSort(int[] q, int l, int r) {
        if (l >= r) return 0;
        // mid
        int mid = l + r >> 1;
        long lr = mergeSort(q, l, mid);
        long rr = mergeSort(q, mid + 1, r);
        int k = 0, i = l, j = mid + 1;
        long sj = 0;
        while (i <= mid && j <= r) {
            if (q[i] <= q[j]) {
                tmp[k++] = q[i++];
            } else {
                tmp[k++] = q[j++];
                // -,3,5
                //   i
                // 0 1 2        2=mid-i+1
                // j
                // 2,4,6
                sj = (sj + (mid - i + 1));
            }
        }
        while (i <= mid) {
            tmp[k++] = q[i++];
        }
        while (j <= r) {
            tmp[k++] = q[j++];
        }
        for (i = l, j = 0; i <= r; i++, j++) {
            q[i] = tmp[j];
        }
        return lr + rr + sj;
    }
}
