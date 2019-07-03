package com.oneisall.learn.java.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 增强自定义String工具类
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:50
 */
public class StringImproveUtil {
    /**
     * 首字母大写,其余小写的字符串
     *
     * @param str 原始字符串
     * @return 首字母大写, 其余小写的字符串
     */
    public static String capitalizeTheFirstChar(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        } else {
            return str;
        }
    }
}
