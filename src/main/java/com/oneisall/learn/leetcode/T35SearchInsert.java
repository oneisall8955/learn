package com.oneisall.learn.leetcode;

/**
 * 二分算法，搜索插入位置
 *
 * @author liuzhicong
 **/
public class T35SearchInsert {

    private static int searchFirst(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            // target 5
            //               mid
            //                ↓
            // 1, 2, 3, 4, 5, 5, 5, 6, 6, 9, 10
            //
            // [满足check(mid) ]
            // [l          mid] -> l=mid
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 此处找到的l满足大于等于target
        return l;
    }

    public static int searchInsert(int[] nums, int target) {
        int first = searchFirst(nums, target);
        if (nums[first] >= target) {
            // 大于等于直接返回
            return first;
        } else {
            // 插入值比所有的都大，此时first=nums.length-1，返回first+1
            return first + 1;
        }
    }
    public static void main(String[] args) {
        //                     0  1  2  3  4  5  6  7  8  9  10
        int[] nums = new int[]{1, 2, 3, 4, 5, 5, 5, 6, 6, 9, 10};
        System.out.println(searchInsert(nums, 0));  // 0   0
        System.out.println(searchInsert(nums, 1));  // 0   0
        System.out.println(searchInsert(nums, 2));  // 1   1
        System.out.println(searchInsert(nums, 5));  // 4   4
        System.out.println(searchInsert(nums, 7));  // 9   9
        System.out.println(searchInsert(nums, 10)); // 10  10
        System.out.println(searchInsert(nums, 12)); // 10  11
        System.out.println(searchInsert(nums, 50)); // 10  11
    }
}
