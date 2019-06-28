package com.oneisall.learn.java.basic.enums;

import org.junit.Test;

/**
 * 枚举的特殊点!!!首先,枚举是特殊的类!!!
 * 成员可以重写枚举的方法,重写的成员将是枚举的子类!!!
 *
 * @author : oneisall
 * @version : v1 2019/6/28 16:02
 */
public class SpecialTest {
    public enum Color {
        RED {
            @Override
            public String mixture(String injection) {
                return "红色+" + injection;
            }
        },
        BLACK;

        public String mixture(String injection) {
            throw new AbstractMethodError();
        }
    }

    @Test
    public void test25() {
        Color red = Color.RED;
        System.out.println(red.mixture("aa"));
        Color black = Color.BLACK;
        System.out.println(black.mixture("aa"));
    }
}
