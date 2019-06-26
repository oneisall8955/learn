package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 职业技能接口
 * @author oenisall
 */
public interface Profession {

    /**
     * 技能描述,默认不会打代码
     *
     * @return 技能描述
     */
    default String skill() {
        return "小白只会BB，不会打代码";
    }

    /**
     * 薪水默认0
     *
     * @return 薪水默认0
     */
    default int salary(){
        return 0;
    }

}
