package com.oneisall.learn.leetcode;

/**
 * nums = [1,3,2,2,3,4,5], val = 3,
 * //函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * @author : liuzhicong
 * @version : v1 2020/11/9
 */
@SuppressWarnings("all")
public class T27RemoveElement {

    public int removeElement(int[] nums, int val) {
        int p = 0;
        int q = nums.length - 1;
        if (p == q) {
            if (nums[p] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        while (p < q) {
            int num = nums[p];
            if (num == val) {
                // 从尾部逆向找不是val的位置
                while (p < q) {
                    if (nums[q] != val) {
                        break;
                    }
                    q--;
                }
                if (p >= q) {
                    // 相碰了，已经移动完成
                    break;
                }
                nums[p] = nums[q];
                nums[q] = num;
            }
            p++;
        }
        return p;
    }

    public static void main(String[] args) {
        int[] nums = {3,3};
        int i = new T27RemoveElement().removeElement(nums, 5);
        for (int j = 0; j < i; j++) {
            System.out.println(nums[j]);
        }
    }


}
