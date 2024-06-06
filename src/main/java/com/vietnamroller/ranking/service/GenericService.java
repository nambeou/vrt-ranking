package com.vietnamroller.ranking.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public abstract class GenericService<T, ID> {


    public Mono<T> save(T entity) {
        return repository().save(entity);
    }

    public Mono<T> findById(ID id) {
        return repository().findById(id).flatMap(this::addDetails);
    }

    @Cacheable
    public Flux<T> findAll() {
        return repository().findAll().flatMap(this::addDetails).sort();
    }

    public Flux<T> findByIds(List<ID> ids) {
        return repository().findAllById(ids).flatMap(this::addDetails).sort();
    }

    public void deleteById(ID id) {
        repository().deleteById(id);
    }


    protected abstract R2dbcRepository<T, ID> repository();

    abstract Mono<T> addDetails(T entity);
}

