package com.oneisall.learn.java.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author liuzhicong
 **/
public class ReactiveUserRepository implements ReactiveRepository<User>{



    @Override
    public Mono<Void> save(Publisher<User> publisher) {
        return null;
    }

    @Override
    public Mono<User> findFirst() {
        return null;
    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Mono<User> findById(String id) {
        return null;
    }
}
