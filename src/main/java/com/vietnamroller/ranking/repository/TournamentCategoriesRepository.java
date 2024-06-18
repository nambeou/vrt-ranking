package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.model.linked.TournamentCategories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TournamentCategoriesRepository extends ReactiveCrudRepository<TournamentCategories, Long> {
}
