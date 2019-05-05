package com.oneisall.learn.universal.design.pattern.strategy.service.impl;


import com.oneisall.learn.universal.design.pattern.strategy.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero;

public class StaturePredicate implements Predicate<Hero> {

    @Override
    public boolean test(Hero t) {
        return t.getStature() > 170;
    }

}
