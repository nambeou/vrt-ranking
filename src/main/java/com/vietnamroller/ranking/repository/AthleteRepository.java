package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Athlete;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface AthleteRepository extends ReactiveCrudRepository<Athlete, Long> {
}