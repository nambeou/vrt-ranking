package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.repository.AthleteRankingsRepository;
import com.vietnamroller.ranking.service.AthleteRankingsService;
import com.vietnamroller.ranking.service.AthleteService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AthleteRankingsServiceImpl extends GenericReactiveService<AthleteRankings, Long> implements AthleteRankingsService {


    private final AthleteRankingsRepository repository;
    private final RankingService rankingService;
    private final AthleteService athleteService;

    public AthleteRankingsServiceImpl(AthleteRankingsRepository repository,
                                      RankingService rankingService,
                                      AthleteService athleteService
    ) {
        super(repository);
        this.repository = repository;
        this.rankingService = rankingService;
        this.athleteService = athleteService;
    }

    @Override
    protected void updateEntity(AthleteRankings existingEntity, AthleteRankings newEntity) {
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setRankingId(newEntity.getRankingId());
    }

    @Override
    public Flux<AthleteRankings> findAllRankingsByAthleteId(Long athleteId) {
        return repository.findAllByAthleteId(athleteId).flatMap(this::enrich);
    }

    @Override
    public Flux<AthleteRankings> findAthleteByRankingId(Long rankingId) {
        return repository.findAllByRankingId(rankingId).flatMap(this::enrich);
    }


    @Override
    protected Mono<AthleteRankings> enrich(AthleteRankings entity) {
        return Mono.zip(this.rankingService.getById(entity.getRankingId()),
                        this.athleteService.getById(entity.getAthleteId()))
                .map(tuple -> {
                    entity.setRanking(tuple.getT1());
                    entity.setAthlete(tuple.getT2());
                    return entity;
                });
    }
}
