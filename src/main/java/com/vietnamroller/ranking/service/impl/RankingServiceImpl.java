package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.repository.RankingRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RankingServiceImpl extends GenericReactiveService<Ranking, Long> implements RankingService {

    public RankingServiceImpl(RankingRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Ranking existingEntity, Ranking newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setPoint(newEntity.getPoint());
        existingEntity.setBestResult(newEntity.getBestResult());
    }

}
