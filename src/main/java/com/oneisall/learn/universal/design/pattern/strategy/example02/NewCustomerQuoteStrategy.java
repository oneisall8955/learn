package com.oneisall.learn.universal.design.pattern.strategy.example02;

import java.math.BigDecimal;

/**
 * 新客户的报价策略实现类
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:35
 */
public class NewCustomerQuoteStrategy implements QuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }
}
