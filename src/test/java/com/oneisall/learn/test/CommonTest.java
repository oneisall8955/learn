package com.oneisall.learn.test;

import org.apache.commons.lang3.StringUtils;

/**
 * 随便测试
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:55
 */
public class CommonTest {
    public static void main(String[] args) {
        String aaa = "aAa";
        String capitalize = StringUtils.capitalize(StringUtils.lowerCase(aaa));
        System.out.println(capitalize);
    }
    
    // 这一行是来自 Git 仓库 啊，模拟别人提交，产生冲突
}
