package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AthleteRankingsRepository extends ReactiveCrudRepository<AthleteRankings, Long> {

    Flux<AthleteRankings> findAllByAthleteId(Long athleteId);
}
