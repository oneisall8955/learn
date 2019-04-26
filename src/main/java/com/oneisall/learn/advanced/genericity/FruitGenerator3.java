package com.oneisall.learn.advanced.genericity;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2019/4/11 15:16
 */
public class FruitGenerator3<T> implements Generator<Generic<T>> {

    private T data;

    public FruitGenerator3(T data) {
        this.data = data;
    }

    @Override
    public Generic<T> next() {
        return new Generic<>(data);
    }
}
