package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.model.linked.AthleteRankings;
import com.vietnamroller.ranking.service.AthleteRankingsService;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController extends GenericReactiveController<Ranking, Long> {

    private final AthleteRankingsService athleteRankingsService;
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService service, AthleteRankingsService athleteRankingsService) {
        super(service);
        this.rankingService = service;
        this.athleteRankingsService = athleteRankingsService;
    }

    @GetMapping("/athlete/{athleteId}")
    public Flux<Ranking> getAllRankingsByAthleteId(@PathVariable Long athleteId) {
        return this.athleteRankingsService
                .findAllRankingsByAthleteId(athleteId)
                .map(AthleteRankings::getRankingId)
                .collectList()
                .flatMapMany(this.service::getAll);
    }

    @GetMapping("/category/{categoryId}")
    public Flux<AthleteRankings> getAllRankingsByCategoryId(@PathVariable Long categoryId) {
        return this.rankingService.getByCategoryId(categoryId)
                .map(Ranking::getId)
                .flatMap(this.athleteRankingsService::findAthleteByRankingId);
    }

}
