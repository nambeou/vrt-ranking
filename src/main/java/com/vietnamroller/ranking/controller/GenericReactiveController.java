package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.service.ReactiveCrudService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class GenericReactiveController<T, ID> {

    protected final ReactiveCrudService<T, ID> service;

    protected GenericReactiveController(ReactiveCrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public Flux<T> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<T> getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<T> create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public Mono<T> update(@PathVariable ID id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable ID id) {
        return service.delete(id);
    }
}
