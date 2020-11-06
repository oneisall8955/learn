package com.oneisall.learn.leetcode;

import java.util.HashMap;

/**
 * @author liuzhicong
 **/
public class T1TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // number,index
        HashMap<Integer, Integer> cache = new HashMap<>((int) ((float) nums.length / 0.75F + 1.0F));
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int key = target - cur;
            if (cache.containsKey(key)) {
                return new int[]{cache.get(key), i};
            } else {
                cache.put(cur, i);
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] result = new T1TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}
