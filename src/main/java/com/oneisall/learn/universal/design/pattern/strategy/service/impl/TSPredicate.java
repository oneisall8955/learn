package com.oneisall.learn.universal.design.pattern.strategy.service.impl;


import com.oneisall.learn.universal.design.pattern.strategy.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero;

/**
 *
 */
public class TSPredicate implements Predicate<Hero> {

    @Override
    public boolean test(Hero t) {
        return Hero.HeroType.assassin.equals(t.getType())&&t.getStature()>170;
    }

}
