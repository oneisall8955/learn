package com.oneisall.learn.universal.design.pattern.strategy.common;

/**
 * 断言
 *
 * @param <T>
 * @author oneisall
 */
@FunctionalInterface
public interface Predicate<T> {

    /**
     * 断言
     *
     * @param t 断言对象
     * @return 判断结果
     */
    boolean test(T t);
}
