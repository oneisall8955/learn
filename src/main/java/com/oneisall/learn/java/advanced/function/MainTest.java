package com.oneisall.learn.java.advanced.function;

import com.oneisall.learn.java.common.Result;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数学习
 * Consumer         :Consumer<T>    接收T对象，不返回值
 * Predicate        :Predicate<T>   接收T对象并返回boolean
 * Function         :Function<T,R>  接收T对象，返回R对象
 * Supplier         :Supplier<T>    提供T对象（例如工厂），不接收值
 * UnaryOperator    :UnaryOperator  接收T对象，返回T对象
 * BinaryOperator   :BinaryOperator 接收两个T对象，返回T对象
 *
 * @author : oneisall
 * @version : v1 2019/6/18 11:00
 */
@SuppressWarnings("all")
public class MainTest {

    /**
     * Consumer
     * accept(T t) 接口,定义一个消费的逻辑
     * 调用accept(t) 进行消费
     * andThen(Consumer after) 接口,original消费者接受一个after消费者,返回一个新的andThen消费者
     * 新的andThen消费者的accept(t)逻辑是:先进行original消费后,再进行after的消费
     */
    @Test
    public void ConsumerTest() {
        Consumer<String> original = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("original consumer accept:" + s);
            }
        };
        original.accept("1");
        System.out.println("--------------");
        Consumer<String> after = s -> {
            System.out.println("after consumer accept:" + s);
        };
        Consumer<String> andThen = original.andThen(after);
        andThen.accept("2");
    }

    /**
     * 拓展的consumer
     *
     * @param <T>
     */
    interface MyComsumer<T> extends Consumer<T> {
        default Consumer<T> compose(Consumer<? super T> before) {
            Objects.requireNonNull(before);
            return (T t) -> {
                before.accept(t);
                accept(t);
            };
        }
    }

    @Test
    public void ConsumerTest2() {
        MyComsumer<String> original = new MyComsumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("original MyComsumer accept:" + s);
            }
        };
        original.accept("1");
        System.out.println("--------------");
        Consumer<String> before = s -> {
            System.out.println("before MyComsumer accept:" + s);
        };
        Consumer<String> compose = original.compose(before);
        compose.accept("2");
    }

    /**
     * Predicate
     * test(T t) 接口,定义一个判断的逻辑断言,返回断言判断逻辑结果
     * 调用test(t) 进行判断
     * <p>
     * or(Predicate other) 接口,original断言接受一个other断言,返回一个新的orPredicate判断断言
     * 新的orPredicate的test(t)逻辑是:original.test(t)||other.test(t)
     * <p>
     * and(Predicate other) 接口,original断言接受一个other断言,返回一个新的andPredicate判断断言
     * 新的andPredicate的test(t)逻辑是:original.test(t)&&other.test(t)
     */
    @Test
    public void PredicateTest() {
        Predicate<Integer> original = (integer) -> {
            return integer != null && integer > 1;
        };
        //false
        boolean test = original.test(0);
        System.out.println("original predicate test(x > 1):" + test);
        System.out.println("-------------");

        Predicate<Integer> other;
        other = integer -> integer != null && integer < -1;
        Predicate<Integer> orPredicate = original.or(other);
        //false
        boolean orTest = orPredicate.test(0);
        System.out.println("or predicate test(x > 1 || x < -1):" + orTest);
        System.out.println("-------------");

        other = integer -> integer != null && integer < 10;
        Predicate<Integer> andPredicate = original.and(other);
        //true
        boolean andTest = andPredicate.test(5);
        System.out.println("and predicate test(x > 1 && x < 10):" + andTest);
        System.out.println("-------------");
    }


    @Test
    public void FunctionTest() {
        Function<Integer, String> original = (integer) -> {
            return integer + "->" + (integer + 1);
        };
        String originalResult = original.apply(100);
        System.out.println(originalResult);
        System.out.println("-------------");

        Function<String, Result> after;
        after = originalFunctionValue -> Result.success(originalFunctionValue + "->" + "!!!");
        Function<Integer, Result> andThen = original.andThen(after);
        Result andThenResult = andThen.apply(100);
        System.out.println(andThenResult.getMsg());
        System.out.println("-------------");

        Function<String, Integer> before = input -> Optional.ofNullable(input).map(String::length).orElse(0);
        Function<String, String> compose = original.compose(before);
        String composeResult = compose.apply("10086");
        System.out.println(composeResult);
        System.out.println("-------------");

        Function<String, String> identity = Function.identity();
        String haha = identity.apply("haha");
        System.out.println(haha);
        System.out.println("-------------");
    }

    @Test
    public void SupplierTest() {
        Supplier<String> original = () -> "a value";
        String originalResult = original.get();
        System.out.println(originalResult);
        System.out.println("-------------");
    }


}
