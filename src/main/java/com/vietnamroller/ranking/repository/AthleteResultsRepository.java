package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.AthleteResults;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AthleteResultsRepository extends ReactiveCrudRepository<AthleteResults, Long> {

    Flux<AthleteResults> findAllByAthleteId(Long athleteId);
}
