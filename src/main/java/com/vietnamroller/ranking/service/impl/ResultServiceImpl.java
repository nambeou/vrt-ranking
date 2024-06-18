package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.repository.ResultRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ResultServiceImpl extends GenericReactiveService<Result, Long> implements ResultService {

    private final ResultRepository repository;

    public ResultServiceImpl(ResultRepository repository) {
        super(repository);
        this.repository = repository;
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
        return repository.findAllByAthleteId(athleteId);
    }

    @Override
    public Flux<Result> findAllResultsByTournamentId(Long tournamentId) {
        return repository.findAllByTournamentId(tournamentId);
    }
}
