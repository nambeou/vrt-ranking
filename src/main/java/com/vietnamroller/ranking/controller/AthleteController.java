package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.linked.AthleteAchievements;
import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.model.linked.OverallAthletes;
import com.vietnamroller.ranking.service.AthleteAchievementsService;
import com.vietnamroller.ranking.service.AthleteRankingsService;
import com.vietnamroller.ranking.service.AthleteService;
import com.vietnamroller.ranking.service.OverallAthletesService;
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
    private final AthleteAchievementsService athleteAchievementsService;

    @Autowired

    public AthleteController(AthleteService service,
                             OverallAthletesService overallAthletesService,
                             AthleteRankingsService athleteRankingsService,
                             AthleteAchievementsService athleteAchievementsService) {
        super(service);
        this.overallAthletesService = overallAthletesService;
        this.athleteRankingsService = athleteRankingsService;
        this.athleteAchievementsService = athleteAchievementsService;
    }

    @GetMapping("/{athleteId}/rankings")
    public Flux<AthleteRankings> getAllRankingsByAthleteId(@PathVariable Long athleteId) {
        return athleteRankingsService.findAllRankingsByAthleteId(athleteId);
    }


    @GetMapping("/{athleteId}/achievements")
    public Flux<AthleteAchievements> getAllAchievementsByAthleteId(@PathVariable Long athleteId) {
        return athleteAchievementsService.findAllAchievementsByAthleteId(athleteId);
    }

    @GetMapping("/{athleteId}/overalls")
    public Flux<OverallAthletes> getAllOverallByAthleteId(@PathVariable Long athleteId) {
        return overallAthletesService.findAllOverallsByAthleteId(athleteId);
    }
}
