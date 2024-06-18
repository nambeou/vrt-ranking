package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.Team;
import com.vietnamroller.ranking.repository.AthleteRepository;
import com.vietnamroller.ranking.repository.TeamRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TeamService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TeamServiceImpl extends GenericReactiveService<Team, Long> implements TeamService {

    private final TeamRepository repository;
    private final AthleteRepository athleteRepository;

    public TeamServiceImpl(TeamRepository repository, AthleteRepository athleteRepository) {
        super(repository);
        this.repository = repository;
        this.athleteRepository = athleteRepository;
    }

    @Override
    protected void updateEntity(Team existingEntity, Team newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDateOfCreation(newEntity.getDateOfCreation());
        existingEntity.setLocation(newEntity.getLocation());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setLogoUrl(newEntity.getLogoUrl());
    }

    @Override
    public Flux<Athlete> findAllAthletesByTeamId(Long teamId) {
        return athleteRepository.findAllByTeamId(teamId);
    }
}
