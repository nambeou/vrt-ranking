package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Result;
import reactor.core.publisher.Flux;

public interface ResultService extends ReactiveCrudService<Result, Long> {
    // Additional methods specific to ResultService can be declared here
    Flux<Result> findAllResultsByAthleteId(Long athleteId);

    Flux<Result> findAllResultsByTournamentId(Long tournamentId);
}
