package com.oneisall.learn.leetcode;

/**
 * @author liuzhicong
 **/
@SuppressWarnings("all")
public class T14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strList) {
        int i = 0;
        for (; ; ) {
            char pre = '\0';
            char cur = '\0';
            boolean shortestReached = false;
            boolean diff = false;
            for (int index = 0, length = strList.length; index < length; index++) {
                if (index > length - 1) {
                    shortestReached = true;
                    break;
                }
                String s = strList[index];
                cur = s.charAt(i);
                if (index == 0) {
                    pre = cur;
                    continue;
                }
                if (pre != cur) {
                    diff = true;
                    break;
                }
                pre = cur;
            }
            if (diff || shortestReached) {
                break;
            }
            i++;
        }
        return strList[0].substring(0, i);
    }

    public static void main(String[] args) {
        String result = new T14LongestCommonPrefix().longestCommonPrefix(new String[]{"dog","racecar","car"});
        System.out.println(result);
    }
}
