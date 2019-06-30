package com.oneisall.learn.universal.design.pattern.factory.simple;

import com.oneisall.learn.universal.design.pattern.factory.Car;
import com.oneisall.learn.universal.design.pattern.factory.CarBrand;
import com.oneisall.learn.universal.design.pattern.factory.BenzCar;
import com.oneisall.learn.universal.design.pattern.factory.BwmCar;
import com.oneisall.learn.universal.design.pattern.factory.HondaCar;

/**
 * 简单工厂模式
 * <p>
 * 特点
 * <p>
 * 1 它是一个具体的类，非接口 抽象类。有一个重要的create()方法，利用if或者 switch创建产品并返回。
 * <p>
 * 2 create()方法通常是静态的，所以也称之为静态工厂。
 * <p>
 * 缺点
 * <p>
 * 1 扩展性差（我想增加一种面条，除了新增一个面条产品类，还需要修改工厂类方法）
 * <p>
 * 2 不同的产品需要不同额外参数的时候 不支持。
 * <p>
 * 参考文章 1:
 * <a href="https://www.cnblogs.com/zailushang1996/p/8601808.html">
 * zailushang1996 : java 三种工厂模式
 * </a>
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:25
 */
public class SimpleCarFactory {
    /**
     * @param brand 品牌
     * @return 车
     */
    public static Car createCar(CarBrand brand) {
        Car newCar;
        switch (brand) {
            case BMW:
                newCar = new BwmCar();
                break;
            case BENZ:
                newCar = new BenzCar();
                break;
            case HONDA:
                newCar = new HondaCar();
                break;
            default:
                //提供一个默认的车???或者抛出异常
                newCar = null;
                break;
        }
        return newCar;
    }
}
