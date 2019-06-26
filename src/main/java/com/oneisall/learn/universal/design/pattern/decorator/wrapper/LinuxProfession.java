package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Profession;
import com.oneisall.learn.universal.design.pattern.decorator.AbstractProfessionDecorator;

/**
 * 服务器工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class LinuxProfession extends AbstractProfessionDecorator {

    public LinuxProfession(Profession profession) {
        super(profession);
    }

    @Override
    public String skill() {
        return "服务器运维、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+1500;
    }
}
