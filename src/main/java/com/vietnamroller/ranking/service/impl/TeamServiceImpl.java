package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Team;
import com.vietnamroller.ranking.repository.TeamRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends GenericReactiveService<Team, Long> implements TeamService {

    public TeamServiceImpl(TeamRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Team existingEntity, Team newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDateOfCreation(newEntity.getDateOfCreation());
        existingEntity.setLocation(newEntity.getLocation());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setLogoUrl(newEntity.getLogoUrl());
    }
}
