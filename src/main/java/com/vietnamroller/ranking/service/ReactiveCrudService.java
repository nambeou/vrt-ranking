package com.vietnamroller.ranking.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReactiveCrudService<T, ID> {
    Flux<T> getAll();

    Flux<T> getAll(List<ID> ids);

    Mono<T> getById(ID id);

    Mono<T> create(T entity);

    Mono<T> update(ID id, T entity);

    Mono<Void> delete(ID id);
}