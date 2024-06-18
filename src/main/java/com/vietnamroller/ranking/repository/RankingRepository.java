package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.Ranking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface RankingRepository extends ReactiveCrudRepository<Ranking, Long> {

    Flux<Ranking> findByCategoryId(Long categoryId);

}
