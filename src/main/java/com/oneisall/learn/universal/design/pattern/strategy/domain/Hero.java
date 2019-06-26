package com.oneisall.learn.universal.design.pattern.strategy.domain;

/**
 * 英雄
 * @author oneisall
 */
public class Hero {

    /** 英雄类型*/
    private HeroType type;
    /** 身高*/
    private double stature;
    /** 名字*/
    private String name;

    public Hero(HeroType type, double stature, String name) {
        super();
        this.type = type;
        this.stature = stature;
        this.name = name;
    }

    public HeroType getType() {
        return type;
    }

    public void setType(HeroType type) {
        this.type = type;
    }

    public double getStature() {
        return stature;
    }

    public void setStature(double stature) {
        this.stature = stature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero [type=" + type + "(" + type.text + "), stature=" + stature + ", name=" + name + "]";
    }

    public enum HeroType {
        assassin("刺客"), shooter("射手"), saber("剑士");
        public String text;

        HeroType(String text) {
            this.text = text;
        }
    }
}
