package com.oneisall.learn.universal.design.pattern.factory.abstraction;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 抽象工厂
 * <p>
 * 抽象工厂模式的优缺点：
 * <p>
 * 优点：
 * <p>
 * 1. 抽象工厂模式最大的好处是易于交换产品系列，由于具体工厂类，例如 Factory factory=new HondaFactory();
 *  在一个应用中只需要在初始化的时候出现一次，这就使得改变一个应用的具体工厂变得非常容易，它只需要改变具体工厂即可使用不同的产品配置。
 * 不管是任何人的设计都无法去完全防止需求的更改，或者项目的维护，那么我们的理想便是让改动变得最小、最容易，例如我现在要更改以上代码的数据库访问时，只需要更改具体的工厂即可。
 * <p>
 * 2. 抽象工厂模式的另一个好处就是它让具体的创建实例过程与客户端分离，客户端是通过它们的抽象接口操作实例，
 * 产品实现类的具体类名也被具体的工厂实现类分离，不会出现在客户端代码中。就像我们上面的例子，客户端只认识Cart和Ship，
 * 至于它是Honda里的表还是Benz品牌就不清楚了
 * <p>
 * 缺点：
 * <p>
 * 1. 如果需求来自增加功能，比如增加一个飞机(Plane)产品，就有点太烦了。首先需要增加 Plane，BenzPlane,HondaPlane。
 * 然后我们还要去修改工厂类： Factory， BenzlFactory， HondaFactory 才可以实现，需要修改三个类，实在是有点麻烦。
 * <p>
 * 2. 还有就是，客户端程序肯定不止一个，每次都需要声明 Factory factory=new HondaFactory()， 如果有100个调用获取产品的类，需要修改为Benz
 * 就需要更改100次 Factory factory=new HondaFactory()为 new BenzFactory()
 * <p>
 * 参考:
 *
 * <a href=https://blog.csdn.net/u012156116/article/details/80857255>
 * u012156116:简单工厂模式、工厂模式以及抽象工厂模式（具体）
 * </a>
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:51
 */
public interface Factory {

    /**
     * 创造车
     *
     * @return 车
     */
    Car createCar();

    /**
     * 创造船
     *
     * @return 船
     */
    Ship createShip();
}
