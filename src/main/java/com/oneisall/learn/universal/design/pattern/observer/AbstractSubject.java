package com.oneisall.learn.universal.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题,一些默认的方法
 *
 * @author : oneisall
 * @version : v1 2019/7/2 11:51
 */
public abstract class AbstractSubject implements Subject {

    private String subjectName;

    private String action;

    public AbstractSubject(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 保存该主题下所有的观察者
     */
    private List<Observer> list = new ArrayList<Observer>();

    /**
     * 添加观察者
     *
     * @param observer 观察者
     */
    @Override
    public void attach(Observer observer) {
        list.add(observer);
    }

    /**
     * 删除观察者
     *
     * @param observer 观察者
     */
    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }


    /**
     * 通知所有观察者
     */
    @Override
    public void notifyAllObserver() {
        for (Observer obs : list) {
            //更新当前主题（subject）对象的信息到所有观察者中
            obs.update(this);
        }
    }

    @Override
    public String getName() {
        return subjectName;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
