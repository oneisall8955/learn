package com.oneisall.learn.universal.design.pattern.strategy.domain;

public class Hero {

    private HeroType type;
    private double stature;
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

    public static enum HeroType {
        assassin("刺客"), shooter("射手"), saber("剑士");
        public String text;

        private HeroType(String text) {
            this.text = text;
        }
    }
}
