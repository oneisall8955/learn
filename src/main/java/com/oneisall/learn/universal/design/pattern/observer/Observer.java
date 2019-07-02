package com.oneisall.learn.universal.design.pattern.observer;

/**
 * 抽象观察者
 *
 * @author : oneisall
 * @version : v1 2019/7/2 11:49
 */
public interface Observer {
    /**
     * 传入主题对象,得到主题对象的通知时根据这个主题进行一些操作
     *
     * @param subject 主题
     */
    void update(Subject subject);
}
