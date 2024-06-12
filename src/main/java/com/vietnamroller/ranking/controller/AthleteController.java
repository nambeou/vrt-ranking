package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.service.AthleteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/athletes")
public class AthleteController extends GenericReactiveController<Athlete, Long> {

    public AthleteController(AthleteService service) {
        super(service);
    }
}
