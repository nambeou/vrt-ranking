package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.OverallAthletes;
import com.vietnamroller.ranking.repository.OverallAthletesRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.OverallAthletesService;
import org.springframework.stereotype.Service;

@Service
public class OverallAthletesServiceImpl extends GenericReactiveService<OverallAthletes, Long> implements OverallAthletesService {

    public OverallAthletesServiceImpl(OverallAthletesRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(OverallAthletes existingEntity, OverallAthletes newEntity) {
        existingEntity.setOverallId(newEntity.getOverallId());
        existingEntity.setAthleteId(newEntity.getAthleteId());
    }
}
