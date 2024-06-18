package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.repository.CategoryRepository;
import com.vietnamroller.ranking.repository.ResultRepository;
import com.vietnamroller.ranking.service.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResultServiceImpl extends GenericReactiveService<Result, Long> implements ResultService {

    private final ResultRepository resultRepository;
    private final CategoryService categoryService;
    private final TournamentService tournamentService;
    private final AthleteService athleteService;

    public ResultServiceImpl(ResultRepository repository,
                             CategoryService categoryService,
                             TournamentService tournamentService,
                             AthleteService athleteService) {
        super(repository);
        this.resultRepository = repository;
        this.categoryService = categoryService;
        this.tournamentService = tournamentService;
        this.athleteService = athleteService;
    }

    @Override
    protected void updateEntity(Result existingEntity, Result newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setTournamentId(newEntity.getTournamentId());
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setResult(newEntity.getResult());
        existingEntity.setPoint(newEntity.getPoint());
    }

    @Override
    public Flux<Result> findAllResultsByAthleteId(Long athleteId) {
        return resultRepository.findAllByAthleteId(athleteId);
    }

    @Override
    public Flux<Result> findAllResultsByTournamentId(Long tournamentId) {
        return resultRepository.findAllByTournamentId(tournamentId);
    }

    @Override
    protected Mono<Result> enrich(Result entity) {
        return Mono.zip(
                categoryService.getById(entity.getCategoryId()),
                tournamentService.getById(entity.getTournamentId()),
                athleteService.getById(entity.getAthleteId()))
                .map(tuple -> {
                    entity.setCategory(tuple.getT1());
                    entity.setTournament(tuple.getT2());
                    entity.setAthlete(tuple.getT3());
                    return entity;
                });
    }
}
