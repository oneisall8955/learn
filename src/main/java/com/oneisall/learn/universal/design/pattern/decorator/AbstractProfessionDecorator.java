package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 装饰程序员，用来分化不同的特征
 * @author oenisall
 */
public abstract class AbstractProfessionDecorator implements Profession {

    private Profession profession;

    public AbstractProfessionDecorator(Profession profession) {
        this.profession = profession;
    }

    @Override
    public String skill(){
        return profession.skill();
    }

    @Override
    public int salary() {
        return profession.salary();
    }

}
