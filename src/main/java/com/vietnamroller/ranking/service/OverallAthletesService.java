package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.linked.OverallAthletes;
import reactor.core.publisher.Flux;

public interface OverallAthletesService extends ReactiveCrudService<OverallAthletes, Long> {
    Flux<OverallAthletes> findAllAthletesByOverallId(Long overallId);

    Flux<OverallAthletes> findAllOverallsByAthleteId(Long athleteId);
}
