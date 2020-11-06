package com.oneisall.learn.leetcode;

/**
 * target=1221
 * 第一步，找到最大的除数div=1000（重复除以10可得到）
 * 第二步，target除以div得到首位1，target除以10得到尾数1，两者对比，不相同直接返回false
 * 第三步，target对div取余再除以10，得到22，div除以100得到新的div
 * 重复第二第三步，当target<=0时，跳出循环
 *
 * @author liuzhicong
 **/
public class T9IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        // 101
        while (x / div >= 10) {
            // 101 / 1 = 101 -> 10
            // 101 / 10 = 10 -> 100
            // 101 / 100 = 1 -> STOP

            // 12
            div = (div * 10);
        }
        while (x > 0) {
            // 3210123
            // 1000000
            // 3
            int headRemainder = x / div;
            // 3
            int tailRemainder = x % 10;
            if (headRemainder != tailRemainder) {
                return false;
            }
            // 3210123->21012
            // x=(3210123%1000000)/10=210123/10=21012
            x = (x % div) / 10;
            div = (div / 100);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new T9IsPalindrome().isPalindrome(101));
    }
}
