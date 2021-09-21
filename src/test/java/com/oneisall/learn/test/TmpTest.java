package com.oneisall.learn.test;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzhicong
 **/
public class TmpTest {

    static class A{ String name;}
    static class B{ String name; }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<A> aList = new ArrayList<>();
        Method add = ArrayList.class.getMethod("add", Object.class);
        add.invoke(aList, new B());
        System.out.println(aList.size());
        echo(aList);
    }

    public static void echo(List<A> aList) {
        Object o = aList.get(0);
        if (o instanceof B){
            System.out.println(((B)o).name);
        }
        System.out.println(o);
    }

    @Test
    public void test01(){
        String s = Base64.getEncoder().encodeToString("{\"user_info\":{\"userId\":\"28d3e7a9ea75150c6ad03d95980cfd4a\"}}".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }
    public static final Pattern CHILD_ORDER_NO_INDEX_PATTERN = Pattern.compile("F(\\d+)$");

    @Test
    public void test02(){
        Matcher matcher = CHILD_ORDER_NO_INDEX_PATTERN.matcher("177156908288-F11");
        if (matcher.find()){
            String group = matcher.group(1);
            System.out.println(group);
        }
    }
}
