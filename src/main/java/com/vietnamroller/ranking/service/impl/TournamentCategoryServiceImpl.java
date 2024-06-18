package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.TournamentCategories;
import com.vietnamroller.ranking.repository.TournamentCategoriesRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import org.springframework.stereotype.Service;

@Service
public class TournamentCategoryServiceImpl extends GenericReactiveService<TournamentCategories, Long> implements TournamentCategoryService {

    public TournamentCategoryServiceImpl(TournamentCategoriesRepository repository) {
        super(repository);
    }


    @Override
    protected void updateEntity(TournamentCategories existingEntity, TournamentCategories newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setTournamentId(newEntity.getTournamentId());
    }
}
