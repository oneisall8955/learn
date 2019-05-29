package com.oneisall.learn.java.advanced.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解测试类
 *
 * @author : oneisall
 * @version : v1 2019/5/28 09:43
 */
public class MainTest {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Class<People> peopleClass = People.class;
        /*
        一个类或者接口来说，Class 类中提供了以下一些方法用于反射注解。
        getAnnotation：返回指定的注解
        isAnnotationPresent：判定当前元素是否被指定注解修饰
        getAnnotations：返回所有的注解
        getDeclaredAnnotation：返回本元素的指定注解
        getDeclaredAnnotations：返回本元素的所有注解，不包含父类继承而来的
        * */
        Method getName = peopleClass.getMethod("getName");
        Custom annotation = getName.getAnnotation(Custom.class);
        System.out.println(annotation);

        Field name = peopleClass.getDeclaredField("name");
        annotation = name.getAnnotation(Custom.class);
        System.out.println(annotation);
        System.out.println(name.isAccessible());
        name.setAccessible(true);

        People people = new People();
        System.out.println(people.getName());
        System.out.println(people.getName());
        name.set(people,annotation.value());
        System.out.println(people.getName());
        System.out.println(people.getAddress());

        Field address = peopleClass.getDeclaredField("address");
        annotation = address.getAnnotation(Custom.class);
        System.out.println(annotation);
        System.out.println(address.isAccessible());
        address.setAccessible(true);
        System.out.println(people.getAddress());
        address.set(people,annotation.value());
        System.out.println(people.getAddress());

        Custom.class.getClasses();
    }
}
