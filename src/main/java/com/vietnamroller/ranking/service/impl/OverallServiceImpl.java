package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Overall;
import com.vietnamroller.ranking.repository.OverAllRepository;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.OverallService;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OverallServiceImpl extends GenericReactiveService<Overall, Long> implements OverallService {

    private final CategoryService categoryService;
    private final RankingService rankingService;
    public OverallServiceImpl(OverAllRepository repository, CategoryService categoryService, RankingService rankingService) {
        super(repository);
        this.categoryService = categoryService;
        this.rankingService = rankingService;
    }

    @Override
    protected void updateEntity(Overall existingEntity, Overall newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
    }

    @Override
    protected Mono<Overall> enrich(Overall entity) {
        return Mono.zip(
                categoryService.getById(entity.getCategoryId()),
                rankingService.getByCategoryId(entity.getCategoryId()).collectList()
        ).map(tuple -> {
            entity.setCategory(tuple.getT1());
            entity.setRankings(tuple.getT2());
            return entity;
        });
    }
}
