package com.oneisall.learn.leetcode;

/**
 * @author liuzhicong
 **/
public class T58LengthOfLastWord {
    static class Solution {
        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int length = s.length();
            int lastIndex = 0;
            for (int i = length - 1; i >= 0; i--) {
                char aChar = s.charAt(i);
                if (aChar != ' ') {
                    lastIndex = i;
                    break;
                }
            }
            if (lastIndex == 0) {
                return 1;
            }
            int size = 0;
            for (int i = lastIndex; i >= 0; i--) {
                char aChar = s.charAt(i);
                if (aChar == ' ') {
                    // _123___
                    return size;
                }
                // 123__
                if (i == 0) {
                    return ++size;
                }
                size++;
            }
            return size;
        }
    }

    public static void main(String[] args) {
        int length = new Solution().lengthOfLastWord("a");
        System.out.println(length);
    }
}
