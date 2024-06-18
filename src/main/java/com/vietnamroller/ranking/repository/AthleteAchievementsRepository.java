package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AthleteAchievementsRepository extends ReactiveCrudRepository<AthleteAchievements, Long> {
    Flux<AthleteAchievements> findAllByAthleteId(Long athleteId);
}
