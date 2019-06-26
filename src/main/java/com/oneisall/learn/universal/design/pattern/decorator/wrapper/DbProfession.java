package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Profession;
import com.oneisall.learn.universal.design.pattern.decorator.AbstractProfessionDecorator;

/**
 * 数据库工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class DbProfession extends AbstractProfessionDecorator {

    public DbProfession(Profession profession) {
        super(profession);
    }

    @Override
    public String skill() {
        return "SQL 调优、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+1000;
    }
}
