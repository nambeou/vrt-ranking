package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.AchievementDTO;
import com.vietnamroller.ranking.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementService extends GenericService<AchievementDTO, Long> {

    private final AchievementRepository achievementRepository;
    private final TournamentService tournamentService;
    private final CategoryService categoryService;

    @Override
    protected R2dbcRepository<AchievementDTO, Long> repository() {
        return this.achievementRepository;
    }

    @Override
    Mono<AchievementDTO> addDetails(AchievementDTO achievement) {
        return Mono.zip(
                        categoryService.findById(achievement.getCategoryId()),
                        tournamentService.findById(achievement.getTournamentId())
                )
                .map(tuple -> {
                    achievement.setCategory(tuple.getT1());
                    achievement.setTournament(tuple.getT2());
                    return achievement;
                });
    }
}
