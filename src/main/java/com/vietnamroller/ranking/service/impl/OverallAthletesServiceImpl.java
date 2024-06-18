package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.OverallAthletes;
import com.vietnamroller.ranking.repository.OverallAthletesRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.OverallAthletesService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OverallAthletesServiceImpl extends GenericReactiveService<OverallAthletes, Long> implements OverallAthletesService {

    private final OverallAthletesRepository repository;

    public OverallAthletesServiceImpl(OverallAthletesRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected void updateEntity(OverallAthletes existingEntity, OverallAthletes newEntity) {
        existingEntity.setOverallId(newEntity.getOverallId());
        existingEntity.setAthleteId(newEntity.getAthleteId());
    }

    @Override
    public Flux<OverallAthletes> findAllAthletesByOverallId(Long overallId) {
        return repository.findAllByOverallId(overallId);
    }

    @Override
    public Flux<OverallAthletes> findAllOverallsByAthleteId(Long athleteId) {
        return repository.findAllByAthleteId(athleteId);
    }
}
