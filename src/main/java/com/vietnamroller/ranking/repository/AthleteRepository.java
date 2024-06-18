package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Athlete;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AthleteRepository extends ReactiveCrudRepository<Athlete, Long> {
    Flux<Athlete> findAllByTeamId(Long teamId);
}
