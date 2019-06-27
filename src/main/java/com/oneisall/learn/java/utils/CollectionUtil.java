package com.oneisall.learn.java.utils;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * 集合工具
 *
 * @author oneisall
 */
public class CollectionUtil {


    /**
     * 判定map为空
     *
     * @param map 需要判断的map
     * @return boolean
     * @author oneisall
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判定map不为空
     *
     * @param map 需要判断的map
     * @return boolean
     * @author oneisall
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 判定collection为空
     *
     * @param collection 需要判断的collection
     * @return boolean
     * @author oneisall
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判定collection不为空
     *
     * @param collection 需要判断的collection
     * @return boolean
     * @author oneisall
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }


    /**
     * 模拟Steam的reduce API
     *
     * @param collection  目标集合
     * @param accumulator 累计的操作
     * @param <T>         类型
     * @return 返回结果, 当为空集合的时候, 返回 {@code Optional.empty}
     */
    @SuppressWarnings("all")
    public static <T> Optional<T> reduce(Collection<T> collection, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        if (collection.isEmpty()) {
            return Optional.empty();
        }
        T firstItem = null;
        //可以使用findFirst代替,但是为了更加清楚怎么运行,则用这个代替
        //firstItem = collection.stream().findFirst().orElse(null);
        for (T item : collection) {
            firstItem = item;
            break;
        }
        if (firstItem == null) {
            throw new NoSuchElementException("CAN NOT FIND FIRST ELEMENT IN COLLECTION FOR ACCUMULATOR !");
        }
        //遍历计算
        T result = firstItem;
        int i = 0;
        for (T item : collection) {
            i++;
            if (i == 1) {
                continue;
            }
            result = accumulator.apply(result, item);
        }
        return Optional.ofNullable(result);
    }

    /**
     * 模拟Steam的reduce API,提供默认值
     *
     * @param collection  目标集合
     * @param accumulator 累计的操作
     * @param orElseValue 累计为NULL时候的默认值
     * @param <T>         类型
     * @return 返回计算结果或orElseValue值
     * @see CollectionUtil#reduce(java.util.Collection, java.util.function.BinaryOperator)
     */

    public static <T> T reduce(Collection<T> collection, BinaryOperator<T> accumulator, T orElseValue) {
        return reduce(collection, accumulator).orElse(orElseValue);
    }
}
