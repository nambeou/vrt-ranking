package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankings")
public class RankingController extends GenericReactiveController<Ranking, Long> {

    public RankingController(RankingService service) {
        super(service);
    }
}
