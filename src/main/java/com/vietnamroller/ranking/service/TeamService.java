package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Team;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamService extends ReactiveCrudService<Team, Long> {
    // Additional methods specific to TeamService can be declared here
}
