package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ResultRepository extends ReactiveCrudRepository<Result, Long> {
    Flux<Result> findAllByAthleteId(Long athleteId);

    Flux<Result> findAllByTournamentId(Long tournamentId);
}
