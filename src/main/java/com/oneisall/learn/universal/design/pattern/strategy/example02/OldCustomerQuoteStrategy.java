package com.oneisall.learn.universal.design.pattern.strategy.example02;

import java.math.BigDecimal;

/**
 * 老客户的报价策略实现
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:35
 */
public class OldCustomerQuoteStrategy implements QuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户享有9折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal("0.9")).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
