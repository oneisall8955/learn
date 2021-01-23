package com.oneisall.learn.leetcode;

/**
 * nums = [3,2,2,3], val = 3,
 * //函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * @author : liuzhicong
 * @version : v1 2020/11/9
 */
@SuppressWarnings("all")
public class T27RemoveElement {

    public int removeDuplicates(int[] nums) {
        int leftIndex = 0;
        int remainLength = nums.length;
        for (; leftIndex < remainLength; leftIndex++) {
            int left = nums[leftIndex];
            int rightIndex = leftIndex + 1;
            if (rightIndex == remainLength) {
                return remainLength;
            }
            while (rightIndex <= remainLength - 1) {
                int right = nums[rightIndex];
                if (right == left) {
                    rightIndex++;
                } else {
                    rightIndex--;
                    break;
                }
            }
            if (rightIndex > leftIndex) {
                merge(nums, leftIndex, rightIndex);
                remainLength = remainLength - (rightIndex - leftIndex);
            }
        }
        return remainLength;
    }
    private void merge(int[] source, int leftIndex, int rightIndex) {
        int leftOffset = leftIndex + 1;
        int rightOffset = rightIndex + 1;
        int limit = source.length - 1;
        while (leftOffset <= limit) {
            if (rightOffset <= limit) {
                source[leftOffset] = source[rightOffset];
            } else {
                source[leftOffset] = -1;
            }
            leftOffset++;
            rightOffset++;
        }
    }
}
