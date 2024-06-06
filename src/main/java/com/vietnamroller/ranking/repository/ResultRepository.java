package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.ResultDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface ResultRepository extends R2dbcRepository<ResultDTO, Long> {
}