package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.repository.AthleteRankingsRepository;
import com.vietnamroller.ranking.service.AthleteRankingsService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AthleteRankingsServiceImpl extends GenericReactiveService<AthleteRankings, Long> implements AthleteRankingsService {


    private final AthleteRankingsRepository repository;

    public AthleteRankingsServiceImpl(AthleteRankingsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected void updateEntity(AthleteRankings existingEntity, AthleteRankings newEntity) {
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setRankingId(newEntity.getRankingId());
    }

    @Override
    public Flux<AthleteRankings> findAllRankingsByAthleteId(Long athleteId) {
        return repository.findAllByAthleteId(athleteId);
    }

}
