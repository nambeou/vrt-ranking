package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.TournamentCategories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TournamentCategoriesRepository extends ReactiveCrudRepository<TournamentCategories, Long> {
    Flux<TournamentCategories> findAllByTournamentId(Long tournamentId);

    Flux<TournamentCategories> findAllByCategoryId(Long categoryId);
}
