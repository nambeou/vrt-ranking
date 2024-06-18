package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Overall;
import com.vietnamroller.ranking.model.linked.OverallAthletes;
import com.vietnamroller.ranking.service.OverallAthletesService;
import com.vietnamroller.ranking.service.OverallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/overalls")
public class OverallController extends GenericReactiveController<Overall, Long> {

    private final OverallAthletesService overallAthletesService;

    @Autowired
    public OverallController(OverallService service, OverallAthletesService overallAthletesService) {
        super(service);
        this.overallAthletesService = overallAthletesService;
    }

    @GetMapping("/{overallId}/athletes")
    public Flux<OverallAthletes> getAllAthletesByOverallId(@PathVariable Long overallId) {
        return overallAthletesService.findAllAthletesByOverallId(overallId);
    }
}
