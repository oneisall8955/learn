package com.oneisall.learn.java.advanced.function;

import org.junit.Test;

import java.util.function.Consumer;

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
     * andThen(Consumer after) 接口,before消费者接受一个after消费者,返回一个新的andThen消费者
     * 新的andThen消费者的accept(t)逻辑是:先进行before消费后,再进行after的消费
     */
    @Test
    public void ConsumerTest() {
        Consumer<String> before = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("before consumer accept:" + s);
            }
        };
        before.accept("1");
        System.out.println("--------------");
        Consumer<String> after = s -> {
            System.out.println("after consumer accept:" + s);
        };
        Consumer<String> andThen = before.andThen(after);
        andThen.accept("2");
    }


}
