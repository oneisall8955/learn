package com.oneisall.learn.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;

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

    @Test
    public void test00(){
        Duration duration = Duration.ZERO;
        System.out.println(duration);
        Duration between = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        System.out.println(between);
        between = Duration.between(LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        System.out.println(between);
        Duration parse = Duration.parse("PT-24H");
        System.out.println(parse);
    }

    @Test
    public void test01(){
        Currency cny = Currency.getInstance("ABC");
        System.out.println(cny);
    }
}
