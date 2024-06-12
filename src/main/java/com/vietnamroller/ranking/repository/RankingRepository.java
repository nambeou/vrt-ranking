package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Ranking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface RankingRepository extends ReactiveCrudRepository<Ranking, Long> {
}