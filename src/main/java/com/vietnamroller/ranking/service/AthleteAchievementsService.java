package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import reactor.core.publisher.Flux;

public interface AthleteAchievementsService extends ReactiveCrudService<AthleteAchievements, Long> {
    Flux<AthleteAchievements> findAllAchievementsByAthleteId(Long athleteId);
}
