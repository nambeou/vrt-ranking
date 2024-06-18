package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.model.linked.TournamentCategories;
import com.vietnamroller.ranking.repository.TournamentCategoriesRepository;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TournamentCategoryServiceImpl extends GenericReactiveService<TournamentCategories, Long> implements TournamentCategoryService {

    private final TournamentCategoriesRepository repository;

    public TournamentCategoryServiceImpl(TournamentCategoriesRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    protected void updateEntity(TournamentCategories existingEntity, TournamentCategories newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setTournamentId(newEntity.getTournamentId());
    }

    @Override
    public Flux<Long> findAllCategoriesByTournamentId(Long tournamentId) {
        return repository.findAllByTournamentId(tournamentId)
                .map(TournamentCategories::getCategoryId);
    }

    @Override
    public Flux<Long> findAllTournamentsByCategoryId(Long categoryId) {
        return repository.findAllByCategoryId(categoryId)
                .map(TournamentCategories::getTournamentId);
    }
}
