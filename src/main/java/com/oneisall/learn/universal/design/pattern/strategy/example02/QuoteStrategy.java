package com.oneisall.learn.universal.design.pattern.strategy.example02;

import java.math.BigDecimal;

/**
 * 价格策略接口
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:33
 */
public interface QuoteStrategy {
    /**
     * 获取折后价的价格
     *
     * @param originalPrice 原价
     * @return 折后价的价格
     */
    BigDecimal getPrice(BigDecimal originalPrice);
}
