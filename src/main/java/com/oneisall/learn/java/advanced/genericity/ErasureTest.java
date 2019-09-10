package com.oneisall.learn.java.advanced.genericity;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 测试泛类类型擦除
 *
 * @author : oneisall
 * @version : v1 2019/9/10 11:54
 */
@SuppressWarnings("all")
public class ErasureTest {

    static class Foo {
        List<Integer> fooList;

        public List<Integer> getFooList() {
            return fooList;
        }

        public void setFooList(List<Integer> fooList) {
            this.fooList = fooList;
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        List<String> strList = Lists.newArrayList("aaa");
        List<Integer> intList = Lists.newArrayList(1);

        Foo foo = new Foo();

        foo.setFooList(intList);
        // compile error :
        // foo.setFooList(strList);

        Method[] methods = foo.getClass().getMethods();
        Method setFooListMethod = null;
        for (Method method : methods) {
            if ("setFooList".equals(method.getName())) {
                setFooListMethod = method;
            }
        }

        if (setFooListMethod == null) {
            return;
        }
        List<Integer> fooList;

        fooList = foo.getFooList();
        System.out.println(fooList);
        System.out.println(fooList.getClass());

        // 反射设置List<String> 类型
        setFooListMethod.invoke(foo, strList);
        fooList = foo.getFooList();
        System.out.println(fooList);
        System.out.println(fooList.getClass());

        // 使用fooList内的元素,运行时类型转化异常
        try {
            Integer element = foo.getFooList().get(0);
            System.out.println(element);
        } catch (Exception e) {
            System.out.println("===> " + e.getMessage() + " <===");
        }

        // 使用Object接收,检查实际类型
        Object element = foo.getFooList().get(0);
        if (element instanceof String) {
            System.out.println("String");
            String str = (String) element;

            // 愉快地使用实际类型地API
            String aaa = str.toUpperCase();
            // AAA
            System.out.println(aaa);
        } else {
            System.out.println("Not String");
        }
    }
}
