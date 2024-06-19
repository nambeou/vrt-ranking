package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.repository.RankingRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.RankingService;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RankingServiceImpl extends GenericReactiveService<Ranking, Long> implements RankingService {

    private final ResultService resultService;
    private final RankingRepository rankingRepository;

    public RankingServiceImpl(RankingRepository repository, ResultService resultService) {
        super(repository);
        this.rankingRepository = repository;
        this.resultService = resultService;
    }

    @Override
    protected void updateEntity(Ranking existingEntity, Ranking newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setRank(newEntity.getRank());
        existingEntity.setBestResult(newEntity.getBestResult());
    }

    @Override
    protected Mono<Ranking> enrich(Ranking entity) {
        return resultService.getById(entity.getBestResultId())
                .map(result -> {
                    entity.setBestResult(result);
                    entity.setCategory(result.getCategory());
                    return entity;
                });
    }

    @Override
    public Flux<Ranking> getByCategoryId(Long categoryId) {
        return rankingRepository
                .findAllByCategoryId(categoryId)
                .flatMap(this::enrich);
    }

}
