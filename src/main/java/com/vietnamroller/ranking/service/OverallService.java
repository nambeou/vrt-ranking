package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Overall;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OverallService extends ReactiveCrudService<Overall, Long> {
    // Additional methods specific to OverallService can be declared here
}
