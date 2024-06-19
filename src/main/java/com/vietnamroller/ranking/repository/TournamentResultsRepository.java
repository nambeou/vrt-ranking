package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.TournamentResults;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TournamentResultsRepository extends ReactiveCrudRepository<TournamentResults, Long> {
    Flux<TournamentResults> findAllByTournamentId(Long tournamentId);
}
