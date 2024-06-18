package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.linked.TournamentCategories;
import reactor.core.publisher.Flux;

public interface TournamentCategoryService extends ReactiveCrudService<TournamentCategories, Long> {
    // Additional methods specific to CategoryService can be declared here
    Flux<TournamentCategories> findAllCategoriesByTournamentId(Long tournamentId);

    Flux<TournamentCategories> findAllTournamentsByCategoryId(Long categoryId);
}
