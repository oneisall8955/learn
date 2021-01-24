package com.oneisall.learn.acwing;

/**
 * @author liuzhicong
 **/
public class MergeSort787 {
    public static int[] tmp = new int[100010];

    public static void main(String[] args) {
        int[] q = new int[]{3, 5, 3, 9, 8};
        mergeSort(q, 0, q.length - 1);
        for (int value : q) {
            System.out.print(value + " ");
        }
    }

    /**
     * 第一步：找出mid
     * -------mid
     * ------- ↓
     * 1,5,7,3,9,10,5,2,4,8
     * l~~~~~~~~~~~~~~~~~~r
     * i~~~~~~~~ j(mid+1)
     * 第二步：递归排序左边和右边
     * ---left--|==right===
     * 第三步：合并
     * left合right分别排序好后如下
     * l
     * i-------mid
     * ↓-------↓
     * 1,3,5,7,9
     * 2,4,5,8,10
     * ↑=======↑
     * j=======r
     * ↑
     * mid+1
     */
    private static void mergeSort(int[] q, int l, int r) {
        if (l >= r) {
            return;
        }
        // 找出中点
        int mid = l + r >> 1;
        // 排序左和右
        mergeSort(q, l, mid);
        mergeSort(q, mid + 1, r);
        // 合并
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            // ↑：用<=是因为i从0开始遍历走到中点，j同理，从mid+1开始走到末尾为r
            if (q[i] <= q[j]) {
                // ↑： 用<=是因为稳定性
                tmp[k++] = q[i++];
            } else {
                tmp[k++] = q[j++];
            }
        }
        // 剩余的
        while (i <= mid) {
            // ↑：用<=是因为i从0开始遍历走到中点
            tmp[k++] = q[i++];
        }
        while (j <= r) {
            // ↑：j同理，从mid+1开始走到末尾为r
            tmp[k++] = q[j++];
        }

        // copy tmp到q
        for (i = l, j = 0; i <= r; i++, j++) {
            // ↑：i代表从原始数组的l开始到r，故i=l && i<=r
            //      ↑：j代表将临时数组从0开始复制，故j=0
            //                     每次加一个，两个指针自增
            q[i] = tmp[j];
        }
    }
}
