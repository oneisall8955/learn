package com.oneisall.learn.java.utils;

/**
 * 增强自定义String工具类
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:50
 */
public class StringImproveUtil {

    /**
     * 首字母转大写
     */
    public static String firstWorldToUpperCase(String name) {
        char[] cs = name.toCharArray();
        if (Character.isLowerCase(cs[0])) {
            cs[0] -= 32;
            return String.valueOf(cs);
        }
        return name;
    }
}
