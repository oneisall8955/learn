package com.oneisall.learn.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * 泛型测试
 *
 * 参考:
 * <p></p>
 * 博客园:RainDream:<? extends T>和<? super T>:https://www.cnblogs.com/drizzlewithwind/p/6100164.html
 * <p></p>
 * 并发编程网: 泛型中? super T和? extends T的区别 : http://ifeve.com/difference-between-super-t-and-extends-t-in-java/
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

    class Pear extends Fruit {
        public Pear() {
            name = "Pear";
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

        public void methodWithT(T t) {
            System.out.println("methodWithT,T's address is : " + Integer.toHexString(t.hashCode()));
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
        fruitPlate.methodWithT(apple);

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
        Pear pear = new Pear();

        Plate<? extends Fruit> extendsFruitPlate;
        extendsFruitPlate = new Plate<Fruit>(fruit);
        // 注:此处运用的是多态,有参构造函数中 Plate(Fruit item){ ... } 的item类型多态
        extendsFruitPlate = new Plate<Apple>(apple);
        extendsFruitPlate = new Plate<Pear>(pear);

        // <? extends T> 容器的API注意点:不能往里存,只能往外取
        // Attention: 意思是:泛类实例用<? extends T>修饰,该泛类的API中,
        // 方法形如 method(T t)均不能再被使用.
        // 方法形如 T method(XX xx)均可以调用(XX xx代表任何确定的类型或者无参)
        // '不能往里存，只能往外取',这种描述不是很准确,只是比较通俗易懂.
        // ERROR:
        // extendsFruitPlate.set(fruit);
        // extendsFruitPlate.set(apple);
        // extendsFruitPlate.set(pear);
        // extendsFruitPlate.set(new Object());
        // extendsFruitPlate.methodWithT(fruit);
        // extendsFruitPlate.methodWithT(apple);
        // extendsFruitPlate.methodWithT(pear);
        // extendsFruitPlate.methodWithT(new Object());

        // SUCCESS
        // 获取到具体的实例是Apple类型
        Fruit getFruit = extendsFruitPlate.get();
        getFruit.echo();

        System.out.println("--------------");

        List<Fruit> fruits = Lists.newArrayList(fruit);
        List<Apple> apples = Lists.newArrayList(apple);
        List<? extends Fruit> extensFruitList;
        // 可以被 List<Fruit> 及 List<Apple> 赋值
        extensFruitList = fruits;
        extensFruitList = apples;
        // 原理一样,使用List重现
        // ERROR:调用 add(T t)
        // extensFruitList.add(fruit);
        // extensFruitList.add(apple);
        // extensFruitList.add(new Object());

        // SUCCESS:调用 T get(int index)
        Fruit getFruitByList = extensFruitList.get(0);
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

        /**
         总结:<? extends T>修饰的泛类实例,为什么可以调用形如 T method(XX xx),却不能调用 形如 method(T t)的方法?
         使用List接口例子理解:

         List<Fruit> fruits = Lists.newArrayList(new Fruit());
         List<Apple> apples = Lists.newArrayList(new Apple());
         List<Pear> pears = Lists.newArrayList(new Pear());
         List<? extends Fruit> extensFruitList;
         extensFruitList = fruits;
         extensFruitList = apples;
         extensFruitList = pears;

         编译器的理解: List<? extends Fruit> extensFruitList 这个 List ,
         你可以读取到 Fruit ,因为 extensFruitList 要么包含 Fruit 实例,要么包含 Fruit 的子类实例.

         你不能保证读取到 Apple ,因为 extensFruitList 可能指向的是 List<Fruit>
         你不能保证读取到 Pear ,因为 extensFruitList 可能指向的是 List<Apple>

         你不能插入一个 Fruit 元素，因为 extensFruitList 可能指向 List<Apple> 或 List<Pear>
         你不能插入一个 Apple 元素，因为 extensFruitList 可能指向 List<Fruit> 或 List<Pear>
         你不能插入一个 Pear 元素，因为 extensFruitList 可能指向 List<Fruit> 或 List<Apple>

         你不能往List<? extends T>中插入任何类型的对象,因为你不能保证列表实际指向的类型是什么,
         你并不能保证列表中实际存储什么类型的对象,唯一可以保证的是,你可以从中读取到T或者T的子类.

         所以,可以调用形如 T method(XX xx)的接口extensFruitList.get(0) 获取 Fruit 的实例
         却不能调用形如 method(T t)的接口extensFruitList.add(new Fruit())添加元素
         也却不能调用形如 method(T t)的接口extensFruitList.set(0,new Fruit())替换对象

         所以,当你理解以上编译器的规则,我们自定义的Plate泛类的例子自然也能理解了:
         Plate<? extends Fruit> extendsFruitPlate;
         extendsFruitPlate = new Plate<Fruit>(fruit);
         extendsFruitPlate = new Plate<Apple>(apple);
         extendsFruitPlate = new Plate<Pear>(pear);
         // ERROR:
         // extendsFruitPlate.set(apple);
         // extendsFruitPlate.methodWithT(apple);
         extendsFruitPlate可能指向 Plate<Pear> , 此时以上调用等价于
         Plate<Pear> pearPlate=new Plate<Pear>();
         pearPlate.set(apple);
         pearPlate.methodWithT(apple);
         明显类型不相同,导致编译异常

         /// ERROR:
         // extendsFruitPlate.set(pear);
         // extendsFruitPlate.methodWithT(pear);
         extendsFruitPlate可能指向 Plate<Apple> , 此时以上调用等价于
         Plate<Apple> pearPlate=new Plate<Apple>();
         pearPlate.set(pear);
         pearPlate.methodWithT(pear);
         明显类型不相同,导致编译异常
         * */
    }
}
