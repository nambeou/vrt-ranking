package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Achievement;
import com.vietnamroller.ranking.service.AchievementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/achievements")
public class AchievementController extends GenericReactiveController<Achievement, Long> {

    public AchievementController(AchievementService service) {
        super(service);
    }

}
