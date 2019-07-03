package com.oneisall.learn.universal.design.pattern.strategy.example02;

/**
 * 客户类型
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:23
 */
public enum CustomType {
    NEW("新客户"), OLD("老客户"), VIP("VIP客户");

    public final String text;

    CustomType(String text) {
        this.text = text;
    }
}
