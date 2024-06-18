package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.model.linked.TournamentCategories;
import com.vietnamroller.ranking.repository.TournamentRepository;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TournamentServiceImpl extends GenericReactiveService<Tournament, Long> implements TournamentService {


    private final TournamentCategoryService tournamentCategoryService;
    private final CategoryService categoryService;
    public TournamentServiceImpl(TournamentRepository repository,
                                 TournamentCategoryService tournamentCategoryService,
                                 CategoryService categoryService) {
        super(repository);
        this.tournamentCategoryService = tournamentCategoryService;
        this.categoryService = categoryService;
    }

    @Override
    protected void updateEntity(Tournament existingEntity, Tournament newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setStartDate(newEntity.getStartDate());
        existingEntity.setEndDate(newEntity.getEndDate());
        existingEntity.setLocation(newEntity.getLocation());
    }

    @Override
    public Mono<Tournament> getById(Long aLong, boolean enriched) {
        return enriched
                ? this.repository.findById(aLong).flatMap(this::enrich)
                : this.repository.findById(aLong);
    }

    @Override
    protected Mono<Tournament> enrich(Tournament entity) {
        return tournamentCategoryService
                .findAllCategoriesByTournamentId(entity.getId())
                .flatMap(categoryService::getById)
                .collectList()
                .map(categories -> {
                    entity.setCategories(categories);
                    return entity;
                });

    }
}
