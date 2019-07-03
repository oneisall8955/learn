package com.oneisall.learn.universal.design.pattern.strategy.example02;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 报价上下文角色
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:43
 */
@SuppressWarnings("all")
public class QuoteContext {

    /**
     * 持有一个具体的报价策略
     */
    private QuoteStrategy quoteStrategy;

    public QuoteContext() {

    }

    /**
     * 由Context来决定策略类型
     *
     * @param customType 用户类型
     */
    public QuoteContext(CustomType customType) {
        Objects.requireNonNull(customType);
        String customTypeName = customType.name();
        String customTypeNameFix = StringUtils.capitalize(StringUtils.lowerCase(customTypeName));
        String basePackName = "com.oneisall.learn.universal.design.pattern.strategy.example02";
        String factoryPackageName = basePackName + "." + customTypeNameFix + "CustomerQuoteStrategy";
        try {
            quoteStrategy = (QuoteStrategy) Class.forName(factoryPackageName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 客户端经过一系列算法,得到一个策略,故由客户端来设置策略
     *
     * @param quoteStrategy 策略
     */
    public QuoteContext(QuoteStrategy quoteStrategy) {
        this.quoteStrategy = quoteStrategy;
    }

    /**
     * 由客户端来设置策略
     *
     * @param quoteStrategy 策略
     */
    public void setQuoteStrategy(QuoteStrategy quoteStrategy) {
        this.quoteStrategy = quoteStrategy;
    }

    /**
     * 回调具体报价策略的方法
     */
    public BigDecimal getPrice(BigDecimal originalPrice) {
        Objects.requireNonNull(quoteStrategy);
        return quoteStrategy.getPrice(originalPrice);
    }
}
