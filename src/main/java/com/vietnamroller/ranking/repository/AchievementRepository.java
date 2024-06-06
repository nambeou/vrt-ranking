package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.AchievementDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface AchievementRepository extends R2dbcRepository<AchievementDTO, Long> {
}