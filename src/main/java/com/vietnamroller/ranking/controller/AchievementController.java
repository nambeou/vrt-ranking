package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Achievement;
import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import com.vietnamroller.ranking.service.AchievementService;
import com.vietnamroller.ranking.service.AthleteAchievementsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/achievements")
public class AchievementController extends GenericReactiveController<Achievement, Long> {
    private final AthleteAchievementsService athleteAchievementsService;

    public AchievementController(AchievementService service, AthleteAchievementsService athleteAchievementsService) {
        super(service);
        this.athleteAchievementsService = athleteAchievementsService;
    }

    @GetMapping("/athlete/{athleteId}")
    public Flux<Achievement> getAllRankingsByAthleteId(@PathVariable Long athleteId) {
        return this.athleteAchievementsService
                .findAllAchievementsByAthleteId(athleteId)
                .map(AthleteAchievements::getAchievementId)
                .collectList()
                .flatMapMany(this.service::getAll);
    }
}
