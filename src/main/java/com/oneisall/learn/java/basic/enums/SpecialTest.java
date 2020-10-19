package com.oneisall.learn.java.basic.enums;

import org.junit.Test;
import com.oneisall.learn.java.advanced.thread.ThreadPoolExecutorTest ;

/**
 * 枚举的特殊点!!!首先,枚举是特殊的类!!!
 * 成员可以重写枚举的方法,重写的成员将是枚举的子类!!!
 * jad 反编译如下:
 *
 * public class Color extends Enum
 * {
 *
 *     public static Color[] values()
 *     {
 *         return (Color[])$VALUES.clone();
 *     }
 *
 *     public static Color valueOf(String name)
 *     {
 *         return (Color)Enum.valueOf(com/oneisall/learn/java/basic/enums/Color, name);
 *     }
 *
 *     private Color(String s, int i)
 *     {
 *         super(s, i);
 *     }
 *
 *     public String mixture(String injection)
 *     {
 *         throw new AbstractMethodError();
 *     }
 *
 *
 *     public static final Color RED;
 *     public static final Color BLACK;
 *     private static final Color $VALUES[];
 *
 *     static
 *     {
 *         //注:RED使用了匿名内部类!!!
 *         RED = new Color("RED", 0) {
 *
 *             public String mixture(String injection)
 *             {
 *                 return (new StringBuilder()).append("\u7EA2\u8272+").append(injection).toString();
 *             }
 *
 *         }
 * ;
 *         BLACK = new Color("BLACK", 1);
 *         $VALUES = (new Color[] {
 *             RED, BLACK
 *         });
 *     }
 * }
 *
 * @author : oneisall
 * @version : v1 2019/6/28 16:02
 */
@SuppressWarnings("all")
public class SpecialTest {
    @Test
    public void test1() {
        Color color = Color.valueOf("BLACK");
        System.out.println(color);
        Color red = Color.RED;
        Color black = Color.BLACK;
        System.out.println(red.getClass().getName());
        System.out.println(black.getClass().getName());
        System.out.println(red.mixture("aa"));
        System.out.println(black.mixture("aa"));
    }

    class Foo {
        void hello() {
            System.out.println("Foo hello");
        }
    }


    /**
     * 匿名内部类
     */
    @Test
    public void test2() {
        Foo foo = new Foo();
        Foo bar = new Foo() {
            @Override
            void hello() {
                System.out.println("bar hello");
            }
        };
        bar.hello();
        System.out.println(foo.getClass().getName());
        System.out.println(bar.getClass().getName());
        System.out.println("opt import class test");
    }
}
