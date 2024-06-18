package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AthleteAchievementsRepository extends ReactiveCrudRepository<AthleteAchievements, Long> {
}
