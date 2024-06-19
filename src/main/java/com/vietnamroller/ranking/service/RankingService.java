package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Ranking;
import reactor.core.publisher.Flux;

public interface RankingService extends ReactiveCrudService<Ranking, Long> {
    // Additional methods specific to RankingService can be declared here
    Flux<Ranking> getByCategoryId(Long categoryId);


}
