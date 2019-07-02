package com.oneisall.learn.universal.design.pattern.adapter.object;

/**
 * 火鸡适配器,实现了 Duck 接口，目的将火鸡转换成鸭子，以假乱真
 *
 * @author : oneisall
 * @version : v1 2019/7/2 09:53
 */
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    /**
     * 通过构造方法，获得适配对象实例化的引用
     *
     * @param turkey 火鸡
     */
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        // 由于火鸡的飞行距离较鸭子近，为了伪装成鸭子，所以让它飞三次来对应鸭子的飞行距离
        int time = 3;
        for (int i = 0; i < time; i++) {
            turkey.fly();
        }
    }
}
