package com.oneisall.learn.universal.design.pattern.strategy.example02;

import java.math.BigDecimal;

/**
 * VIP客户的报价策略实现
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:35
 */
public class VipCustomerQuoteStrategy implements QuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户享有8折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal("0.8")).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
