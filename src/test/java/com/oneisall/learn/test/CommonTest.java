package com.oneisall.learn.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test01() {

        Integer a = 1;

        List<Number> numbers = new ArrayList<>();
        numbers.add(a);
        System.out.println(numbers);

        List<? super Number> superNumbers = new ArrayList<>();
        superNumbers.add(a);
        System.out.println(superNumbers);

        List<? extends Number> extendsNumbers = new ArrayList<>();
        extendsNumbers.add(a);
        System.out.println(extendsNumbers);
    }

    public void supperTest(){

    }

    public void extendsTest(){

    }
}
