package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ResultService extends ReactiveCrudService<Result, Long> {
    // Additional methods specific to ResultService can be declared here
}
