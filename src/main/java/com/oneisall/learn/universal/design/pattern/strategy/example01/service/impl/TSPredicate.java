package com.oneisall.learn.universal.design.pattern.strategy.example01.service.impl;


import com.oneisall.learn.universal.design.pattern.strategy.example01.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.example01.domain.Hero;

/**
 *
 */
public class TSPredicate implements Predicate<Hero> {

    @Override
    public boolean test(Hero t) {
        return Hero.HeroType.assassin.equals(t.getType())&&t.getStature()>170;
    }

}
