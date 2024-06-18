package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AthleteRankingsRepository extends ReactiveCrudRepository<AthleteRankings, Long> {
}
