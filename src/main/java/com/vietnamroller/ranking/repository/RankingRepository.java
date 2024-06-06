package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.RankingDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface RankingRepository extends R2dbcRepository<RankingDTO, Long> {
}