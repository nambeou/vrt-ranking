package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.OverallAthletes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OverallAthletesRepository extends ReactiveCrudRepository<OverallAthletes, Long> {
}
