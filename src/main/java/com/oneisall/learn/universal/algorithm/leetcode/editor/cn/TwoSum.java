//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表

package com.oneisall.learn.universal.algorithm.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // 两次遍历暴力求出
            /*for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{-1, -1};*/
            // 求补数
            int length = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>((int) ((float) length / 0.75F + 1.0F));
            for (int i = 0; i < length; i++) {
                int num = nums[i];
                int complement = target - num;
                if (map.containsKey(complement)) {
                    // [2, 7, 11, 15] 9
                    // 第1元素2坐标 0 第2元素7坐标1 结果应该是[0,1]
                    // 遍历到元素7，坐标是1，字典中找到互补的2坐标是0，这时候，应该按照最早的入字典顺序作为结果，所以，互补的肯定在前面
                    return new int[]{map.get(complement), i};
                } else {
                    map.put(num, i);
                }
            }
            return new int[]{-1, -1};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
