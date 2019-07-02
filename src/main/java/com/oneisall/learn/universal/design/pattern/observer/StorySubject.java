package com.oneisall.learn.universal.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 故事主题
 *
 * @author : oneisall
 * @version : v1 2019/7/2 14:58
 */
public class StorySubject implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String action;
    private String name;
    private List<String> storyList;

    public StorySubject(String name) {
        this.name = name;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<String> storyList) {
        this.storyList = storyList;
    }
}
