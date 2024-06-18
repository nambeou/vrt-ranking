package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/results")
public class ResultController extends GenericReactiveController<Result, Long> {

    private final ResultService resultService;

    @Autowired

    public ResultController(ResultService service) {
        super(service);
        this.resultService = service;
    }

    @GetMapping("/athlete/{athleteId}")
    public Flux<Result> getAllResultsByAthleteId(@PathVariable Long athleteId) {
        return resultService.findAllResultsByAthleteId(athleteId);
    }

    @GetMapping("/tournament/{tournamentId}")
    public Flux<Result> getAllResultsByTournamentId(@PathVariable Long tournamentId) {
        return resultService.findAllResultsByTournamentId(tournamentId);
    }
}
