package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import com.vietnamroller.ranking.repository.AthleteAchievementsRepository;
import com.vietnamroller.ranking.service.AthleteAchievementsService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;

@Service
public class AthleteAchievementsServiceImpl extends GenericReactiveService<AthleteAchievements, Long> implements AthleteAchievementsService {

    public AthleteAchievementsServiceImpl(AthleteAchievementsRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(AthleteAchievements existingEntity, AthleteAchievements newEntity) {
        existingEntity.setAthleteId(newEntity.getAthleteId());
        existingEntity.setAchievementId(newEntity.getAchievementId());
    }
}
