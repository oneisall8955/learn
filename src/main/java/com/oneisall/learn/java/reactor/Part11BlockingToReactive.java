package com.oneisall.learn.java.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Supplier;

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 * <p>
 * For those who know RxJava:
 * - RxJava subscribeOn = Reactor subscribeOn
 * - RxJava observeOn = Reactor publishOn
 * - RxJava Schedulers.io <==> Reactor Schedulers.elastic
 *
 * @author Sebastien Deleuze
 * @see Flux#subscribeOn
 * @see Flux#publishOn(reactor.core.scheduler.Scheduler)
 * @see Schedulers
 */
public class Part11BlockingToReactive {

    //========================================================================================

    // TODO Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with a bounded elastic scheduler
    Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        Supplier<Publisher<User>> publisherSupplier = () -> Flux.fromIterable(repository.findAll()).subscribeOn(Schedulers.boundedElastic());
        return Flux.defer(publisherSupplier);
    }

    //========================================================================================

    // TODO Insert users contained in the Flux parameter in the blocking repository using a bounded elastic scheduler and return a Mono<Void> that signal the end of the operation
    Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(repository::save)
                .then();
    }

}
