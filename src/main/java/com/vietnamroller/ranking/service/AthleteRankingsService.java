package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import reactor.core.publisher.Flux;

public interface AthleteRankingsService extends ReactiveCrudService<AthleteRankings, Long> {
    Flux<AthleteRankings> findAllRankingsByAthleteId(Long athleteId);
}
