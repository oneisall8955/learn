package com.oneisall.learn.java.utils;


import com.oneisall.learn.java.common.EnumCode;
import com.oneisall.learn.java.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 通用匹配工具,stream流匹配API增强版,用于需要知道哪个元素导致失败或成功!
 *
 * @author : oneisall
 * @version : v1 2019/6/2 15:57
 * @see Stream
 */
public class CommonMatchUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonMatchUtil.class);

    /**
     * 重写stream类中的anyMatch,
     * 有一个匹配成功则返回成功,且包含导致成功的元素,
     * 否则返回失败,提示没有一个元素匹配
     *
     * @param collection 匹配集合
     * @param predicate  条件
     * @param <T>        数据类型
     * @return 是否匹配成功
     * @see Stream#anyMatch(Predicate)
     */
    public static <T> Result<T> anyMatch(Iterable<T> collection, Predicate<T> predicate) {
        for (T t : collection) {
            if (predicate.test(t)) {
                return Result.success("匹配到符合的第一个元素!", t);
            }
        }
        return Result.failed("没有匹配到任何一个元素!");
    }

    /**
     * 重写stream类中的anyMatch,
     * 有一个匹配成功则返回成功,且包含这个成功的元素,
     * 否则返回失败,提示没有一个元素匹配
     *
     * @param collection 匹配集合
     * @param predicate  条件
     * @param <T>        数据类型
     * @return 是否匹配成功
     * @see Stream#anyMatch(Predicate)
     */
    @SafeVarargs
    public static <T> Result<T> anyMatch(Predicate<T> predicate, T... collection) {
        for (T t : collection) {
            if (predicate.test(t)) {
                return Result.success("匹配到符合的第一个元素!", t);
            }
        }
        return Result.failed("没有匹配到任何一个元素!");
    }

    /**
     * 重写stream类中的allMatch,
     * 必须所有元素都要匹配成功才返回成功,提示所有元素均匹配,
     * 否则返回失败,且包含导致失败的元素
     *
     * @param collection 匹配集合
     * @param predicate  条件
     * @param <T>        数据类型
     * @return 是否匹配成功
     * @see Stream#allMatch(Predicate)
     */
    public static <T> Result<T> allMatch(Iterable<T> collection, Predicate<T> predicate) {
        for (T t : collection) {
            if (!predicate.test(t)) {
                return Result.failed("存在一个元素不匹配!", t);
            }
        }
        return Result.success("所有元素均匹配!");
    }

    /**
     * 重写stream类中的noneMatch,
     * 必须所有元素都要不匹配成功才返回成功,提示所有元素均不匹配,
     * 否则返回失败,且包含导致失败的元素
     *
     * @param collection 匹配集合
     * @param predicate  条件
     * @param <T>        数据类型
     * @return 是否匹配成功
     * @see Stream#allMatch(Predicate)
     */
    public static <T> Result<T> noneMatch(Iterable<T> collection, Predicate<T> predicate) {
        for (T t : collection) {
            if (predicate.test(t)) {
                return Result.failed("存在一个元素匹配!", t);
            }
        }
        return Result.success("所有元素均不匹配!");
    }

    private static Map<Class<?>, Map<Integer, Result<?>>> enumCacheMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends EnumCode>> E findByCode(Class<E> enumCodeClass, int code) {
        Map<Integer, Result<?>> forThisEnumClass = enumCacheMap.computeIfAbsent(enumCodeClass, k -> new ConcurrentHashMap<>());
        Result<?> cacheResult = forThisEnumClass.get(code);
        if (cacheResult != null) {
            Object cacheData = cacheResult.getData();
            if (cacheData == null) {
                logger.warn("获取枚举出错，原因：{}", cacheResult.getMsg());
            }
            return (E) cacheData;
        }
        try {
            E[] enumConstants = enumCodeClass.getEnumConstants();
            EnumCode[] enumValues = (EnumCode[]) enumConstants;
            EnumCode data = CommonMatchUtil.anyMatch(item -> code == item.getCode(), enumValues).getData();
            if (data != null) {
                forThisEnumClass.put(code, Result.success(data));
            } else {
                forThisEnumClass.put(code, Result.failed("枚举类 " + enumCodeClass.getSimpleName() + " 中没有 code 为 " + code + " 的枚举实例"));
            }
            return (E) data;
        } catch (Exception e) {
            logger.error("获取枚举出错，原因：{}", e.getMessage());
            forThisEnumClass
                    .put(code,
                            Result.failed("枚举类 " + enumCodeClass.getSimpleName() + " 中查找 code 为 " + code + " 时候发生异常，异常信息：" + e.getMessage()));
            return null;
        }
    }
}
