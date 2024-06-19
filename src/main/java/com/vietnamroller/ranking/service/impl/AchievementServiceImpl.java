package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Achievement;
import com.vietnamroller.ranking.repository.AchievementRepository;
import com.vietnamroller.ranking.service.AchievementService;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AchievementServiceImpl extends GenericReactiveService<Achievement, Long> implements AchievementService {

    private final CategoryService categoryService;
    private final TournamentService tournamentService;

    public AchievementServiceImpl(AchievementRepository repository, CategoryService categoryService, TournamentService tournamentService) {
        super(repository);
        this.categoryService = categoryService;
        this.tournamentService = tournamentService;
    }

    @Override
    protected Mono<Achievement> enrich(Achievement entity) {
        return Mono.zip(
                categoryService.getById(entity.getCategoryId()),
                tournamentService.getById(entity.getTournamentId())
        ).map(tuple -> {
            entity.setCategory(tuple.getT1());
            entity.setTournament(tuple.getT2());
            return entity;
        });
    }

    @Override
    protected void updateEntity(Achievement existingEntity, Achievement newEntity) {
        existingEntity.setTournamentId(newEntity.getTournamentId());
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setPosition(newEntity.getPosition());
    }

}
