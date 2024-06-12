package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.repository.TournamentRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TournamentServiceImpl extends GenericReactiveService<Tournament, Long> implements TournamentService {

    public TournamentServiceImpl(TournamentRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Tournament existingEntity, Tournament newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setStartDate(newEntity.getStartDate());
        existingEntity.setEndDate(newEntity.getEndDate());
        existingEntity.setLocation(newEntity.getLocation());
    }

}
