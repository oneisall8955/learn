package com.oneisall.learn.leetcode;

import java.util.Stack;

/**
 * //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @author : liuzhicong
 * @version : v1 2020/11/9
 */
@SuppressWarnings("all")
public class T20IsValid {

    public static boolean isValid(String s) {
        Stack<Character> source = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if ('(' == c || '{' == c || '[' == c) {
                // 左边入栈
                source.push(c);
            } else if (')' == c || '}' == c || ']' == c) {
                // 右边出栈
                if (source.isEmpty()) {
                    // 已经空了，右边比左边多
                    return false;
                }
                Character popChar = source.pop();
                if ((c == ')' && popChar == '(')
                        || (c == '}' && popChar == '{')
                        || (c == ']' && popChar == '[')) {
                    continue;
                } else {
                    return false;
                }
            } else {
                // 不是括号
                return false;
            }
        }
        // 判断左边比右边多
        return source.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("({})"));
    }
}
