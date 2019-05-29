package com.oneisall.learn.java.advanced.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解,字符串属性设置默认值
 * //@Target：注解的作用目标
 * //@Retention：注解的生命周期
 * //@Documented：注解是否应当被包含在 JavaDoc 文档中
 * //@Inherited：是否允许子类继承该注解
 *
 * @author : oneisall
 * @version : v1 2019/5/28 09:10
 */
/*
ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
ElementType.FIELD：允许作用在属性字段上
ElementType.METHOD：允许作用在方法上
ElementType.PARAMETER：允许作用在方法参数上
ElementType.CONSTRUCTOR：允许作用在构造器上
ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
ElementType.ANNOTATION_TYPE：允许作用在注解上
ElementType.PACKAGE：允许作用在包上
* */
@Target(value = {ElementType.FIELD})
/*
RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件
RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件
RetentionPolicy.RUNTIME：永久保存，可以反射获取
注解指定了被修饰的注解的生命周期，
一种是只能在编译期可见，编译后会被丢弃，
一种会被编译器编译进 class 文件中，
无论是类或是方法，乃至字段，他们都是有属性表的，
而 JAVA 虚拟机也定义了几种注解属性表用于存储注解信息，
但是这种可见性不能带到方法区，类加载时会予以丢弃，
最后一种则是永久存在的可见性
**/
@Retention(RetentionPolicy.RUNTIME)
/*
 注解修饰的注解，当执行 JavaDoc 文档打包时会被保存进 doc 文档，反之将在打包时丢弃
* */
@Documented
/*
注解修饰的注解是具有可继承性的.注解修饰了一个类，而该类的子类将自动继承父类的该注解
* */
@Inherited()
public @interface Custom {
    String value() default "我是默认的值啦";
}
