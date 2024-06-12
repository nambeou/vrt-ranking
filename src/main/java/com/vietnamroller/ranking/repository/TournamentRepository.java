package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Tournament;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface TournamentRepository extends ReactiveCrudRepository<Tournament, Long> {
}