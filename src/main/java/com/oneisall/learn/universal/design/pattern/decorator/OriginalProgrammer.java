package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 原生程序员，用来定义初始化的公共的特征
 */
public class OriginalProgrammer implements Programmer {

    @Override

    public String skill() {
        return "基础科目：数据结构和算法";
    }
    @Override
    public int salary() {
        return 50;
    }
}
