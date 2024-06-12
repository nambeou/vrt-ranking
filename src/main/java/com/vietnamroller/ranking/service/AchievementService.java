package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Achievement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AchievementService extends ReactiveCrudService<Achievement, Long> {
    // Additional methods specific to AchievementService can be declared here
}
