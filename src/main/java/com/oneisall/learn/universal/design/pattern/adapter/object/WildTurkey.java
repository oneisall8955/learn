package com.oneisall.learn.universal.design.pattern.adapter.object;

/**
 * 野生火鸡
 *
 * @author : oneisall
 * @version : v1 2019/7/2 09:51
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance!");
    }
}
