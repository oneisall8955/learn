package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 原生程序员，用来定义初始化的公共的特征
 * @author oneisall
 */
public class OriginalProfession implements Profession {

    /**
     * @return 最基础的程序员技能只会基础科目：数据结构和算法
     */
    @Override
    public String skill() {
        return "基础科目：数据结构和算法";
    }

    /**
     * @return 最基础的程序员薪水只有50
     */
    @Override
    public int salary() {
        return 50;
    }
}
