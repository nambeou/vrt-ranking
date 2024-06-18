package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.repository.AthleteRankingsRepository;
import com.vietnamroller.ranking.service.AthleteRankingsService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;

@Service
public class AthleteRankingsServiceImpl extends GenericReactiveService<AthleteRankings, Long> implements AthleteRankingsService {

    public AthleteRankingsServiceImpl(AthleteRankingsRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(AthleteRankings existingEntity, AthleteRankings newEntity) {
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setRankingId(newEntity.getRankingId());
    }
}
