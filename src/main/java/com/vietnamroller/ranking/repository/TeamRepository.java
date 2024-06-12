package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeamRepository extends ReactiveCrudRepository<Team, Long> {
}
