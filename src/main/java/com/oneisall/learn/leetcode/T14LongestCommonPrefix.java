package com.oneisall.learn.leetcode;

/**
 * 最长公共前缀
 * 1.先读取第一个字符串first为模板，默认其为最长对的公共前缀。其下标为 max=first.length
 * 2.第二个字符串长度curr.length-1与max对比，两者最小为最新的max
 * 3.从0开始到max遍历第一个字符串和第二个字符串，如果不相等则更新max的值
 * 4.重复2~3步骤，将第二个字符串改为第三个...最后一个字符串
 * 5.最后的max值是公共前缀的下表
 *
 * @author : oneisall
 * @version : v1 2020/11/9 00:10
 */
@SuppressWarnings("all")
public class T14LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String first = strs[0];
        if (strs.length == 1) {
            return first;
        }
        int max = first.length() - 1;
        for (int i = 1; i < strs.length; i++) {
            String curr = strs[i];
            // limit other
            max = Math.min(max, curr.length() - 1);
            for (int j = 0; j <= max; j++) {
                if (curr.charAt(j) != first.charAt(j)) {
                    if (j == 0) {
                        // other's first char diff
                        return "";
                    }
                    // flo - fli -> o!=i , max = j -1
                    max = j - 1;
                }
            }
        }
        return first.substring(0, max + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}
