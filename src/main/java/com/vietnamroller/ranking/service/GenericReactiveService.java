package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.service.ReactiveCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class GenericReactiveService<T, ID> implements ReactiveCrudService<T, ID> {

    private final ReactiveCrudRepository<T, ID> repository;

    @Override
    public Flux<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Mono<T> create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<T> update(ID id, T entity) {
        return repository.findById(id)
                .flatMap(existingEntity -> {
                    updateEntity(existingEntity, entity);
                    return repository.save(existingEntity);
                });
    }

    @Override
    public Mono<Void> delete(ID id) {
        return repository.deleteById(id);
    }

    protected abstract void updateEntity(T existingEntity, T newEntity);
}
