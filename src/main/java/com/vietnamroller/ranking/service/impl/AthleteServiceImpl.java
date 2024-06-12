package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.repository.AthleteRepository;
import com.vietnamroller.ranking.service.AthleteService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AthleteServiceImpl extends GenericReactiveService<Athlete, Long> implements AthleteService {

    public AthleteServiceImpl(AthleteRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Athlete existingEntity, Athlete newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDateOfBirth(newEntity.getDateOfBirth());
        existingEntity.setGender(newEntity.getGender());
        existingEntity.setJoinDate(newEntity.getJoinDate());
        existingEntity.setProfilePhotoUrl(newEntity.getProfilePhotoUrl());
        existingEntity.setTeamId(newEntity.getTeamId());
    }

}
