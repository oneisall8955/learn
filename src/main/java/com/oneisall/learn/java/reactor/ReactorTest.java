package com.oneisall.learn.java.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author liuzhicong
 **/
public class ReactorTest {

    @Test
    public void test(){
        Flux<Long> longFlux = Flux.fromIterable(List.of(1L));
        Flux<Long> just = Flux.just(1L, 2L);

    }
}
