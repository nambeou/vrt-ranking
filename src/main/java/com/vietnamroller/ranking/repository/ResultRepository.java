package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface ResultRepository extends ReactiveCrudRepository<Result, Long> {
}