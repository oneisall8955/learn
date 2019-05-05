package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 装饰程序员，用来分化不同的特征
 */
public abstract class ProgrammerDecorator implements Programmer {

    private Programmer programmer;

    public ProgrammerDecorator(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public String skill(){
        return programmer.skill();
    }

    @Override
    public int salary() {
        return programmer.salary();
    }

}
