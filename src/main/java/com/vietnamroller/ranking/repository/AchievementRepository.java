package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Achievement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface AchievementRepository extends ReactiveCrudRepository<Achievement, Long> {
}