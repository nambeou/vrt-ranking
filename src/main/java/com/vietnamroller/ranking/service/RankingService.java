package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.RankingDTO;
import com.vietnamroller.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingService extends GenericService<RankingDTO, Long> {

    private final RankingRepository rankingRepository;
    private final CategoryService categoryService;


    @Override
    protected R2dbcRepository<RankingDTO, Long> repository() {
        return this.rankingRepository;
    }

    @Override
    Mono<RankingDTO> addDetails(RankingDTO ranking) {
        return categoryService.findById(ranking.getCategoryId())
                .map(category -> {
                    ranking.setCategory(category);
                    return ranking;
                });
    }
}
