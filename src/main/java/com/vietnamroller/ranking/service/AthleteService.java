package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Athlete;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AthleteService extends ReactiveCrudService<Athlete, Long> {
    // Additional methods specific to AthleteService can be declared here
}
