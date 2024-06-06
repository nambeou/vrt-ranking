package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.TournamentDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface TournamentRepository extends R2dbcRepository<TournamentDTO, Long> {
}