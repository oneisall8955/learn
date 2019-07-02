package com.oneisall.learn.universal.design.pattern.observer.jdk;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * JDK 自带观察者测试
 *
 * @author : oneisall
 * @version : v1 2019/7/2 17:59
 */
public class MainTest {

    private static class MyObserver implements Observer {
        Logger logger = LoggerFactory.getLogger(MyObserver.class);
        private String name;

        MyObserver(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void update(Observable o, Object arg) {
            MyObservable myObservable = (MyObservable) o;
            String observableName = myObservable.getName();
            List<String> cartoonList = myObservable.getCartoonList();
            logger.info("{}订阅的{}更新了,内容{}真好看", this.name, observableName, StringUtils.join(cartoonList, ","));
        }
    }

    private static class MyObservable extends Observable {

        private String name;

        MyObservable(String name) {
            this.name = name;
        }

        List<String> cartoonList = new ArrayList<>();

        void changeAndNotify() {
            //必须在继承Observable的子类中才能使用setChanged()!!!
            super.setChanged();
            this.notifyObservers();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        List<String> getCartoonList() {
            return cartoonList;
        }

        public void setCartoonList(List<String> cartoonList) {
            this.cartoonList = cartoonList;
        }
    }

    public static void main(String[] args) {
        //订阅者
        MyObserver lu = new MyObserver("Lu");
        MyObserver lee = new MyObserver("Lee");
        //主题
        MyObservable subject = new MyObservable("Cartoon");
        subject.addObserver(lu);
        subject.addObserver(lee);
        //改变了,通知
        List<String> cartoonList = subject.getCartoonList();
        cartoonList.add("One Piece 999");
        cartoonList.add("Naruto 345");
        subject.changeAndNotify();
    }
}
