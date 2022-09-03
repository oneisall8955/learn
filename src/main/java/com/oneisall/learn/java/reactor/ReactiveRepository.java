package com.oneisall.learn.java.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author liuzhicong
 **/
public interface ReactiveRepository<T> {

    Mono<Void> save(Publisher<T> publisher);

    Mono<T> findFirst();

    Flux<T> findAll();

    Mono<T> findById(String id);
}
