package com.oneisall.learn.universal.design.pattern.observer;

import com.google.common.collect.Lists;

/**
 * 观察者测试
 *
 * @author : oneisall
 * @version : v1 2019/7/2 14:43
 */
public class MainTest {
    public static void main(String[] args) {

        CartoonObserver mike = new CartoonObserver("Mike");
        CartoonObserver lee = new CartoonObserver("Lee");

        CartoonSubject cartoonSubject = new CartoonSubject("cartoon home");
        cartoonSubject.attach(mike);
        cartoonSubject.attach(lee);
        cartoonSubject.setCartoonContent("One Piece 999");
        cartoonSubject.setAction("update");
        cartoonSubject.notifyAllObserver();

        StoryObserver max = new StoryObserver("Max");
        StoryObserver lu = new StoryObserver("Lu");
        StorySubject storySubject = new StorySubject("story home");
        storySubject.attach(max);
        storySubject.attach(lu);
        storySubject.setStoryList(Lists.newArrayList("s1","s2","s3"));
        storySubject.setAction("update");
        storySubject.notifyAllObserver();

        VarietyObserver me = new VarietyObserver("oneisall");
        CartoonSubject cartoonSubject2me = new CartoonSubject("cartoon home 2 me");
        cartoonSubject2me.attach(me);
        cartoonSubject2me.setCartoonContent("One Piece 999");
        cartoonSubject2me.setAction("update");
        cartoonSubject2me.notifyAllObserver();

        StorySubject storySubject2me = new StorySubject("story home 2 me");
        storySubject2me.attach(me);
        storySubject2me.setStoryList(Lists.newArrayList("s1","s2","s3"));
        storySubject2me.setAction("update");
        storySubject2me.notifyAllObserver();
    }
}
