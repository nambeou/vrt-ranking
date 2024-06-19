package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.model.linked.AthleteResults;
import com.vietnamroller.ranking.model.linked.TournamentResults;
import com.vietnamroller.ranking.repository.AthleteResultsRepository;
import com.vietnamroller.ranking.repository.ResultRepository;
import com.vietnamroller.ranking.repository.TournamentResultsRepository;
import com.vietnamroller.ranking.service.AthleteService;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResultServiceImpl extends GenericReactiveService<Result, Long> implements ResultService {

    private final TournamentResultsRepository tournamentResultsRepository;
    private final AthleteResultsRepository athleteResultsRepository;
    private final CategoryService categoryService;
    private final AthleteService athleteService;

    public ResultServiceImpl(ResultRepository repository,
                             TournamentResultsRepository tournamentResultsRepository,
                             AthleteResultsRepository athleteResultsRepository,
                             CategoryService categoryService,
                             AthleteService athleteService) {
        super(repository);
        this.tournamentResultsRepository = tournamentResultsRepository;
        this.athleteResultsRepository = athleteResultsRepository;
        this.categoryService = categoryService;
        this.athleteService = athleteService;
    }

    @Override
    protected void updateEntity(Result existingEntity, Result newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setResult(newEntity.getResult());
        existingEntity.setPoint(newEntity.getPoint());
    }

    @Override
    public Flux<Result> findAllResultsByAthleteId(Long athleteId) {
        return athleteResultsRepository.findAllByAthleteId(athleteId)
                .map(AthleteResults::getResultId)
                .collectList()
                .flatMapMany(repository::findAllById);
    }

    @Override
    public Flux<Result> findAllResultsByTournamentId(Long tournamentId) {
        return tournamentResultsRepository.findAllByTournamentId(tournamentId)
                .map(TournamentResults::getResultId)
                .collectList()
                .flatMapMany(repository::findAllById).flatMap(this::enrich);
    }

    @Override
    public Flux<Result> findAllResultsByTournamentIdAndCategoryId(Long tournamentId, Long categoryId) {
        return findAllResultsByAthleteId(tournamentId)
                .filter(result -> result.getCategory().getId().equals(categoryId));
    }


    @Override
    protected Mono<Result> enrich(Result entity) {
        return
                categoryService.getById(entity.getCategoryId())
                        .map(category -> {
                            entity.setCategory(category);
                            return entity;
                        });
    }

}
