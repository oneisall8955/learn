package com.oneisall.learn.universal.design.pattern.strategy.example02;

import com.google.common.collect.Lists;

import java.math.BigDecimal;

/**
 * 策略模式测试
 *
 * @author : oneisall
 * @version : v1 2019/7/3 15:21
 */
@SuppressWarnings("all")
public class MainTest {

    public static BigDecimal quote(BigDecimal originalPrice, CustomType customType) {
        if (CustomType.NEW.equals(customType)) {
            System.out.println("抱歉！新客户没有折扣！");
            return originalPrice;
        } else if (CustomType.OLD.equals(customType)) {
            System.out.println("恭喜你！老客户打9折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        } else if (CustomType.VIP.equals(customType)) {
            System.out.println("恭喜你！VIP客户打8折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }
        //其他人员都是原价
        return originalPrice;
    }

    public static BigDecimal quoteImprove(BigDecimal originalPrice, CustomType customType) {
        if (CustomType.NEW.equals(customType)) {
            return quoteNewCustomer(originalPrice);
        } else if (CustomType.OLD.equals(customType)) {
            return quoteOldCustomer(originalPrice);
        } else if (CustomType.VIP.equals(customType)) {
            return quoteVIPCustomer(originalPrice);
        }
        //其他人员都是原价
        return originalPrice;
    }

    /**
     * 对VIP客户的报价算法
     *
     * @param originalPrice 原价
     * @return 折后价
     */
    private static BigDecimal quoteVIPCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户打8折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * 对老客户的报价算法
     *
     * @param originalPrice 原价
     * @return 折后价
     */
    private static BigDecimal quoteOldCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户打9折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * 对新客户的报价算法
     *
     * @param originalPrice 原价
     * @return 折后价
     */
    private static BigDecimal quoteNewCustomer(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }

    public static void main(String[] args) {
        CustomType customType = CustomType.OLD;
        BigDecimal originalPrice = new BigDecimal("100");
        BigDecimal quote = quote(originalPrice, customType);
        System.out.println(customType.text + "-" + originalPrice + "->" + quote);

        System.out.println("----------------");

        BigDecimal quoteImprove = quoteImprove(originalPrice, customType);
        System.out.println(customType.text + "-" + originalPrice + "->" + quoteImprove);
        System.out.println("----------------");

        Lists.newArrayList(CustomType.values()).forEach(type -> {
            QuoteContext quoteContext = new QuoteContext(type);
            BigDecimal quoteContextPrice = quoteContext.getPrice(originalPrice);
            System.out.println(type.text + "-" + originalPrice + "->" + quoteContextPrice);
            System.out.println("----------------");
        });
    }
}
