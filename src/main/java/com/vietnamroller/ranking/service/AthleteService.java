package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.AthleteDTO;
import com.vietnamroller.ranking.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AthleteService extends GenericService<AthleteDTO, Long> {

    private final AthleteRepository athleteRepository;
    private final RankingService rankingService;
    private final AchievementService achievementService;

    @Override
    protected R2dbcRepository<AthleteDTO, Long> repository() {
        return this.athleteRepository;
    }

    @Override
    Mono<AthleteDTO> addDetails(AthleteDTO athlete) {
        return Mono.zip(
                        rankingService.findByIds(athlete.getAchievementIds()).collectList(),
                        achievementService.findByIds(athlete.getAchievementIds()).collectList()
                )
                .map(tuple -> {
                    athlete.setRankings(tuple.getT1());
                    athlete.setAchievements(tuple.getT2());
                    return athlete;
                });
    }
}
