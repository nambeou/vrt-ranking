package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.OverallDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface OverAllRepository extends R2dbcRepository<OverallDTO, Long> {

}