package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
@Slf4j
public abstract class GenericController<T, ID> {

    @CrossOrigin(origins = {"*"})
    @GetMapping("")
    public Flux<T> getAll() {
        return this.service().findAll();
    }

    protected abstract GenericService<T, ID> service();
}