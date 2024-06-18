package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.OverallAthletes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OverallAthletesRepository extends ReactiveCrudRepository<OverallAthletes, Long> {
    Flux<OverallAthletes> findAllByOverallId(Long overallId);

    Flux<OverallAthletes> findAllByAthleteId(Long athleteId);
}
