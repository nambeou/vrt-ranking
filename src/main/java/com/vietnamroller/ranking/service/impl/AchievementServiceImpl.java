package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Achievement;
import com.vietnamroller.ranking.repository.AchievementRepository;
import com.vietnamroller.ranking.service.AchievementService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AchievementServiceImpl extends GenericReactiveService<Achievement, Long> implements AchievementService {

    public AchievementServiceImpl(AchievementRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Achievement existingEntity, Achievement newEntity) {
        existingEntity.setTournamentId(newEntity.getTournamentId());
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setPosition(newEntity.getPosition());
    }

}
