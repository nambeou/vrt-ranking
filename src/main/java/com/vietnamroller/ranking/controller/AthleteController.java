package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Achievement;
import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.model.linked.OverallAthletes;
import com.vietnamroller.ranking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/athletes")
public class AthleteController extends GenericReactiveController<Athlete, Long> {


    private final OverallAthletesService overallAthletesService;
    private final AthleteRankingsService athleteRankingsService;
    private final RankingService rankingService;
    private final AchievementService achievementService;
    private final AthleteAchievementsService athleteAchievementsService;

    @Autowired

    public AthleteController(AthleteService service,
                             OverallAthletesService overallAthletesService,
                             AthleteRankingsService athleteRankingsService,
                             RankingService rankingService,
                             AchievementService achievementService,
                             AthleteAchievementsService athleteAchievementsService) {
        super(service);
        this.overallAthletesService = overallAthletesService;
        this.athleteRankingsService = athleteRankingsService;
        this.rankingService = rankingService;
        this.athleteAchievementsService = athleteAchievementsService;
        this.achievementService = achievementService;
    }

    @GetMapping("/{athleteId}/rankings")
    public Flux<Ranking> getAllRankingsByAthleteId(@PathVariable Long athleteId) {
        return athleteRankingsService
                .findAllRankingsByAthleteId(athleteId)
                .map(AthleteRankings::getRankingId)
                .collectList()
                .flatMapMany(this.rankingService::getAll);
    }


    @GetMapping("/{athleteId}/achievements")
    public Flux<Achievement> getAllAchievementsByAthleteId(@PathVariable Long athleteId) {
        return athleteAchievementsService.
                findAllAchievementsByAthleteId(athleteId)
                .map(AthleteAchievements::getAchievementId)
                .collectList()
                .flatMapMany(this.achievementService::getAll);
    }

    @GetMapping("/{athleteId}/overalls")
    public Flux<OverallAthletes> getAllOverallByAthleteId(@PathVariable Long athleteId) {
        return overallAthletesService.findAllOverallsByAthleteId(athleteId);
    }
}
