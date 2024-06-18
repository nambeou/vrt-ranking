package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController extends GenericReactiveController<Ranking, Long> {

    @Autowired
    public RankingController(RankingService service) {
        super(service);
    }

}
