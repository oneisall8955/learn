package com.oneisall.learn.universal.design.pattern.adapter.clazz;

/**
 *
 * 火鸡适配器,模拟鸭子
 *
 * @author : oneisall
 * @version : v1 2019/7/2 10:24
 */
public class TurkeyAdapter extends Turkey implements Duck {

    @Override
    public void quack() {
        this.gobble();
    }

}
