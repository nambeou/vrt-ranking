package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Ranking;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RankingService extends ReactiveCrudService<Ranking, Long> {
    // Additional methods specific to RankingService can be declared here
}
