package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournaments")
public class TournamentController extends GenericReactiveController<Tournament, Long> {

    public TournamentController(TournamentService service) {
        super(service);
    }
}
