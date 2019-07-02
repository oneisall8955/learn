package com.oneisall.learn.universal.design.pattern.observer;

/**
 * 主题接口
 *
 * @author : oneisall
 * @version : v1 2019/7/2 14:12
 */
public interface Subject {

    /**
     * 添加观察者
     *
     * @param observer 观察者
     */
    public void attach(Observer observer);

    /**
     * 删除观察者
     *
     * @param observer 观察者
     */
    public void detach(Observer observer);

    /**
     * 通知所有观察者
     */
    public void notifyAllObserver();

    /**
     * 设置动作/状态
     * @param action 动作/状态
     */
    void setAction(String action);

    /**
     * 获取动作/状态
     * @return 动作/状态
     */
    String getAction();

    /**
     * 主题名称
     * @return 主题名称
     */
    String getName();
}
