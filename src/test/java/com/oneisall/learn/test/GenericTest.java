package com.oneisall.learn.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试
 *
 * @author : oneisall
 * @version : v1 2019/8/13 09:29
 */
@SuppressWarnings("all")
public class GenericTest {
    class Fruit {
        protected String name = "Fruit";

        public void echo() {
            System.out.println(name);
        }
    }

    class Apple extends Fruit {
        public Apple() {
            name = "Apple";
        }
    }

    class Plate<T> {
        private T item;

        public Plate() {

        }

        public Plate(T item) {
            this.item = item;
        }

        public void set(T t) {
            item = t;
        }

        public T get() {
            return item;
        }
    }

    /**
     * 检测普通泛型容器API
     */
    @Test
    public void plateSelfTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();

        // 父类容器,放置子类
        // 此处是多态的提供的API
        // apple is a fruit
        fruitPlate.set(apple);

        // 父类容器与子类容器的关系,此处关注的是容器这个类!!!
        // 并没有像父子类中的继承关系!!!
        // apple's plate is not a fruit's plate
        // ERROR
        // fruitPlate = applePlate;
        // ERROR
        // applePlate = fruitPlate;

        // 为解决此类容器间'继承多态'问题,使用了<? extends T>及<? supper T>
    }

    /**
     * <? extends T> 上界通配符(Upper Bounds Wildcards)
     * <p>
     * Plate <? extends Fruit> extendsFruitPlate 可以被 Plate <Fruit> 及 Plate <Apple> 赋值
     */
    @Test
    public void extendsTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();
        Plate<? extends Fruit> extendsFruitPlate = new Plate<>();

        // ERROR
        // fruitPlate = applePlate;

        // SUCCESS
        // Plate<? extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类
        extendsFruitPlate = applePlate;
        extendsFruitPlate = fruitPlate;

        // ERROR
        // 反过来明显是错误的!
        // applePlate = extendsFruitPlate;
        // fruitPlate = extendsFruitPlate;

        // <? extends T> 容器的API注意点
        // extendsFruitPlate.set(fruit);
        // extendsFruitPlate.set(new Object());
        // extendsFruitPlate.set(apple);
        Fruit getFruit = extendsFruitPlate.get();
    }

    /**
     * <? supper T> 下界通配符(Lower Bounds Wildcards)
     * Plate <? supper Fruit> superFruitPlate 可以被 Plate <Fruit> 及 Plate <Object> 赋值
     */
    @Test
    public void supperTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();
        Plate<Object> objectPlate = new Plate<>();
        Plate<? super Fruit> superFruitPlate = new Plate<>();

        // ERROR
        // superFruitPlate = applePlate;
        // SUCCESS
        superFruitPlate = fruitPlate;
        superFruitPlate = objectPlate;

        // ERROR
        // 反过来明显是错误的!
        // fruitPlate = superFruitPlate;
        // objectPlate = superFruitPlate;
    }

    /**
     * <? extends T> 上界通配符(Upper Bounds Wildcards)
     * 注意点: 只能获取,不能存放
     */
    @Test
    public void extendsAttentionTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        // 此处运用的是多态,有参构造函数中 Plate(Fruit item){ ... } 的item类型多态
        Plate<? extends Fruit> extendsFruitPlate = new Plate<Fruit>(apple);

        // <? extends T> 容器的API注意点:不能往里存，只能往外取
        // Attention: 意思是:泛类用<? extends T>修饰,该泛类的API中,
        //  方法形如 method(T t)均不能再被使用.
        //  方法形如 T method(XX xx)均可以调用(XX xx代表任何类型或者无参)
        // '不能往里存，只能往外取',这种描述不是很准确,只是比较通俗易懂.
        // ERROR:
        // extendsFruitPlate.set(fruit);
        // extendsFruitPlate.set(apple);
        // extendsFruitPlate.set(new Object());

        // SUCCESS
        // 获取到具体的实例是Apple类型
        Fruit getFruit = extendsFruitPlate.get();
        getFruit.echo();

        System.out.println("--------------");

        List<Fruit> fruits = Lists.newArrayList(fruit);
        List<Apple> apples = Lists.newArrayList(apple);
        List<? extends Fruit> fruitList = new ArrayList<>(apples);
        // 原理一样,使用List重现
        // ERROR:调用 add(T t)
        // fruitList.add(fruit);
        // fruitList.add(apple);
        // fruitList.add(new Object());

        // SUCCESS:调用 T get(int index)
        Fruit getFruitByList = fruitList.get(0);
        getFruitByList.echo();
        System.out.println("--------------");

        // 接口测试
        class ExtendsClass {
            public void extendsMethod(List<? extends Fruit> extendsList) {
                // ERROR:
                // 出错原理同上,不能调用形如 method(T t)方法
                // extendsList.add(fruit);
                // extendsList.add(apple);
                // extendsList.add(new Object());
                // SUCCESS
                // 获取是父类,可以强转为子类再使用
                Fruit getFruitByList = extendsList.get(0);
                getFruitByList.echo();
            }
        }
        ExtendsClass extendsClass = new ExtendsClass();
        extendsClass.extendsMethod(fruits);
        extendsClass.extendsMethod(apples);

        List<Object> objects = Lists.newArrayList(new Object());
        // ERROR
        // 明显无法调用
        // extendsClass.extendsMethod(objects);
    }
}
