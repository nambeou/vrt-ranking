package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.AthleteDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface AthleteRepository extends R2dbcRepository<AthleteDTO, Long> {
}