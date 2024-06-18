package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.repository.ResultRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl extends GenericReactiveService<Result, Long> implements ResultService {

    public ResultServiceImpl(ResultRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Result existingEntity, Result newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setTournamentId(newEntity.getTournamentId());
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setResult(newEntity.getResult());
        existingEntity.setPoint(newEntity.getPoint());
    }
}
