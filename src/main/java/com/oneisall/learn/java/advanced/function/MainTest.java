package com.oneisall.learn.java.advanced.function;

import com.oneisall.learn.java.common.Result;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

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

    /**
     * Function<T,R>
     * R apply(T t) 接口,定义一个接受T类型的方法,经过执行运算,返回类型为R的结果
     * 调用apply(t) 进行执行并返回结果
     *
     * andThen(Function<? super R, ? extends V> after),
     * original函数接受一个after函数,返回一个新的andThen函数,新的函数的输入类型为original输入类型,输出类型为after的输出类型
     * (注:after函数必须是以original的结果类型R为after输入类型!!! after函数输出结果类型V将作为新函数的输出结果类型),
     * 新的函数andThen.apply(t)时,逻辑为:调用original处理T t得出中间结果R r,再将中间结果r作为after的输入,得到最终结果V v
     *
     * compose(Function<? super V, ? extends T> before),
     * original函数接受一个before函数,返回一个新的compose函数,新的函数的输入类型为before输入类型,输出类型为compose的输出类型
     * (注:before函数必须是以original的输入类型T为after输入类型!!! before函数输入结果类型V将作为新函数的输入结果类型),
     * 新的函数compose.apply(v)时,逻辑为:调用before处理V v得出中间结果T t,再将中间结果t作为original的输入,得到最终结果R r
     *
     * static <T> identity()
     * 返回输出等于输入的特殊函数,输出与输入的类型必须相同!
     *
     */
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

    /**
     * Supplier<T>
     * T get() 定义一个不接收值得方法,经过执行运算,返回类型为T的结果
     * 调用get() 进行执行并返回结果
     */
    @Test
    public void SupplierTest() {
        Supplier<String> original = () -> "a value";
        String originalResult = original.get();
        System.out.println(originalResult);
        System.out.println("-------------");
    }

    /**
     * UnaryOperator<T>
     * UnaryOperator作为继承Function接口操作,限定了Function输出输入格式必须是相同得,是一个特殊类型得函数,
     */
    @Test
    public void UnaryOperatorTest() {
        UnaryOperator<String> original = input -> input + "->" + input;
        Function<String, String> specialOriginal = original;
        //function ...
    }
}
